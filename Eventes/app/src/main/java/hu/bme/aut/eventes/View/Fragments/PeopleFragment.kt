package hu.bme.aut.eventes.View.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import hu.bme.aut.eventes.Interfaces.Contracts.IView.IPeopleContract
import hu.bme.aut.eventes.View.Adapters.PeopleAdapter
import hu.bme.aut.eventes.databinding.PeopleFragmentBinding
import hu.bme.aut.eventes.Presenter.PeoplePresenter


class PeopleFragment: Fragment(),IPeopleContract.IView  {
    private lateinit var binding: PeopleFragmentBinding
    private lateinit var peoplePres: IPeopleContract.IPresenter
    private lateinit var adapter: PeopleAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {
        binding = PeopleFragmentBinding.inflate(inflater, container, false)
        binding.PeopleList.layoutManager = GridLayoutManager(requireActivity(), 5)
        peoplePres = PeoplePresenter(this)
        adapter = PeopleAdapter(peoplePres)
        binding.PeopleList.adapter = adapter

        peoplePres.personListener()

        return binding.root

    }

    override fun updateAdapter() {
        adapter.notifyDataSetChanged()
    }


}