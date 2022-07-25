package hu.bme.aut.eventes.Model.Data

data class Friend(var name : String ?= null, var connectCnt : Int ?= null){
    override fun equals(other: Any?): Boolean {
        return this.name == (other as Friend).name && this.connectCnt == other.connectCnt
    }
}
