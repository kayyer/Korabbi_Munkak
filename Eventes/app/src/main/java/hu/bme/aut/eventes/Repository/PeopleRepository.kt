package hu.bme.aut.eventes.Repository

import com.google.firebase.storage.FirebaseStorage
import hu.bme.aut.eventes.Interfaces.Contracts.IView.IPeopleContract
import hu.bme.aut.eventes.Model.Data.User
import hu.bme.aut.eventes.Presenter.PeoplePresenter

class PeopleRepository: Repository<PeoplePresenter>() {
    fun fillPeople(fr: String){
        db.collection("Users").document(fr).get().addOnSuccessListener { documentSnapshot ->
            val user = documentSnapshot.toObject(User::class.java)
            if(user != null)
                pres.updateUsers(user)

        }
    }
    fun userChange() {
        db.collection("Users").document(auth.currentUser?.email!!)
            .addSnapshotListener { value, _ ->
                val user = value?.toObject(User::class.java)
                val friends = user?.friends
                if (friends != null) {
                    val map = mutableMapOf<String, Int>()
                    friends.associateByTo(map, { it.name!! }, { it.connectCnt!! })
                    pres.clearUsers()
                    for (fr in map.keys) {
                        if (fr == "")
                            continue
                        fillPeople(fr)
                    }
                    pres.notifyAdapter()

                }
            }
    }
    fun getProfPic(email: String?,holder: IPeopleContract.IAdapter){
        FirebaseStorage.getInstance().reference.child(email+ "Profile.jpg").downloadUrl.addOnSuccessListener{
            pres.setProfPic(it, holder)
        }.addOnFailureListener{
            pres.setProfPic(null,holder)
        }
    }

}