package hu.bme.aut.eventes


import hu.bme.aut.eventes.Interfaces.Contracts.IView.IRegisterContract
import hu.bme.aut.eventes.Presenter.RegisterPresenter
import hu.bme.aut.eventes.Repository.RegisterRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RegisterPresenterTest{

    private lateinit var presenter: RegisterPresenter

    @Mock
    private lateinit var view: IRegisterContract.IRegisterView
    @Mock
    private lateinit var repo: RegisterRepository


    private var isUserInputCorrect = true
    private val email = "Test email"
    private val password = "Test password"


    @Before
    fun setUP(){
        MockitoAnnotations.initMocks(this)
        presenter = RegisterPresenter(view,repo)
    }
    @Test
    fun testUserCreationSuccess(){
        doAnswer { presenter.successfulCreation() }.`when`(repo).createUser(email, password)
        `when`(view.checkInputs()).thenReturn(true)

        presenter.create(email, password)

        verify(view).startMainActivity()

    }

    @Test
    fun testUserCreationFailure(){
        doAnswer { presenter.unsuccessfulCreation() }.`when`(repo).createUser(email, password)
        `when`(view.checkInputs()).thenReturn(true)

        presenter.create(email, password)

        verify(view).showToast(anyString())
    }
    @Test
    fun testUserWrongInput(){
        isUserInputCorrect = false
        presenter.create(email, password)
        verify(repo,never()).createUser(email,password)

    }

}