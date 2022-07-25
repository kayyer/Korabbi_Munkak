package hu.bme.aut.eventes.View.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.eventes.Interfaces.Contracts.IView.IEventListContract
import hu.bme.aut.eventes.View.Adapters.MainListAdapter
import hu.bme.aut.eventes.Presenter.EventListPresenter
import hu.bme.aut.eventes.databinding.MyeventFragmentBinding

class MyEventFragment : Fragment(), IEventListContract.IEventView {
    private lateinit var binding: MyeventFragmentBinding
    private lateinit var adapterCreated: MainListAdapter
    private lateinit var adapterLiked: MainListAdapter
    private lateinit var eventCreatedPres: IEventListContract.IEventPresenter
    private lateinit var eventLikedPres: IEventListContract.IEventPresenter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = MyeventFragmentBinding.inflate(inflater, container, false)


        eventCreatedPres = EventListPresenter(this)
        adapterCreated = MainListAdapter(eventCreatedPres)
        binding.OwnEventList.adapter = adapterCreated
        binding.OwnEventList.layoutManager = LinearLayoutManager(requireActivity())

        eventCreatedPres.eventChangeListener(1)

        eventLikedPres = EventListPresenter(this)
        adapterLiked = MainListAdapter(eventLikedPres)
        binding.IBeThereEventList.adapter = adapterLiked
        binding.IBeThereEventList.layoutManager = LinearLayoutManager(requireActivity())

        eventLikedPres.eventChangeListener(2)


        return binding.root

    }

    override fun updateAdapter(pickAdapter: Int?) {
        if(pickAdapter == 1)
            adapterCreated.notifyDataSetChanged()
        else if(pickAdapter == 2)
            adapterLiked.notifyDataSetChanged()
    }


}