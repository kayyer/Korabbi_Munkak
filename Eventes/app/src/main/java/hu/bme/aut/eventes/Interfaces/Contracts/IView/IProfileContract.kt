package hu.bme.aut.eventes.Interfaces.Contracts.IView

import android.net.Uri

interface IProfileContract {
    interface IView : IEventListContract.IEventView{
        fun setRating(rating: Float)
        fun setReviewers(num: String)
        fun newRatingToast(rating: Float)
        fun ratingSwap(rating: Float)
        fun fillUpRatingEditor(ratingAvg: Float, revNum: String)
        fun fillUpBffEditor(name: String, isBff: Boolean)
        fun setProfPic(uri: Uri)
        fun setChecked(name: String)
    }
    interface IPresenter{
        fun bffSwitchChecked(friendName: String?)
        fun bffSwitchNotChecked(friendName: String?)
        fun isChecked()
        fun onRatingChange(friendName: String?, rating: Float?)
        fun setProfPic(name: String)
        fun autoChangeRating(rt: Float)
        fun getListPres(): IEventListContract.IEventPresenter

    }

}