package hu.bme.aut.eventes.Presenter

import android.net.Uri
import hu.bme.aut.eventes.Interfaces.Contracts.IView.IChatContract
import hu.bme.aut.eventes.Model.ChatModel
import hu.bme.aut.eventes.Model.Data.Chat
import hu.bme.aut.eventes.Model.Data.Message
import hu.bme.aut.eventes.Repository.ChatRepository

class ChatPresenter(private val chatView: IChatContract.IView, var repo: ChatRepository = ChatRepository()): IChatContract.IPresenter {
    var messages =  ChatModel()
    private var msg: Message? = null
    private var holder: IChatContract.IAdapter? = null

    init{
        repo.setPresenter(this)
    }
    override fun bindViewModel(holder: IChatContract.IAdapter, position: Int){
        repo.setPresenter(this)
        this.holder = holder
        msg = messages.getMessage(position)
        if(msg != null) {
            holder.setTextPast(msg?.text)
            repo.getMessageSender(msg!!)

            if (msg?.sender == repo.auth.currentUser?.email) {
                holder.rightSide()
            } else {
                holder.leftSide()
            }
        }
    }

    override fun getMsgCount(): Int {
        return messages.getMsgCnt()
    }

    override fun initMessages(category: Int, id: String) {
        if (category == 1) {
            repo.getMessages(id + "X" + repo.auth.currentUser?.email!!)
            repo.getMessages(repo.auth.currentUser?.email!! + "X" + id)
        } else if (category == 2) {
            repo.getMessages(id)
        }
    }

    override fun createMessage(text: String?) {
        repo.uploadMessage(text)
    }

    fun setSenderName(name: String?){
        holder?.setSenderName(name)
    }
    fun setSenderPic(uri: Uri){
        holder?.setSenderPic(uri)
    }
    fun showMessages(msgs: Chat){
        messages.clearMessages()
        for (msg in msgs.Messages!!) {
            messages.addMessage(msg)
            chatView.updateAdapter()
        }
        chatView.scrollToBottom(messages.getMsgCnt() - 1)
    }


}