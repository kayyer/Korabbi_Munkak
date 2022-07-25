package hu.bme.aut.eventes.Presenter

import android.net.Uri
import hu.bme.aut.eventes.Interfaces.Contracts.IView.IEventListContract
import hu.bme.aut.eventes.Interfaces.Contracts.IView.IProfileContract
import hu.bme.aut.eventes.Model.Data.User
import hu.bme.aut.eventes.Repository.ProfileRepository

class ProfilePresenter(
    private val profileView: IProfileContract.IView, val repo: ProfileRepository = ProfileRepository(),
    val listPres: EventListPresenter = EventListPresenter(profileView)): IProfileContract.IPresenter {

    private var autoChange = false

    init{
        repo.setPresenter(this)
    }

    override fun autoChangeRating(rt: Float){
        autoChange = true
        profileView.setRating(rt)
        autoChange = false
    }

    override fun getListPres(): IEventListContract.IEventPresenter {
        return listPres
    }

    override fun setProfPic(name: String) {
       repo.getProfPic(name)
    }

    override fun onRatingChange(friendName: String?,rating: Float?) {

        if (friendName == null || autoChange)
            return
        repo.getRatings(friendName,rating)

    }
    fun setRating(user: User,rating: Float?){
        var userRatings = user.ratings
        if (userRatings == null)
            userRatings = ArrayList()
        val map = mutableMapOf<String, Float>()
        userRatings.associateByTo(map, { it.reviewer!! }, { it.rating!! })
        if(rating != null) {
            if (map[repo.auth.currentUser?.email!!] == null) {
                map.put(repo.auth.currentUser?.email!!, rating)
                profileView.setReviewers(map.size.toString())
                profileView.newRatingToast(rating)
            } else {
                profileView.ratingSwap(rating)
            }
            map[repo.auth.currentUser?.email!!] = rating

            repo.updateRatings(map)
        }
        var sum = 0.0f
        for (rt in map.keys) {
            sum += map[rt]!!
        }
        profileView.fillUpRatingEditor(sum / map.size, map.size.toString())

        autoChangeRating(sum / map.size)
    }


        override fun bffSwitchChecked(friendName: String?) {
            if (friendName == null)
                return
            profileView.fillUpBffEditor(repo.auth.currentUser?.email!!,true)
            repo.addFriend(friendName)


        }
        override fun bffSwitchNotChecked(friendName: String?){
            if(friendName == null)
                return
            profileView.fillUpBffEditor(repo.auth.currentUser?.email!!,false)
            repo.removeFriend(friendName)


        }
        override fun isChecked(){
            profileView.setChecked(repo.auth.currentUser?.email!!)
        }
        fun changeProfPic(uri: Uri){
            profileView.setProfPic(uri)
        }
    }

