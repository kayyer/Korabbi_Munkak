package hu.bme.aut.eventes.Interfaces.Contracts.IView

import android.net.Uri

interface IPeopleContract {
    interface IView{
        fun updateAdapter()
    }
    interface IPresenter{
        fun profileBtnClick(position: Int, holder: IAdapter)
        fun personListener()
        fun bindViewHolder(holder: IAdapter, position: Int)
        fun getPeopleCount() : Int
        fun eventPersonListener(beThere: ArrayList<String>?)

    }
    interface IAdapter{
        fun setProfileBtn(uri: Uri?)
        fun setName(name: String?)
        fun openProfileActivity(
            chatAvailable: Boolean,
            friendArray: ArrayList<String>?,
            GroupChat: Boolean,
            )
    }
}