package hu.bme.aut.eventes.Presenter

import com.google.firebase.firestore.*
import hu.bme.aut.eventes.Interfaces.Contracts.IView.IEventListContract
import hu.bme.aut.eventes.Model.Data.Event
import hu.bme.aut.eventes.Model.Enum.State
import hu.bme.aut.eventes.Model.EventListModel
import hu.bme.aut.eventes.Repository.EventListRepository

class EventListPresenter(private var eventListView: IEventListContract.IEventView, var repo: EventListRepository = EventListRepository()) : IEventListContract.IEventPresenter{
    var events = EventListModel()
    var holder: IEventListContract.IEventAdapter? = null
    var position = 100

    init{
        repo.setPresenter(this)
    }

    override fun eventChangeListener(category: Int?,email: String?) {
        if(category == null) {
            repo.allEventChange()
        }
        else if(category == 1)
        {
            repo.myEventChange()
        }
        else if(category == 2)
        {
            repo.beThereEventChange()
        }
        else if( category == 3 && email != null && email != "missing")
        {
            repo.othersEventChange(email)
        }

    }

    fun onDataChange(value: QuerySnapshot?, cat: Int? = null){
        for (dc: DocumentChange in value?.documentChanges!!) {
            if(cat == null) {
                val e = dc.document.toObject(Event::class.java)
                if(e.beThere?.contains(repo.auth.currentUser?.email!!) == true) {
                    if (events.containsEvent(e))
                        events.removeEvent(e.id)
                    continue
                }
                else if(dc.type != DocumentChange.Type.ADDED && !events.containsEvent(e))
                    events.addEvent(e)

            }
            if (dc.type == DocumentChange.Type.ADDED) {
                events.addEvent(dc.document.toObject(Event::class.java))
            }
            if (dc.type == DocumentChange.Type.REMOVED) {
                val d = dc.document.toObject(Event::class.java)
                events.removeEvent(d.id)
            }
            if (dc.type == DocumentChange.Type.MODIFIED) {
                val d = dc.document.toObject(Event::class.java)
                events.modifyEvent(d)
            }

        }
        if(cat == 3)
            eventListView.updateAdapter(null)
        else
            eventListView.updateAdapter(cat)

    }

    override fun bindViewHolder(holder: IEventListContract.IEventAdapter, position: Int) {
        val event = events.getEvent(position)

        this.holder = holder

        holder.setName(event.name)
        holder.setDate(event.date)
        holder.setLocation(event.location)
        holder.setSubBtnImg(selector(event))
    }


     override fun subBtnClick(position: Int){
         val event = events.getEvent(position)
         when {
             selector(event) == State.DELETE -> {
                 repo.deleteMyEvent(event)
             }
             selector(event) == State.UNSUBSCRIBE -> {
                 repo.unsubFromEvent(event)
             }
             else -> {
                 repo.subToEvent(event)
             }
         }
    }


    override fun frameClick(position: Int){
        val event = events.getEvent(position)
        val eventArray = ArrayList<String>()
        eventArray.add(event.owner ?: "missing")
        eventArray.add(event.name ?: "missing")
        eventArray.add(event.date ?: "missing")
        eventArray.add(event.desc ?: "missing")
        eventArray.add(event.location ?: "missing")
        holder?.openEventActivity(eventArray,event.beThere,true,event.id,
            event.owner == repo.currentUser || event.beThere?.contains(repo.currentUser) == true
        )

    }

    override fun getEventCount(): Int {
        return events.getEventCnt()
    }


    private fun selector(event: Event): State {
        if (event.owner.equals(repo.currentUser))
            return State.DELETE
        if (event.beThere != null && event.beThere!!.contains(repo.currentUser))
            return State.UNSUBSCRIBE
        return State.SUBSCRIBE

    }

}