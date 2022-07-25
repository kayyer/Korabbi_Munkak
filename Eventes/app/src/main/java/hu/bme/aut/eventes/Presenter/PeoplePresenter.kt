package hu.bme.aut.eventes.Presenter

import android.net.Uri
import hu.bme.aut.eventes.Interfaces.Contracts.IView.IPeopleContract
import hu.bme.aut.eventes.Model.Data.User
import hu.bme.aut.eventes.Model.PeopleModel
import hu.bme.aut.eventes.Repository.PeopleRepository

class PeoplePresenter(private val peopleView : IPeopleContract.IView, val repo: PeopleRepository = PeopleRepository()):IPeopleContract.IPresenter {
    val people = PeopleModel()
    private var chatAvailable = true

    init{
        repo.setPresenter(this)
    }

    override fun eventPersonListener(beThere: ArrayList<String>?){
        if(beThere == null)
            return
        chatAvailable = false
        for(person in beThere)
            repo.fillPeople(person)
    }

    override fun personListener() {
        repo.userChange()
    }

    fun clearUsers(){
        people.clearUsers()
    }

    fun updateUsers(user: User){
        if (!people.containsUser(user)) {
            people.addUser(user)
            peopleView.updateAdapter()
        }
    }

    fun notifyAdapter(){
        peopleView.updateAdapter()
    }

    override fun profileBtnClick(position: Int,holder: IPeopleContract.IAdapter) {
        val friend = people.getPerson(position)
        val friendArray =  ArrayList<String>()
        friendArray.add(friend.email?:"missing")
        friendArray.add(friend.nickname?:"missing")
        friendArray.add(friend.description?:"missing")
        holder.openProfileActivity(chatAvailable,friendArray,false)

    }

    override fun bindViewHolder(holder: IPeopleContract.IAdapter, position: Int){
        val friend = people.getPerson(position)
        holder.setName(friend.nickname)
        repo.getProfPic(friend.email, holder)

    }
    fun setProfPic(it: Uri?, holder: IPeopleContract.IAdapter){
        holder.setProfileBtn(it)
    }

    override fun getPeopleCount(): Int {
       return people.getPeopleCnt()
    }
}