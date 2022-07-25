package hu.bme.aut.eventes.Interfaces.Contracts.IView

import android.content.ContentValues
import android.net.Uri

interface IPopUpContract {
    interface IView{
        fun openCamera(values: ContentValues)
    }
    interface IPresenter{
        fun initCamera()
        fun uploadPicture(imageUri: Uri?)
    }
}