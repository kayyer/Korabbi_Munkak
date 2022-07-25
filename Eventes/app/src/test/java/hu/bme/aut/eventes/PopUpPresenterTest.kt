package hu.bme.aut.eventes

import android.content.ContentValues
import android.provider.MediaStore.Images.Media
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import hu.bme.aut.eventes.Interfaces.Contracts.IView.IPopUpContract
import hu.bme.aut.eventes.Presenter.PopUpPresenter
import hu.bme.aut.eventes.Repository.PopUpRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.doAnswer
import org.mockito.MockitoAnnotations.initMocks
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PopUpPresenterTest {
    private lateinit var presenter: PopUpPresenter
    @Mock
    private lateinit var repo: PopUpRepository
    @Mock
    private lateinit var view: IPopUpContract.IView
    @Mock
    private lateinit var values: ContentValues
    @Mock
    private lateinit var auth: FirebaseAuth
    @Mock
    private lateinit var user: FirebaseUser

    @Before
    fun setup(){
        initMocks(this)
        Mockito.`when`(auth.currentUser).thenReturn(user)
        Mockito.`when`(user.email).thenReturn("myEmail")
        Mockito.`when`(repo.auth).thenReturn(auth)

        presenter = PopUpPresenter(view,repo,values)
    }
    @Test
    fun testInitCamera(){
        doAnswer{ x -> assert(x.arguments[1].toString() == "myEmail.jpg")}.`when`(values).put(eq(Media.TITLE),anyString())
        doAnswer{ x -> assert(x.arguments[1].toString() == "Image capture by camera")}.`when`(values).put(eq(Media.DESCRIPTION),anyString())
        presenter.initCamera()

    }
}