package hu.bme.aut.eventes.Model

import hu.bme.aut.eventes.Model.Data.Message

class ChatModel {
    private var messages = ArrayList<Message>()
    fun addMessage(c: Message){
        messages.add(c)
    }

    fun containsMessage(c: Message?): Boolean{
        return messages.contains(c)
    }
    fun clearMessages(){
        messages.clear()
    }

    fun getMessage(Position: Int): Message {
        return messages[Position]
    }

    fun getMsgCnt(): Int {
        return messages.size
    }
}