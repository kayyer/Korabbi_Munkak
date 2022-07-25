package hu.bme.aut.eventes

import android.net.Uri
import hu.bme.aut.eventes.Interfaces.Contracts.IView.IMyProfileContract
import hu.bme.aut.eventes.Model.Data.Friend
import hu.bme.aut.eventes.Model.Data.User
import hu.bme.aut.eventes.Presenter.MyProfilePresenter
import hu.bme.aut.eventes.Repository.MyProfileRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.Mockito.doAnswer
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations.initMocks
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MyProfilePresenterTest{
    lateinit var presenter: MyProfilePresenter
    @Mock
    lateinit var repo: MyProfileRepository
    @Mock
    lateinit var view: IMyProfileContract.IView
    @Mock
    lateinit var uri: Uri

    private lateinit var testUser: User


    @Before
    fun setUp(){
        initMocks(this)
        val friends = ArrayList<Friend>()
        friends.add(Friend("friend1",1))
        friends.add(Friend("friend2",2))
        testUser = User(friends,"testName","testEmail","testDesc")
        presenter = MyProfilePresenter(view,repo)
    }

    @Test
    fun testInitProfData(){
        doAnswer{presenter.setUserData(testUser)}.`when`(repo).getUserData()

        val desc: ArgumentCaptor<String> = ArgumentCaptor.forClass(String::class.java)
        val name: ArgumentCaptor<String> = ArgumentCaptor.forClass(String::class.java)
        presenter.initProfData()

        verify(view).setDescription(desc.capture())
        verify(view).setNickname(name.capture())
        assert("testDesc" == desc.value)
        assert("testName" == name.value)

    }
    @Test
    fun testInitProfPic(){
        doAnswer{presenter.setProfPic(uri)}.`when`(repo).getProfPic()
        presenter.initProfPic()
        verify(view).setProfPic(uri)

    }

}