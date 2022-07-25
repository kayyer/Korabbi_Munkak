package hu.bme.aut.eventes.Repository

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FieldValue
import hu.bme.aut.eventes.Model.Data.Chat
import hu.bme.aut.eventes.Model.Data.Message
import hu.bme.aut.eventes.Model.Data.User
import hu.bme.aut.eventes.Presenter.ChatPresenter

class ChatRepository: Repository<ChatPresenter>() {
    private var currentChat: DocumentReference? = null
    fun getMessageSender(msg: Message) {
        if (msg.sender != auth.currentUser?.email) {
            storage.reference.child(msg.sender + "Profile.jpg").downloadUrl.addOnSuccessListener {
                pres.setSenderPic(it)
            }
            db.collection("Users").document(msg.sender!!).get()
                .addOnSuccessListener { documentSnapshot ->
                    val user = documentSnapshot.toObject(User::class.java)
                    pres.setSenderName(user?.nickname)
                }
        }
    }
    fun uploadMessage(text: String?){
        val msgToSend = Message(text,auth.currentUser?.email)
        currentChat?.update("messages", FieldValue.arrayUnion(msgToSend))
    }
    fun getMessages(msgId: String){
        val dr =  db.collection("Chat").document(msgId)
        dr.addSnapshotListener { value, _ ->
            if (value != null && value.exists()) {
                val msgs = value.toObject(Chat::class.java)
                if(msgs != null) {
                    pres.showMessages(msgs)
                    currentChat = dr
                }
            }
        }

    }


}