package hu.bme.aut.eventes.View.Activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import hu.bme.aut.eventes.View.Adapters.EventAdapter
import hu.bme.aut.eventes.View.Adapters.EventWOChatAdapter
import hu.bme.aut.eventes.databinding.ActivityMainBinding


class EventActivity: AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding


    public override fun onStart() {
        super.onStart()
        if (auth.currentUser == null) {
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        auth = FirebaseAuth.getInstance()

        setContentView(binding.root)

        val extras = this.intent.extras
        val chatAvailable = extras?.getBoolean("chatAvailable")
        if(chatAvailable == true)
            binding.vpMain.adapter = EventAdapter(supportFragmentManager)
        else
            binding.vpMain.adapter = EventWOChatAdapter(supportFragmentManager)




    }
}