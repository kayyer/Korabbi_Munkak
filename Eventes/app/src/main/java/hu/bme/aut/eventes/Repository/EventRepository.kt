package hu.bme.aut.eventes.Repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import hu.bme.aut.eventes.Model.Data.User
import hu.bme.aut.eventes.Presenter.EventPresenter

class EventRepository: Repository<EventPresenter>() {
    fun getProfPic(email: String?) {
        FirebaseStorage.getInstance().reference.child(email + "Profile.jpg").downloadUrl.addOnSuccessListener {
            pres.setProfPicEvent(it)
        }
    }
    fun getOwnerInfo(email: String){
        val friendUser = FirebaseFirestore.getInstance().collection("Users").document(email)
        friendUser.get().addOnSuccessListener { documentSnapshot ->
            val owner = documentSnapshot.toObject(User::class.java)
            pres.setOwnerInfo(owner)
            }
    }
}