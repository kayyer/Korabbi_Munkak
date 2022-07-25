package hu.bme.aut.eventes.Interfaces.Contracts.IView

import android.net.Uri

interface IChatContract {
    interface IView{
        fun updateAdapter()
        fun scrollToBottom(pos: Int)
    }
    interface IAdapter{
        fun setTextPast(text: String?)
        fun setSenderPic(uri: Uri)
        fun setSenderName(text: String?)
        fun rightSide()
        fun leftSide()
    }
    interface IPresenter{
        fun bindViewModel(holder: IAdapter, position: Int)
        fun getMsgCount(): Int
        fun initMessages(category: Int,id: String)
        fun createMessage(text: String?)
    }
}