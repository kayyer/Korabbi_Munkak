package hu.bme.aut.eventes.Interfaces.Contracts.IView

import android.net.Uri

interface IEventContract {
    interface IEventView: IPeopleContract.IView {
        fun setText(text: String?)
        fun setProfilePic(uri: Uri?)
        fun changeToProfileActivity(friendArray: ArrayList<String>, chat: Boolean)
    }
    interface IEventPresenter{
        fun setUpOwner(email: String)
        fun getListPres(): IPeopleContract.IPresenter
    }
}