package hu.bme.aut.eventes.Repository

import hu.bme.aut.eventes.Interfaces.Helpers.IFriend
import hu.bme.aut.eventes.Model.Data.Event
import hu.bme.aut.eventes.Presenter.EventListPresenter

 class EventListRepository: Repository<EventListPresenter>(), IFriend {

    private val eventDB = db.collection("Events")
    var currentUser = auth.currentUser?.email

    fun deleteMyEvent(event: Event){
        db.collection("Events").document(event.id ?: "").delete()
        if(event.beThere != null) {
            for (wasThere in event.beThere!!) {
                removeFriend(auth.currentUser?.email!!, wasThere, true)
                removeFriend(wasThere, auth.currentUser?.email!!,true )
            }
        }
    }
    fun unsubFromEvent(event: Event){
        event.beThere?.remove(auth.currentUser?.email)
        val updEvent = db.collection("Events").document(event.id ?: "")
        updEvent.update("beThere", event.beThere)
        removeFriend(event.owner!!, auth.currentUser?.email!!, true)
        removeFriend(auth.currentUser?.email!!, event.owner!!, true)
    }
    fun subToEvent(event: Event){
        if (event.beThere == null) {
            event.beThere = ArrayList()
        }
        event.beThere?.add(auth.currentUser?.email!!)
        val updEvent = db.collection("Events").document(event.id ?: "")
        updEvent.update("beThere", event.beThere)
        addFriend(auth.currentUser?.email!!, event.owner!!, true)
        addFriend(event.owner!!, auth.currentUser?.email!!)
    }

    fun myEventChange(){
        eventDB.whereEqualTo("owner", auth.currentUser?.email).addSnapshotListener { value, _ -> pres.onDataChange(value,1) }
    }
    fun allEventChange(){
        eventDB.whereNotEqualTo("owner", auth.currentUser?.email).addSnapshotListener { value, _ -> pres.onDataChange(value) }
    }
    fun beThereEventChange(){
        eventDB.whereArrayContains("beThere",auth.currentUser?.email.toString()).addSnapshotListener { value, _ -> pres.onDataChange(value,2) }
    }
    fun othersEventChange(email: String?){
        eventDB.whereEqualTo("owner", email).addSnapshotListener { value, _ -> pres.onDataChange(value,3) }
    }


}