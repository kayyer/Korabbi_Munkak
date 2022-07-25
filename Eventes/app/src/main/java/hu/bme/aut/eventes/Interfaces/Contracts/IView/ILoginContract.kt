package hu.bme.aut.eventes.Interfaces.Contracts.IView

interface ILoginContract {
    interface ILoginView {
        fun showToast(message: String)
        fun startMainActivity()
    }
    interface ILoginPresenter{
        fun login(email: String, password: String)
    }


}