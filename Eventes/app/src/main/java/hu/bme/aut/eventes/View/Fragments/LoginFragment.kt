package hu.bme.aut.eventes.View.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import hu.bme.aut.eventes.databinding.LoginFragmentBinding
import android.content.Intent
import hu.bme.aut.eventes.Interfaces.Contracts.IView.ILoginContract
import hu.bme.aut.eventes.Presenter.LoginPresenter
import hu.bme.aut.eventes.View.Activities.MainActivity


class LoginFragment: Fragment(), ILoginContract.ILoginView {
    private lateinit var binding: LoginFragmentBinding
    private lateinit var loginPres : ILoginContract.ILoginPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = LoginFragmentBinding.inflate(inflater, container, false)
        loginPres = LoginPresenter(this)
        binding.btnLogin.setOnClickListener{
            loginPres.login(binding.etEmailAddress.text.toString(),binding.etPassword.text.toString())
        }
        return binding.root
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