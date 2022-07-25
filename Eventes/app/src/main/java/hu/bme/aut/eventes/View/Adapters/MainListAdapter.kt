package hu.bme.aut.eventes.View.Adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.eventes.Interfaces.Contracts.IView.IEventListContract
import hu.bme.aut.eventes.Model.Enum.State
import hu.bme.aut.eventes.View.Activities.EventActivity
import hu.bme.aut.eventes.R
import hu.bme.aut.eventes.databinding.ItemEventBinding
import kotlin.collections.ArrayList

class MainListAdapter(private val eventListPres: IEventListContract.IEventPresenter) : RecyclerView.Adapter<MainListAdapter.MyViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainListAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        eventListPres.bindViewHolder(holder,position)
        holder.subButton.setOnClickListener {
            eventListPres.subBtnClick(position)
        }
        holder.frame.setOnClickListener{
          eventListPres.frameClick(position)

        }
    }


    override fun getItemCount(): Int {
        return eventListPres.getEventCount()
        //events.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),IEventListContract.IEventAdapter {

        var binding = ItemEventBinding.bind(itemView)
        var name = binding.eventName
        var date = binding.eventDate
        var location = binding.eventLocation
        var subButton = binding.fab
        var frame = binding.constraintLayout

        override fun setName(name: String?) {
            this.name.text = name
        }
        override fun setDate(date: String?){
            this.date.text = date
        }

        override fun setLocation(location: String?) {
            this.location.text = location
        }

        override fun setSubBtnImg(state: State) {
            var resourceId = R.drawable.plus
            if(state == State.UNSUBSCRIBE)
             resourceId = R.drawable.minus
            else if(state == State.DELETE)
                resourceId = R.drawable.delete
            subButton.setImageResource(resourceId)
        }

        override fun openEventActivity(
            Event: ArrayList<String>,
            BeThere: ArrayList<String>?,
            GroupChat: Boolean,
            EventID: String?,
            Chat: Boolean
        ) {
            val eventIntent = Intent(itemView.context, EventActivity::class.java)
            eventIntent.putExtra("Event", Event)
            eventIntent.putExtra("BeThere", BeThere)
            eventIntent.putExtra("GroupChat", GroupChat)
            eventIntent.putExtra("eventID", EventID)
            eventIntent.putExtra("chatAvailable", Chat)
            itemView.context.startActivity(eventIntent)

        }



    }


}
