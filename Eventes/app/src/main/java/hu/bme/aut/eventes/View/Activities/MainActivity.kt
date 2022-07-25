package hu.bme.aut.eventes.View.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import hu.bme.aut.eventes.databinding.ActivityMainBinding

import hu.bme.aut.eventes.View.Adapters.MainAdapter


class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding

    public override fun onStart() {
        super.onStart()
        if(auth.currentUser == null)
        {
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.vpMain.adapter = MainAdapter(supportFragmentManager)


    }
}