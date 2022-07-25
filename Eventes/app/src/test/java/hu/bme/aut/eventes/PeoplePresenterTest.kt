package hu.bme.aut.eventes

import android.net.Uri
import hu.bme.aut.eventes.Interfaces.Contracts.IView.IPeopleContract
import hu.bme.aut.eventes.Model.Data.Friend
import hu.bme.aut.eventes.Model.Data.User
import hu.bme.aut.eventes.Presenter.PeoplePresenter
import hu.bme.aut.eventes.Repository.PeopleRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.doAnswer
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PeoplePresenterTest {
    lateinit var presenter: PeoplePresenter

    @Mock
    lateinit var repo: PeopleRepository
    @Mock
    lateinit var view: IPeopleContract.IView
    @Mock
    lateinit var testUri: Uri
    @Mock
    lateinit var adapter: IPeopleContract.IAdapter

    @Captor
    lateinit var friendArray: ArgumentCaptor<ArrayList<String>>


    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = PeoplePresenter(view,repo)

        doAnswer{x ->
            presenter.updateUsers(User(email = x.arguments[0].toString(), nickname = "test1name"))}.`when`(repo).fillPeople(anyString())
    }

    @Test
    fun testEventPersonListener(){
        val beThere = ArrayList<String>()
        beThere.add("test1")
        beThere.add("test2")
        presenter.eventPersonListener(beThere)
        assert(presenter.people.getPeopleCnt() == 2)
        assert(presenter.people.getPerson(0).email == "test1")
    }

    @Test
    fun testProfileBtnClick(){
        val friends = ArrayList<Friend>()
        friends.add(Friend("test1friend",1))
        friends.add(Friend("test2friend",1))
        presenter.people.addUser(User(friends, "test1name","email1","desc1"))
        presenter.profileBtnClick(0,adapter)

        val chat: ArgumentCaptor<Boolean> = ArgumentCaptor.forClass(Boolean::class.java)
        val groupChat: ArgumentCaptor<Boolean> = ArgumentCaptor.forClass(Boolean::class.java)

        Mockito.verify(adapter).openProfileActivity(chat.capture(),capture(friendArray),groupChat.capture())

        assert(chat.value)
        assert(!groupChat.value)
        assert("email1" == friendArray.value[0])
        assert("test1name" == friendArray.value[1])
        assert("desc1" == friendArray.value[2])
    }

    @Test
    fun testBindViewHolder(){
        val name: ArgumentCaptor<String> = ArgumentCaptor.forClass(String::class.java)
        val uri: ArgumentCaptor<Uri> = ArgumentCaptor.forClass(Uri::class.java)
        doAnswer{ adapter.setProfileBtn(testUri)}.`when`(repo).getProfPic("test1",adapter)
        presenter.people.addUser(User(email = "test1", nickname = "test1name"))
        presenter.bindViewHolder(adapter,0)


        Mockito.verify(adapter).setName(name.capture())
        Mockito.verify(adapter).setProfileBtn(uri.capture())
        assert("test1name" == name.value)
        assert(testUri == uri.value)

    }

    private fun <T> capture(argumentCaptor: ArgumentCaptor<T>): T = argumentCaptor.capture()

}