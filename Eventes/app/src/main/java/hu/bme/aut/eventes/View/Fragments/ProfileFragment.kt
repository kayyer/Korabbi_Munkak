package hu.bme.aut.eventes.View.Fragments

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import hu.bme.aut.eventes.Interfaces.Contracts.IView.IProfileContract
import hu.bme.aut.eventes.Presenter.ProfilePresenter
import hu.bme.aut.eventes.View.Adapters.MainListAdapter
import hu.bme.aut.eventes.databinding.ProfileFragmentBinding


class ProfileFragment: Fragment(), IProfileContract.IView {
    private lateinit var binding: ProfileFragmentBinding
    private lateinit var profilePres: IProfileContract.IPresenter
    private lateinit var adapter: MainListAdapter
    private lateinit var sharedPref: SharedPreferences
    private lateinit var friendData: ArrayList<String>

    override fun onStart() {
        super.onStart()
        profilePres.autoChangeRating(sharedPref.getFloat(friendData[0] + "Rating", 0.0f))
        binding.reviewers.text = sharedPref.getString(friendData[0] + "Reviewers","0")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View{
        super.onCreate(savedInstanceState)
        binding = ProfileFragmentBinding.inflate(inflater, container, false)

        sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val b = requireActivity().intent.extras
        if(b?.getBoolean("chatAvailable")!!)
            Toast.makeText(context,"Swipe right to chat",Toast.LENGTH_SHORT).show()

        friendData = b.getStringArrayList("user")!!
        profilePres = ProfilePresenter(this)

        profilePres.isChecked()
        binding.nickname.text = friendData[1]
        binding.description.text = friendData[2]

        binding.bffSwitch.isVisible = b.getBoolean("chatAvailable")
        binding.bffSwitchLabel.isVisible = b.getBoolean("chatAvailable")

        binding.rating.setIsIndicator(!b.getBoolean("chatAvailable"))

        profilePres.setProfPic(friendData[0])

        profilePres.onRatingChange(friendData[0],null)
        if(friendData[0] != "missing")
        {
                binding.eventList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                adapter = MainListAdapter((profilePres as ProfilePresenter).listPres)
                binding.eventList.adapter = adapter
                profilePres.getListPres().eventChangeListener(3, friendData[0])
        }

        binding.rating.onRatingBarChangeListener =
            RatingBar.OnRatingBarChangeListener { _, rating, _ -> profilePres.onRatingChange(friendData[0],rating) }



        binding.bffSwitch.setOnCheckedChangeListener{ _, isChecked ->
            if(friendData[0] != "missing") {
                if (isChecked) {
                    profilePres.bffSwitchChecked(friendData[0])
                } else {
                    profilePres.bffSwitchNotChecked(friendData[0])
                }
            }
        }


        return binding.root
    }

    override fun setRating(rating: Float){
        binding.rating.rating = rating
    }
    override fun setReviewers(num: String)
    {
        binding.reviewers.text = num
    }
    override fun newRatingToast(rating: Float){
        Toast.makeText(
            requireActivity().baseContext,
            "You rated " + friendData[0] + " " + rating + " stars!",
            Toast.LENGTH_SHORT
        ).show()
    }
    override fun ratingSwap(rating: Float){
        Toast.makeText(
            requireActivity().baseContext,
            "You rated " + friendData[0] + " " + rating + " stars!",
            Toast.LENGTH_SHORT
        ).show()
    }
    override fun fillUpRatingEditor(ratingAvg: Float, revNum: String){
        val editor = sharedPref.edit()
        editor.putFloat(friendData[0] + "Rating", ratingAvg)
        editor.putString(friendData[0] + "Reviewers", revNum)
        editor.apply()
    }
    override fun fillUpBffEditor(name: String, isBff: Boolean){
        val editor = sharedPref.edit()
        editor.putBoolean(name + "X" + friendData[0], isBff)
        editor.apply()
    }

    override fun updateAdapter(pickAdapter: Int?) {
        adapter.notifyDataSetChanged()
    }
    override fun setProfPic(uri: Uri){
    Picasso.get().load(uri).into(binding.profPic)
    }
    override fun setChecked(name: String){
        binding.bffSwitch.isChecked = sharedPref.getBoolean(name + "X" + friendData[0],false)
    }


}