package hu.bme.aut.eventes.Repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

open class Repository<T: Any> {
    lateinit var pres: T
    var auth = FirebaseAuth.getInstance()
    var db = FirebaseFirestore.getInstance()
    var storage = FirebaseStorage.getInstance()

    fun setPresenter(pres: T){
        this.pres = pres
    }

}