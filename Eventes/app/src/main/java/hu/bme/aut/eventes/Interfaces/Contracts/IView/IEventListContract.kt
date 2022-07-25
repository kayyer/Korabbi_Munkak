package hu.bme.aut.eventes.Interfaces.Contracts.IView

import hu.bme.aut.eventes.Model.Enum.State


interface IEventListContract {
    interface IEventView{
        fun updateAdapter(pickAdapter: Int? = null)

    }
    interface IEventPresenter {
        fun eventChangeListener(category: Int? = null, email: String? = null)

        fun getEventCount() : Int
        fun bindViewHolder(holder: IEventAdapter, position: Int)
        fun subBtnClick(position: Int)
        fun frameClick(position: Int)

    }
    interface IEventAdapter {
        fun setName(name: String?)
        fun setDate(date: String?)
        fun setLocation(location: String?)
        fun setSubBtnImg(state: State)
        fun openEventActivity(Event: ArrayList<String>, BeThere: ArrayList<String>?, GroupChat: Boolean, EventID: String?,Chat: Boolean)
    }
}