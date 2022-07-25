package hu.bme.aut.eventes

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import hu.bme.aut.eventes.Interfaces.Contracts.IView.IChatContract
import hu.bme.aut.eventes.Model.Data.Chat
import hu.bme.aut.eventes.Model.Data.Message
import hu.bme.aut.eventes.Presenter.ChatPresenter
import hu.bme.aut.eventes.Repository.ChatRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.doAnswer
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations.initMocks
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ChatPresenterTest {
    private lateinit var presenter: ChatPresenter

    @Mock
    private lateinit var view: IChatContract.IView
    @Mock
    private lateinit var repo: ChatRepository
    @Mock
    private lateinit var adapter: IChatContract.IAdapter
    @Mock
    private lateinit var uri: Uri
    @Mock
    private lateinit var auth: FirebaseAuth
    @Mock
    private lateinit var user: FirebaseUser

    private lateinit var msg: Message
    private lateinit var msg2: Message
    private lateinit var msgs: Chat

    @Before
    fun setup(){
        initMocks(this)

        Mockito.`when`(auth.currentUser).thenReturn(user)
        Mockito.`when`(user.email).thenReturn("myEmail")
        Mockito.`when`(repo.auth).thenReturn(auth)

        presenter = ChatPresenter(view,repo)
        msg = Message("textTest","friendEmail")
        msg2 = Message("textTest2","myEmail")
        val msgArray = ArrayList<Message>()
        msgArray.add(msg)
        msgArray.add(msg2)
        msgs = Chat(msgArray)

        presenter.messages.addMessage(msg)
    }

    @Test
    fun testBindViewModel(){
        doAnswer{
            presenter.setSenderPic(uri)
            presenter.setSenderName(msg.sender)
        }.`when`(repo).getMessageSender(msg)

        presenter.bindViewModel(adapter,0)

        verify(adapter).setTextPast(msg.text)
        verify(adapter).setSenderName(msg.sender)
        verify(adapter).setSenderPic(uri)
        verify(adapter).leftSide()
    }
    @Test
    fun testBindViewModelMe(){
        presenter.messages.addMessage(Message("myText","myEmail"))
        presenter.bindViewModel(adapter,1)
        verify(adapter).rightSide()
    }
    @Test
    fun testInitMessages(){
        doAnswer { presenter.showMessages(msgs) }.`when`(repo).getMessages("myEmailXfriendEmail" )

        presenter.initMessages(1,"friendEmail")

        assert(presenter.messages.containsMessage(msg))
        assert(presenter.messages.containsMessage(msg2))
        verify(view).scrollToBottom(1)
    }

}