package hu.bme.aut.eventes.Presenter

import hu.bme.aut.eventes.Interfaces.Contracts.IView.IRegisterContract
import hu.bme.aut.eventes.Repository.RegisterRepository

class RegisterPresenter(private var registerView: IRegisterContract.IRegisterView, var repo: RegisterRepository = RegisterRepository()): IRegisterContract.IRegisterPresenter
{
    override fun create(email : String,password : String){
        repo.setPresenter(this)
        if(!registerView.checkInputs())
            return
        repo.createUser(email,password)
        }
    fun successfulCreation() {
        registerView.startMainActivity()
    }
    fun unsuccessfulCreation(){
        registerView.showToast("User creation failed.")
    }
}