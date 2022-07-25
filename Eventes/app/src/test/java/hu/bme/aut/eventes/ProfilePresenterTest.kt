package hu.bme.aut.eventes

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import hu.bme.aut.eventes.Interfaces.Contracts.IView.IProfileContract
import hu.bme.aut.eventes.Model.Data.Friend
import hu.bme.aut.eventes.Model.Data.FriendRating
import hu.bme.aut.eventes.Model.Data.User
import hu.bme.aut.eventes.Presenter.EventListPresenter
import hu.bme.aut.eventes.Presenter.ProfilePresenter
import hu.bme.aut.eventes.Repository.ProfileRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyFloat
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations.initMocks
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ProfilePresenterTest {
    private lateinit var presenter: ProfilePresenter
    @Mock
    private lateinit var view: IProfileContract.IView
    @Mock
    private lateinit var listPres: EventListPresenter
    @Mock
    private lateinit var repo: ProfileRepository
    @Mock
    private lateinit var uri: Uri
    @Mock
    private lateinit var auth: FirebaseAuth
    @Mock
    private lateinit var user: FirebaseUser

    private lateinit var testUser: User

    @Before
    fun setUp(){
        initMocks(this)
        `when`(auth.currentUser).thenReturn(user)
        `when`(user.email).thenReturn("myEmail")
        `when`(repo.auth).thenReturn(auth)

        presenter = ProfilePresenter(view,repo,listPres)

        val fa = ArrayList<Friend>()
        fa.add(Friend("testFriend",1))
        testUser = User(fa,"testName","testEmail","testDesc",ArrayList())
    }

    @Test
    fun testAutoChangeRating(){
        doAnswer{ presenter.onRatingChange("name",3.0f)}.`when`(view).setRating(3.0f)
        presenter.autoChangeRating(3.0f)
        verify(repo,never()).getRatings(anyString(),anyFloat())
    }

    @Test
    fun testSetProfPic(){
        doAnswer{presenter.changeProfPic(uri)}.`when`(repo).getProfPic("nameTest")

        presenter.setProfPic("nameTest")

        verify(view).setProfPic(uri)
    }

    @Test
    fun testOnRatingChangeNew(){
        testUser.ratings?.add(FriendRating("testReviewer",1.0f))
        doAnswer{ presenter.setRating(testUser,5.0f)}.`when`(repo).getRatings("emailTest",5.0f)

        presenter.onRatingChange("emailTest",5.0f)

        verify(view).fillUpRatingEditor(3.0f,(2).toString())
        verify(view,times(1)).setRating(anyFloat())
        verify(view).newRatingToast(5.0f)

    }

    @Test
    fun tesOnRatingChangeOld() {
        testUser.ratings?.add(FriendRating("myEmail", 1.0f))
        doAnswer { presenter.setRating(testUser, 5.0f) }.`when`(repo).getRatings("emailTest", 5.0f)

        presenter.onRatingChange("emailTest", 5.0f)

        verify(view).fillUpRatingEditor(5.0f, (1).toString())
        verify(view,times(1)).setRating(anyFloat())
        verify(view).ratingSwap(5.0f)
    }

}