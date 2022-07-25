package hu.bme.aut.eventes.Repository


import hu.bme.aut.eventes.Presenter.LoginPresenter

class LoginRepository: Repository<LoginPresenter>() {

    fun login(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty())
            return
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    pres.loginSuccess()
                } else {
                    pres.loginFailure()
                }
            }
    }

}
