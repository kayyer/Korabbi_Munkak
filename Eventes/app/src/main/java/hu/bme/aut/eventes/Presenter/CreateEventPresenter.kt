package hu.bme.aut.eventes.Presenter

import hu.bme.aut.eventes.Interfaces.Contracts.IView.ICreateEventContract
import hu.bme.aut.eventes.Model.Data.Event
import hu.bme.aut.eventes.Repository.CreateEventRepository
import java.text.ParseException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class CreateEventPresenter(val createEventView: ICreateEventContract.IView,val repo: CreateEventRepository = CreateEventRepository()): ICreateEventContract.IPresenter {
    init{
        repo.setPresenter(this)
    }
    override fun create(name: String, date: String, desc: String, location: String) {
        if (createEventView.checkInput()) {
            val uniqueID = UUID.randomUUID().toString()
            val newEvent = Event(id = uniqueID, name = name, date = date, desc = desc, location = location)
            repo.createEvent(newEvent)
            createEventView.finishCreation()
        }
    }

    override fun initDatePicker() {
        val calendar = Calendar.getInstance()
        createEventView.openDatepicker(calendar[Calendar.YEAR],calendar[Calendar.MONTH],calendar[Calendar.DAY_OF_MONTH])
    }

    @Throws(ParseException::class)
    override fun checkDate(dateStr: String): Boolean {
            val date = LocalDate.parse(dateStr, DateTimeFormatter.ISO_DATE)
            return date.isBefore(LocalDate.now())
        }

}