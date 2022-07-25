package hu.bme.aut.eventes.Repository

import com.google.firebase.firestore.DocumentReference
import hu.bme.aut.eventes.Model.Data.User
import hu.bme.aut.eventes.Presenter.MyProfilePresenter

class MyProfileRepository: Repository<MyProfilePresenter>() {
    var profile: DocumentReference?= null

    fun getUserData(){
        profile = db.collection("Users").document(auth.currentUser?.email!!)
        profile?.addSnapshotListener { value, _ ->
            val myUser = value?.toObject(User::class.java)
            pres.setUserData(myUser)
        }
    }
    fun update(desc: String,name: String){
        profile?.update("description",desc)
        profile?.update("nickname",name)
    }
    fun getProfPic() {
        storage.reference.child(auth.currentUser?.email + "Profile.jpg").downloadUrl.addOnSuccessListener {
            pres.setProfPic(it)
        }
    }
}