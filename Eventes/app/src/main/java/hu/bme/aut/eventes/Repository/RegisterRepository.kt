package hu.bme.aut.eventes.Repository

import hu.bme.aut.eventes.Model.Data.User
import hu.bme.aut.eventes.Presenter.RegisterPresenter

class RegisterRepository: Repository<RegisterPresenter>() {

    fun createUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                addUserToDB()
                pres.successfulCreation()
            } else {
                pres.unsuccessfulCreation()
            }
        }
    }
    private fun addUserToDB(){
            val user = auth.currentUser
            val newUser = User(email = user?.email)
            db.collection("Users").document(newUser.email!!).set(newUser)
    }

}