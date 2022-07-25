package hu.bme.aut.eventes.Model

import hu.bme.aut.eventes.Model.Data.User

class PeopleModel {
    private var people =  ArrayList<User>()

    fun addUser(u: User){
        people.add(u)
    }

    fun containsUser(u: User?): Boolean{
        return people.contains(u)
    }
    fun clearUsers(){
        people.clear()
    }

    fun getPerson(Position: Int): User {
        return people[Position]
    }

    fun getPeopleCnt(): Int {
        return people.size
    }
}