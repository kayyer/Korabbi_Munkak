package hu.bme.aut.eventes.Presenter

import android.net.Uri
import hu.bme.aut.eventes.Interfaces.Contracts.IView.IEventContract
import hu.bme.aut.eventes.Interfaces.Contracts.IView.IPeopleContract
import hu.bme.aut.eventes.Model.Data.User
import hu.bme.aut.eventes.Repository.EventRepository

class EventPresenter(private val eventView: IEventContract.IEventView, var repo: EventRepository = EventRepository(), private val listPresenter: PeoplePresenter = PeoplePresenter(eventView)): IEventContract.IEventPresenter{
    var eventOwner: User? = null

    init{
        repo.setPresenter(this)
    }

    override fun setUpOwner(email: String) {
        repo.getProfPic(email)
        repo.getOwnerInfo(email)
    }

    override fun getListPres(): IPeopleContract.IPresenter {
        return listPresenter
    }

    fun profileBtnClick() {
        if(eventOwner == null)
            return
        val friendArray = java.util.ArrayList<String>()
        friendArray.add(eventOwner?.email ?: "missing")
        friendArray.add(eventOwner?.nickname ?: "missing")
        friendArray.add(eventOwner?.description ?: "missing")
        eventView.changeToProfileActivity(friendArray,false)
    }
     fun setProfPicEvent(uri: Uri?) {
        eventView.setProfilePic(uri)
    }
    fun setOwnerInfo(owner: User?){
        if (owner != null) {
            eventOwner = owner
            eventView.setText(owner.nickname)

        }
    }
}