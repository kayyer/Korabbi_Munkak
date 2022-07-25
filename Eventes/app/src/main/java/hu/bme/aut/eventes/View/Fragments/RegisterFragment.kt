package hu.bme.aut.eventes.View.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import hu.bme.aut.eventes.Interfaces.Contracts.IView.IRegisterContract
import hu.bme.aut.eventes.View.Activities.MainActivity
import hu.bme.aut.eventes.Presenter.RegisterPresenter

import hu.bme.aut.eventes.databinding.RegisterFragmentBinding


class RegisterFragment: Fragment(), IRegisterContract.IRegisterView {

    private lateinit var binding: RegisterFragmentBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var registerPres : IRegisterContract.IRegisterPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = RegisterFragmentBinding.inflate(inflater, container, false)
        registerPres = RegisterPresenter(this)
        auth = FirebaseAuth.getInstance()
        binding.btnRegister.setOnClickListener{
            registerPres.create(binding.etEmailAddress.text.toString(), binding.etPassword.text.toString())
        }
        return binding.root
    }


    override fun checkInputs(): Boolean {
        var bad = false
        if (!binding.etEmailAddress.text.toString().contains("@")) {
            binding.etEmailAddress.error = "The email address must contain @ sign"
            bad = true
        }
        if (binding.etPassword.text.toString() != binding.etPassword2.text.toString()) {
            binding.etPassword.error = "Passwords should be the same"
            binding.etPassword2.error = "Passwords should be the same"
            bad = true
        }
        if (binding.etEmailAddress.text.toString() == "") {
            binding.etEmailAddress.error = "The email address field can't be empty"
            bad = true
        }
        if (binding.etPassword.text.toString().length < 6) {
            binding.etPassword.error = "The password can't be shorter than 6"
            bad = true
        }
        if (binding.etPassword2.text.toString() == "") {
            binding.etPassword2.error = "The password field can't be empty"
            bad = true
        }
        return !bad
    }
    override fun startMainActivity() {
        val mainIntent = Intent(requireActivity(), MainActivity::class.java)
        startActivity(mainIntent)
    }

    override fun showToast(message: String) {
        Toast.makeText(requireActivity().baseContext, message,
            Toast.LENGTH_SHORT).show()
    }
}