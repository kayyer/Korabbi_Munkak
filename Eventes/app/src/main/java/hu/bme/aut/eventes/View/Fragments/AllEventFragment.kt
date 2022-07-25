package hu.bme.aut.eventes.View.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.eventes.Presenter.EventListPresenter
import hu.bme.aut.eventes.Interfaces.Contracts.IView.IEventListContract
import hu.bme.aut.eventes.View.Adapters.MainListAdapter
import hu.bme.aut.eventes.databinding.AlleventFragmentBinding

class AllEventFragment : Fragment(), IEventListContract.IEventView {
        private lateinit var binding: AlleventFragmentBinding
        private lateinit var eventListPres : IEventListContract.IEventPresenter
        private lateinit var adapter: MainListAdapter


        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
            binding = AlleventFragmentBinding.inflate(inflater, container, false)
            binding.AllEventList.layoutManager = LinearLayoutManager(requireActivity())
            eventListPres = EventListPresenter(this)
            adapter = MainListAdapter(eventListPres)
            binding.AllEventList.adapter = adapter
            eventListPres.eventChangeListener()
            return binding.root
        }

    override fun updateAdapter(pickAdapter: Int?){
        adapter.notifyDataSetChanged()
    }

    }
