package hu.bme.aut.eventes

import hu.bme.aut.eventes.Interfaces.Contracts.IView.ICreateEventContract
import hu.bme.aut.eventes.Model.Data.Event
import hu.bme.aut.eventes.Presenter.CreateEventPresenter
import hu.bme.aut.eventes.Repository.CreateEventRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations.initMocks
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CreateEventPresenterTest {
    lateinit var presenter: CreateEventPresenter
    @Mock
    lateinit var view: ICreateEventContract.IView
    @Mock
    lateinit var repo: CreateEventRepository


    @Before
    fun setUp(){
        initMocks(this)
        presenter = CreateEventPresenter(view,repo)

    }
    @Test
    fun testCreateEvent(){
        val event: ArgumentCaptor<Event> = ArgumentCaptor.forClass(Event::class.java)
        `when`(view.checkInput()).thenReturn(true)
        presenter.create("nameTest","dateTest","descTest","locTest")
        verify(repo).createEvent(capture(event))
        verify(view).finishCreation()

        assert("nameTest" == event.value.name)
        assert("dateTest" == event.value.date)
        assert("descTest" == event.value.desc)
        assert("locTest" == event.value.location)

    }
    private fun <T> capture(argumentCaptor: ArgumentCaptor<T>): T = argumentCaptor.capture()


}