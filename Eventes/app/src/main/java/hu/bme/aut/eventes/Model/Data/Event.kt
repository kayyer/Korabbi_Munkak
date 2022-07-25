package hu.bme.aut.eventes.Model.Data

data class Event(var id: String ?= null,
                 var owner: String ?= null,
                 var name: String ?= null,
                 var date: String ?= null,
                 var desc: String ?= null,
                 var location: String ?= null,
                 var beThere: ArrayList<String> ?= null){
    override fun equals(other: Any?): Boolean {
        if(other is Event)
            return other.id == this.id
        return false
    }
}