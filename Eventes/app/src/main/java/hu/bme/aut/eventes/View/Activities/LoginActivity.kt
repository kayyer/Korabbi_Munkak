package hu.bme.aut.eventes.View.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import hu.bme.aut.eventes.View.Adapters.LoginAdapter
import hu.bme.aut.eventes.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

        private lateinit var binding: ActivityLoginBinding

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityLoginBinding.inflate(layoutInflater)

            setContentView(binding.root)

            binding.vpLogin.adapter = LoginAdapter(supportFragmentManager)

            Toast.makeText(baseContext, "Swipe right to Sign Up",
                Toast.LENGTH_LONG).show()

        }

    }
