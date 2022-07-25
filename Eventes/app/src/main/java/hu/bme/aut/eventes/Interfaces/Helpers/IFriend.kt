package hu.bme.aut.eventes.Interfaces.Helpers

import com.google.firebase.firestore.FirebaseFirestore
import hu.bme.aut.eventes.Model.Data.Chat
import hu.bme.aut.eventes.Model.Data.Friend
import hu.bme.aut.eventes.Model.Data.User


interface IFriend {
    fun addFriend(user1: String, user2: String,createChat: Boolean = false){
        val db = FirebaseFirestore.getInstance()

        val currUser = db.collection("Users").document(user1)
        currUser.get().addOnSuccessListener { documentSnapshot ->
            val user = documentSnapshot.toObject(User::class.java)
            val map = mutableMapOf<String,Int>()
            if(user?.friends == null)
                user?.friends = ArrayList()
            user?.friends?.associateByTo(map,{it.name!!},{it.connectCnt!!})
            var friend = map[user2]
            if(friend == null)
            {
                map.put(user2,1)
                if(createChat)
                {
                    FirebaseFirestore.getInstance().collection("Chat").document(user1 + "X" + user2).set(
                        Chat(ArrayList())
                    )
                }
            }
            else {
                map[user2] = ++friend
            }
            currUser.update("friends",   map.toList().map { Friend(it.first,it.second) })
        }

    }
    fun removeFriend(user1 : String, user2: String,deleteChat : Boolean = false) {
        val db = FirebaseFirestore.getInstance()
        val currUser = db.collection("Users").document(user1)
        currUser.get().addOnSuccessListener { documentSnapshot ->
            val user = documentSnapshot.toObject(User::class.java)
            val map = mutableMapOf<String,Int>()
            user?.friends?.associateByTo(map,{it.name!!},{it.connectCnt!!})
            var friend = map[user2]
            if(friend != null) {
                friend -= 1
                if (friend == 0) {
                    map.remove(user2)
                    if(deleteChat)
                      FirebaseFirestore.getInstance().collection("Chat").document(user1 + "X" + user2).delete()
                }
                else {
                    map[user2] = friend
                }
                currUser.update("friends", map.toList().map { Friend(it.first,it.second) })
            }
        }
    }
}