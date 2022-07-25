package hu.bme.aut.eventes.Interfaces.Contracts.IView

interface ICreateEventContract {
    interface IView{
        fun checkInput(): Boolean
        fun finishCreation()
        fun openDatepicker(mYear: Int, mMonth: Int, mDay: Int)
    }
    interface IPresenter{
        fun create(name: String,date: String, desc: String, location: String )
        fun initDatePicker()
        fun checkDate(dateStr: String): Boolean
    }
}