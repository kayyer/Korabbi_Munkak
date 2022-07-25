package hu.bme.aut.eventes.Presenter

import android.content.ContentValues
import android.net.Uri
import android.provider.MediaStore
import hu.bme.aut.eventes.Interfaces.Contracts.IView.IPopUpContract
import hu.bme.aut.eventes.Repository.PopUpRepository


class PopUpPresenter(private val popUpView: IPopUpContract.IView, val repo: PopUpRepository = PopUpRepository(), private val values: ContentValues = ContentValues()): IPopUpContract.IPresenter {
    override fun initCamera(){
        val fileName = repo.auth.currentUser?.email + ".jpg"
        values.put(MediaStore.Images.Media.TITLE, fileName)
        values.put(MediaStore.Images.Media.DESCRIPTION, "Image capture by camera")
        popUpView.openCamera(values)
    }
    override fun uploadPicture(imageUri: Uri?)
    {
       repo.uploadImage(imageUri)
    }
}