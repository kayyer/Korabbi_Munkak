package hu.bme.aut.eventes.Model

import hu.bme.aut.eventes.Model.Data.Event

class EventListModel {
    private var events = ArrayList<Event>()
    fun addEvent(e: Event){
        events.add(e)
    }
    fun removeEvent(eventID : String?){
        events.removeAll{it.id  == eventID }
    }
    fun modifyEvent(event: Event){
        val ind = events.indexOfFirst{it.id == event.id}
        events[ind] = event
    }
    fun containsEvent(event: Event): Boolean{
        return events.contains(event)
    }

    fun getEvent(Position: Int): Event {
        return events[Position]
    }

    fun getEventCnt(): Int {
        return events.size
    }
}