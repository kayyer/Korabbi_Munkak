package hu.bme.aut.eventes.Presenter

import android.net.Uri
import hu.bme.aut.eventes.Interfaces.Contracts.IView.IMyProfileContract
import hu.bme.aut.eventes.Model.Data.User
import hu.bme.aut.eventes.Repository.MyProfileRepository

class MyProfilePresenter(val myProfView: IMyProfileContract.IView, val repo: MyProfileRepository = MyProfileRepository()): IMyProfileContract.IPresenter {

    init{
        repo.setPresenter(this)
    }
    override fun initProfData() {
        repo.getUserData()
    }
    override fun saveClick(desc: String, name: String){
       repo.update(desc,name)
    }
    override fun initProfPic(){
       repo.getProfPic()
    }
    fun setProfPic(uri: Uri?){
         myProfView.setProfPic(uri)
    }
    fun setUserData(myUser: User?){
        myProfView.setDescription(myUser?.description)
        myProfView.setNickname(myUser?.nickname)
    }
}