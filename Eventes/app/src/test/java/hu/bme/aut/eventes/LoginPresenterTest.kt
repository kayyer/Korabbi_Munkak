package hu.bme.aut.eventes


import hu.bme.aut.eventes.Interfaces.Contracts.IView.ILoginContract
import hu.bme.aut.eventes.Presenter.LoginPresenter
import hu.bme.aut.eventes.Repository.LoginRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.doAnswer
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations.initMocks
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginPresenterTest {

    @Mock
    private lateinit var presenter: LoginPresenter
    @Mock
    private lateinit var view: ILoginContract.ILoginView
    @Mock
    private lateinit var repo: LoginRepository

    private lateinit var email: String
    private lateinit var password: String

    @Before
    fun setUp() {
        initMocks(this)
        presenter = LoginPresenter(view,repo)

        email = "emailTest"
        password ="passwordTest"
    }

    @Test
    fun logInSuccess_test() {
        doAnswer{presenter.loginSuccess()}.`when`(repo).login(email,password)

        presenter.login(email, password)

        verify(view).startMainActivity()
    }

    @Test
    fun logInFailure_test() {
        doAnswer{presenter.loginFailure()}.`when`(repo).login(email,password)

        presenter.login(email, password)

        verify(view).showToast(anyString())

    }
}