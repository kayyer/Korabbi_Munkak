package hu.bme.aut.eventes.Repository

import com.google.firebase.firestore.DocumentReference
import hu.bme.aut.eventes.Interfaces.Helpers.IFriend
import hu.bme.aut.eventes.Model.Data.FriendRating
import hu.bme.aut.eventes.Model.Data.User
import hu.bme.aut.eventes.Presenter.ProfilePresenter

class ProfileRepository: Repository<ProfilePresenter>(), IFriend {
    private lateinit var friendUser: DocumentReference
    fun getProfPic(name: String){
        storage.reference.child(name + "Profile.jpg").downloadUrl.addOnSuccessListener {
            pres.changeProfPic(it)
        }
    }
    fun getRatings(friendName: String,rating: Float?){
        friendUser = db.collection("Users").document(friendName)
        friendUser.get().addOnSuccessListener { documentSnapshot ->
            val user = documentSnapshot.toObject(User::class.java)
            if(user != null)
                pres.setRating(user,rating)
        }
    }
    fun updateRatings(map: MutableMap<String,Float>){
        friendUser.update(
            "ratings",
            map.toList().map { FriendRating(it.first, it.second) })

    }
    fun addFriend(friendName: String){
        addFriend(auth.currentUser?.email!!, friendName, true)
        addFriend(friendName, auth.currentUser?.email!!)
    }
    fun removeFriend(friendName: String){
        removeFriend(auth.currentUser?.email!!, friendName,true)
        removeFriend(friendName, auth.currentUser?.email!!, true)
    }
}