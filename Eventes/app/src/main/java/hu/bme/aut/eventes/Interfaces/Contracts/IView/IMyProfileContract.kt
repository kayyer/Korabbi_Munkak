package hu.bme.aut.eventes.Interfaces.Contracts.IView

import android.net.Uri

interface IMyProfileContract {
    interface IView{
        fun setDescription(desc: String?)
        fun setNickname(name: String?)
        fun setProfPic(uri: Uri?)
    }
    interface IPresenter{
        fun saveClick(desc: String, name: String)
        fun initProfData()
        fun initProfPic()
    }
}