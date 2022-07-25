package hu.bme.aut.eventes.View.Fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import hu.bme.aut.eventes.Interfaces.Contracts.IView.IMyProfileContract
import hu.bme.aut.eventes.Presenter.MyProfilePresenter
import hu.bme.aut.eventes.databinding.MyprofileFragmentBinding

class MyProfileFragment : Fragment(), IMyProfileContract.IView {
    private lateinit var binding: MyprofileFragmentBinding
    private lateinit var myProfPres: IMyProfileContract.IPresenter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MyprofileFragmentBinding.inflate(inflater, container, false)
        myProfPres = MyProfilePresenter(this)
        myProfPres.initProfData()
        myProfPres.initProfPic()

        binding.profPic.setOnClickListener {
            val puf = PopUpFragment()
            puf.show(childFragmentManager, "PopUpFragment")
        }
        binding.saveBtn.setOnClickListener {
            myProfPres.saveClick(
                binding.description.text.toString(),
                binding.nickname.text.toString()
            )
        }
        return binding.root
    }

    override fun setDescription(desc: String?) {
        binding.description.setText(desc)
    }

    override fun setNickname(name: String?) {
        binding.nickname.setText(name)
    }

    override fun setProfPic(uri: Uri?) {
        Picasso.get().load(uri).into(binding.profPic)
    }
}