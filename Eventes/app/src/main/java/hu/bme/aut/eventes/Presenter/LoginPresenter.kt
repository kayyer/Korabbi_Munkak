package hu.bme.aut.eventes.Presenter

import hu.bme.aut.eventes.Interfaces.Contracts.IView.ILoginContract
import hu.bme.aut.eventes.Repository.LoginRepository

class LoginPresenter(private var loginView: ILoginContract.ILoginView, var repo: LoginRepository = LoginRepository()) : ILoginContract.ILoginPresenter {
    init{
        repo.setPresenter(this)
    }
    override fun login(email: String, password: String) {
        repo.login(email,password)
    }
   fun loginSuccess(){
        loginView.startMainActivity()
    }
    fun loginFailure(){
        loginView.showToast("Login failed.")
    }


}