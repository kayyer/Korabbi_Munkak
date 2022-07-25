package hu.bme.aut.eventes.Repository

import com.google.firebase.firestore.FirebaseFirestore
import hu.bme.aut.eventes.Model.Data.Chat
import hu.bme.aut.eventes.Model.Data.Event
import hu.bme.aut.eventes.Presenter.CreateEventPresenter
import java.util.ArrayList

class CreateEventRepository: Repository<CreateEventPresenter>() {
    fun createEvent(event: Event){
        event.owner = auth.currentUser?.email
        val db = FirebaseFirestore.getInstance()
        db.collection("Events").document(event.id!!).set(event)
        db.collection("Chat").document(event.id!!).set(Chat(ArrayList()))
    }
}