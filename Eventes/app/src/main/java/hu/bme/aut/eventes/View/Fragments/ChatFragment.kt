package hu.bme.aut.eventes.View.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.eventes.Interfaces.Contracts.IView.IChatContract
import hu.bme.aut.eventes.View.Adapters.ChatAdapter
import hu.bme.aut.eventes.Presenter.ChatPresenter
import hu.bme.aut.eventes.databinding.ChatFragmentBinding

class ChatFragment : Fragment(),IChatContract.IView {
    private lateinit var binding: ChatFragmentBinding
    private lateinit var adapter: ChatAdapter
    private lateinit var chatPres: IChatContract.IPresenter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = ChatFragmentBinding.inflate(inflater, container, false)
        binding.MessageList.layoutManager = LinearLayoutManager(requireActivity())
        chatPres = ChatPresenter(this)
        adapter = ChatAdapter(chatPres)
        binding.MessageList.adapter = adapter
        val b = requireActivity().intent.extras
        val friendData = b?.getStringArrayList("user")
        val isGroupChat = b?.getBoolean("GroupChat")

        if(isGroupChat != true && friendData?.get(0) != null && friendData[0] != "missing") {
            chatPres.initMessages(1,friendData[0])
        }
        else if(isGroupChat == true)
        {
            val eventID = b.getString("eventID")
            if(eventID != null)
              chatPres.initMessages(2,eventID)
        }

        binding.sendBtn.setOnClickListener{
            if(binding.textMessage.text != null || binding.textMessage.text.toString() != "")
            {
                chatPres.createMessage(binding.textMessage.text.toString())
                binding.textMessage.text = null
            }
        }

        return binding.root

    }

    override fun scrollToBottom(pos: Int){
        binding.MessageList.layoutManager!!.scrollToPosition(pos)
    }

    override fun updateAdapter() {
        adapter.notifyDataSetChanged()
    }

    }