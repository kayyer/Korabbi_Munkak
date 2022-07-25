package hu.bme.aut.eventes.View.Activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import hu.bme.aut.eventes.View.Adapters.FriendsProfileAdapter
import hu.bme.aut.eventes.View.Adapters.FriendsProfileWOChatAdapter
import hu.bme.aut.eventes.databinding.ActivityProfileBinding
import hu.bme.aut.eventes.Interfaces.Helpers.IFriend


class ProfileActivity: AppCompatActivity(), IFriend {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityProfileBinding

    override fun onStart() {
        super.onStart()
        auth = FirebaseAuth.getInstance()
        if(auth.currentUser == null)
        {
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        binding = ActivityProfileBinding.inflate(layoutInflater)

        setContentView(binding.root)
        val extras = this.intent.extras
        val chatAvailable = extras?.getBoolean("chatAvailable")
        if(chatAvailable == true)
            binding.vpMain.adapter = FriendsProfileAdapter(supportFragmentManager)
        else
            binding.vpMain.adapter = FriendsProfileWOChatAdapter(supportFragmentManager)

        }
}