package hu.bme.aut.eventes.View.Fragments

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import hu.bme.aut.eventes.Interfaces.Contracts.IView.IPopUpContract
import hu.bme.aut.eventes.Presenter.PopUpPresenter
import hu.bme.aut.eventes.databinding.ImageSelectPopupBinding


class PopUpFragment : DialogFragment(),IPopUpContract.IView {
    private lateinit var binding: ImageSelectPopupBinding
    private var imageUri: Uri? = null
    private lateinit var popUpPres: IPopUpContract.IPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = ImageSelectPopupBinding.inflate(inflater, container, false)
        popUpPres = PopUpPresenter(this)
        binding.cameraBtn.setOnClickListener {
            popUpPres.initCamera()
        }
        binding.galleryBtn.setOnClickListener{
            val openGalleryIntent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(openGalleryIntent,1000)
        }

        return binding.root
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1000 && resultCode == Activity.RESULT_OK) {
            imageUri = data?.data
        }
        (parentFragment as MyProfileFragment).setProfPic(imageUri)
        popUpPres.uploadPicture(imageUri)

    }
    override fun openCamera(values: ContentValues){
        imageUri = requireActivity().contentResolver.insert(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            values
        )
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        startActivityForResult(intent, 1231)
    }

}