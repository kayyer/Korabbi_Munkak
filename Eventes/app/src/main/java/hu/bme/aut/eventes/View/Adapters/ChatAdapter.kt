package hu.bme.aut.eventes.View.Adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.PARENT_ID
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import hu.bme.aut.eventes.Interfaces.Contracts.IView.IChatContract
import hu.bme.aut.eventes.R
import hu.bme.aut.eventes.databinding.ItemChatBinding


class ChatAdapter(private val chatPres: IChatContract.IPresenter) : RecyclerView.Adapter<ChatAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chat, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        chatPres.bindViewModel(holder, position)
    }

    override fun getItemCount(): Int = chatPres.getMsgCount()


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), IChatContract.IAdapter {

        var binding = ItemChatBinding.bind(itemView)
        private var textPast = binding.text
        private var frame = binding.frame
        private var senderPic = binding.senderPic
        private var senderName = binding.senderName
        private var picAndName = binding.picAndName
        private val params = frame.layoutParams as ConstraintLayout.LayoutParams

        override fun setTextPast(text: String?){
            textPast.text = text
        }
        override fun setSenderPic(uri: Uri)
        {
            Picasso.get().load(uri).into(senderPic)
        }
        override fun setSenderName(text: String?){
            senderName.text = text
        }
        override fun rightSide(){
            params.endToEnd = PARENT_ID
            params.startToStart = ConstraintLayout.LayoutParams.UNSET
            frame.setBackgroundResource(R.drawable.messageframe)
            senderPic.isVisible = false
            senderName.isVisible = false
        }
        override fun leftSide(){
            params.endToEnd = ConstraintLayout.LayoutParams.UNSET
            params.startToStart = picAndName.id
            senderPic.isVisible = true
            senderName.isVisible = true
            frame.setBackgroundResource(R.drawable.roundedtextview)

        }


    }

}
