package hu.bme.aut.eventes.Interfaces.Contracts.IView

interface IRegisterContract {

    interface IRegisterView : ILoginContract.ILoginView{
        fun checkInputs(): Boolean
    }
    interface IRegisterPresenter {
        fun create(email: String, password: String)
    }

}