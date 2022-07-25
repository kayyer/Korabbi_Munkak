package hu.bme.aut.eventes

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.QuerySnapshot
import hu.bme.aut.eventes.Interfaces.Contracts.IView.IEventListContract
import hu.bme.aut.eventes.Model.Data.Event
import hu.bme.aut.eventes.Model.Enum.State
import hu.bme.aut.eventes.Presenter.EventListPresenter
import hu.bme.aut.eventes.Repository.EventListRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.Mockito.doAnswer
import org.mockito.MockitoAnnotations.initMocks
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class EventListPresenterTest{
    lateinit var presenter: EventListPresenter
    private lateinit  var extraEvent: Event
    private lateinit var dcList: List<DocumentChange>

    @Mock
    lateinit var auth: FirebaseAuth
    @Mock
    lateinit var QS: QuerySnapshot
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    lateinit var dc1: DocumentChange
    @Mock
    lateinit var user: FirebaseUser
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    lateinit var repo: EventListRepository
    @Spy
    lateinit var view : IEventListContract.IEventView
    @Mock
    lateinit var adapter: IEventListContract.IEventAdapter
    @Captor
    lateinit var friendArray: ArgumentCaptor<ArrayList<String>>
    @Captor
    lateinit var eventArray: ArgumentCaptor<ArrayList<String>>

    @Before
    fun setUP(){
        initMocks(this)
        Mockito.`when`(auth.currentUser).thenReturn(user)
        Mockito.`when`(user.email).thenReturn("myEmail")
        Mockito.`when`(repo.auth).thenReturn(auth)
        Mockito.`when`(repo.currentUser).thenReturn("myEmail")
        val beThere = ArrayList<String>()
        beThere.add("beThere1")
        beThere.add("beThere2")
        dcList = listOf(dc1)
        Mockito.`when`(QS.documentChanges).thenReturn(dcList)
        Mockito.`when`(dcList[0].type).thenReturn(DocumentChange.Type.ADDED)
        Mockito.`when`(dcList[0].document.toObject(Event::class.java)).thenReturn(Event("testId1","testOwner","name1","date1","desc1","loc1",beThere))

        presenter = EventListPresenter(view,repo)

        presenter.onDataChange(QS)

        val beThereChat = ArrayList<String>()
        extraEvent = Event("testId1","testOwner","name1","date1","desc1","loc1",beThereChat)

    }

    @Test
    fun testOnDataChangeAdd(){
        Mockito.`when`(dcList[0].type).thenReturn(DocumentChange.Type.ADDED)
        Mockito.`when`(dcList[0].document.toObject(Event::class.java)).thenReturn(Event("testId2"))
        doAnswer{ presenter.onDataChange(QS)}.`when`(repo).allEventChange()
        presenter.eventChangeListener()
        assert(presenter.events.getEvent(0).id == "testId1")
        assert(presenter.events.getEvent(1).id == "testId2")
        assert(presenter.events.getEventCnt() == 2)
    }
   @Test

    fun testOnDataChangeRemove(){

        Mockito.`when`(dcList[0].type).thenReturn(DocumentChange.Type.REMOVED)
        Mockito.`when`(dcList[0].document.toObject(Event::class.java)).thenReturn(Event("testId1"))
        doAnswer{ presenter.onDataChange(QS)}.`when`(repo).myEventChange()
        presenter.eventChangeListener(1)

        assert(presenter.events.getEventCnt() == 0)
    }
    @Test
    fun testOnDataChangeModified(){
        assert(presenter.events.getEvent(0).owner == "testOwner")
        Mockito.`when`(dcList[0].type).thenReturn(DocumentChange.Type.MODIFIED)
        Mockito.`when`(dcList[0].document.toObject(Event::class.java)).thenReturn(Event("testId1","changedOwner"))
        doAnswer{ presenter.onDataChange(QS)}.`when`(repo).othersEventChange("testEmail")
        presenter.eventChangeListener(3,"testEmail")
        presenter.onDataChange(QS)

        assert(presenter.events.getEvent(0).owner == "changedOwner")
    }

    @Test
    fun testOnDataChangeAllAdd(){
        Mockito.`when`(dcList[0].type).thenReturn(DocumentChange.Type.MODIFIED)
        Mockito.`when`(dcList[0].document.toObject(Event::class.java)).thenReturn(Event("testId2","changedOwner"))
        doAnswer{ presenter.onDataChange(QS)}.`when`(repo).allEventChange()
        presenter.eventChangeListener()
        presenter.onDataChange(QS)

        assert(presenter.events.getEventCnt() == 2)
        assert(presenter.events.getEvent(1).owner == "changedOwner")
    }
    @Test
    fun testOnDataChangeAllRemove(){
        presenter.events.addEvent(Event("testId2"))
        val beT = ArrayList<String>()
        beT.add("myEmail")
        Mockito.`when`(dcList[0].document.toObject(Event::class.java)).thenReturn(Event("testId2",beThere = beT))
        doAnswer{ presenter.onDataChange(QS)}.`when`(repo).allEventChange()
        presenter.eventChangeListener()
        presenter.onDataChange(QS)

        assert(presenter.events.getEventCnt() == 1)
    }

    @Test
    fun subButtonOwnEvent(){
        extraEvent.owner = "myEmail"
        presenter.events.addEvent(extraEvent)
        presenter.subBtnClick(1)
        Mockito.verify(repo).deleteMyEvent(extraEvent)
    }
    @Test
    fun subButtonBeThereEvent(){
        extraEvent.beThere?.add("myEmail")
        presenter.events.addEvent(extraEvent)
        presenter.subBtnClick(1)
        Mockito.verify(repo).unsubFromEvent(extraEvent)
    }
    @Test
    fun subButtonRandomEvent(){
        presenter.subBtnClick(0)
        Mockito.verify(repo).subToEvent(presenter.events.getEvent(0))
    }
    @Test
    fun testBindViewHolder(){
        presenter.bindViewHolder(adapter,0)
        val date: ArgumentCaptor<String> = ArgumentCaptor.forClass(String::class.java)
        val name: ArgumentCaptor<String> = ArgumentCaptor.forClass(String::class.java)
        val loc: ArgumentCaptor<String> = ArgumentCaptor.forClass(String::class.java)
        val posOrMin: ArgumentCaptor<State> = ArgumentCaptor.forClass(State::class.java)


        Mockito.verify(adapter).setDate(date.capture())
        Mockito.verify(adapter).setName(name.capture())
        Mockito.verify(adapter).setLocation(loc.capture())
        Mockito.verify(adapter).setSubBtnImg(capture(posOrMin))

        assert("date1" == date.value)
        assert("name1" == name.value)
        assert("loc1" == loc.value)
        assert(posOrMin.value ==  State.SUBSCRIBE)




    }
    @Test
    fun subBtnImgMinus(){
        val posOrMin: ArgumentCaptor<State> = ArgumentCaptor.forClass(State::class.java)
        presenter.events.getEvent(0).owner = "myEmail"
        presenter.bindViewHolder(adapter,0)
        Mockito.verify(adapter).setSubBtnImg(capture(posOrMin))
        assert(posOrMin.value == State.DELETE)
    }
    @Test
    fun testFrameClick(){
        val groupChat: ArgumentCaptor<Boolean> = ArgumentCaptor.forClass(Boolean::class.java)
        val eventID: ArgumentCaptor<String> = ArgumentCaptor.forClass(String::class.java)
        val chat: ArgumentCaptor<Boolean> = ArgumentCaptor.forClass(Boolean::class.java)

        extraEvent.beThere?.add("myEmail")
        presenter.events.addEvent(extraEvent)
        presenter.bindViewHolder(adapter,1)
        presenter.frameClick(1)

        val testEvent = ArrayList<String?>()
        testEvent.add(extraEvent.owner)
        testEvent.add(extraEvent.name)
        testEvent.add(extraEvent.date)
        testEvent.add(extraEvent.desc)
        testEvent.add(extraEvent.location)

        Mockito.verify(adapter).openEventActivity(capture(eventArray),capture(friendArray), groupChat.capture(),eventID.capture(),chat.capture())

        assert(testEvent == eventArray.value)
        assert(extraEvent.beThere == friendArray.value)
        assert(groupChat.value)
        assert(extraEvent.id == eventID.value)
        assert(chat.value)
        }

    private fun <T> capture(argumentCaptor: ArgumentCaptor<T>): T = argumentCaptor.capture()

}