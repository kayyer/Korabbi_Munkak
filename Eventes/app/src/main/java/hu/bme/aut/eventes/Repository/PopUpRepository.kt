package hu.bme.aut.eventes.Repository

import android.net.Uri
import hu.bme.aut.eventes.Presenter.PopUpPresenter

class PopUpRepository: Repository<PopUpPresenter>() {
    fun uploadImage(imageUri: Uri?){
        if(imageUri != null)
            storage.reference.child(auth.currentUser?.email + "Profile.jpg").putFile(imageUri)

    }
}