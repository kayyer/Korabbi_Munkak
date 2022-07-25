package hu.bme.aut.eventes

import android.net.Uri
import hu.bme.aut.eventes.Interfaces.Contracts.IView.IEventContract
import hu.bme.aut.eventes.Model.Data.Friend
import hu.bme.aut.eventes.Model.Data.User
import hu.bme.aut.eventes.Presenter.EventPresenter
import hu.bme.aut.eventes.Presenter.PeoplePresenter
import hu.bme.aut.eventes.Repository.EventRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.Mockito.doAnswer
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class EventPresenterTest{
    lateinit var presenter: EventPresenter

    @Mock
    lateinit var repo: EventRepository

    private lateinit var testUser: User
    @Mock
    lateinit var view: IEventContract.IEventView
    @Mock
    lateinit var listPres: PeoplePresenter
    @Mock
    lateinit var uri: Uri

    @Captor
    lateinit var friendArray: ArgumentCaptor<ArrayList<String>>

    @Captor
    lateinit var chat: ArgumentCaptor<Boolean>


    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = EventPresenter(view,repo,listPres)
        val friends = ArrayList<Friend>()
        friends.add(Friend("friend1",1))
        friends.add(Friend("friend2",2))
        testUser = User(friends,"testName", "testEmail","testDesc")

    }

    @Test
    fun testSetupOwner(){
        val name: ArgumentCaptor<String> = ArgumentCaptor.forClass(String::class.java)
        doAnswer{presenter.setProfPicEvent(uri)}.`when`(repo).getProfPic("testEmail")
        doAnswer{presenter.setOwnerInfo(testUser)}.`when`(repo).getOwnerInfo("testEmail")
        presenter.setUpOwner("testEmail")


        verify(view).setText(name.capture())
        assert(testUser.nickname == name.value)
        assert(presenter.eventOwner == testUser)
        verify(view).setProfilePic(uri)


    }
    @Test
    fun testProfileBtnClick(){
        presenter.eventOwner = testUser
        presenter.profileBtnClick()

        verify(view).changeToProfileActivity(capture(friendArray),capture(chat))
        assert(!chat.value)
        assert("testEmail" == friendArray.value!![0])
        assert("testName" == friendArray.value!![1])
        assert("testDesc" == friendArray.value!![2])

    }

    private fun <T> capture(argumentCaptor: ArgumentCaptor<T>): T = argumentCaptor.capture()



}