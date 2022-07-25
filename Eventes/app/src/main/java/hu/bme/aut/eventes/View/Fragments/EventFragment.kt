package hu.bme.aut.eventes.View.Fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import hu.bme.aut.eventes.Interfaces.Contracts.IView.IEventContract
import hu.bme.aut.eventes.View.Activities.ProfileActivity
import hu.bme.aut.eventes.View.Adapters.PeopleAdapter
import hu.bme.aut.eventes.Presenter.EventPresenter
import hu.bme.aut.eventes.databinding.EventFragmentBinding
import java.util.ArrayList

class EventFragment: Fragment(), IEventContract.IEventView {
    private lateinit var binding: EventFragmentBinding
    private lateinit var adapter: PeopleAdapter
    private lateinit var eventData: ArrayList<String>
    private lateinit var beThere: ArrayList<String>
    private lateinit var eventPres: IEventContract.IEventPresenter



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        val extras = requireActivity().intent.extras
        if(extras?.getBoolean("chatAvailable")!!)
            Toast.makeText(requireActivity().baseContext,"Swipe right to chat", Toast.LENGTH_SHORT).show()
        eventData = extras.getStringArrayList("Event")!!
        beThere = extras.getStringArrayList("BeThere") ?: ArrayList()
        binding = EventFragmentBinding.inflate(inflater, container, false)
        binding.beThere.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        eventPres = EventPresenter(this)
        adapter = PeopleAdapter(eventPres.getListPres())
        binding.beThere.adapter = adapter

        binding.eventName.text = eventData[1]
        binding.eventDate.text = eventData[2]
        binding.eventDesc.text = eventData[3]
        binding.eventLocation.text = eventData[4]

        eventPres.getListPres().eventPersonListener(extras.getStringArrayList("BeThere"))
        eventPres.setUpOwner(eventData[0])
        binding.ProfileBtn.setOnClickListener {
            (eventPres as EventPresenter).profileBtnClick()
        }


        return binding.root

    }

    override fun updateAdapter() {
        adapter.notifyDataSetChanged()
    }

    override fun setText(text: String?) {
        binding.ownerName.text = text
    }

    override fun setProfilePic(uri: Uri?) {
        Picasso.get().load(uri).into(binding.ProfileBtn)
    }

    override fun changeToProfileActivity(friendArray: ArrayList<String>, chat: Boolean) {
        val profileIntent = Intent(context, ProfileActivity::class.java)
        profileIntent.putExtra("user", friendArray)
        profileIntent.putExtra("chatAvailable", false)
        context?.startActivity(profileIntent)

    }
}
