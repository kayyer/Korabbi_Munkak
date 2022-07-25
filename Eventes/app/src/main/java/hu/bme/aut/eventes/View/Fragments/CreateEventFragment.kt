package hu.bme.aut.eventes.View.Fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import hu.bme.aut.eventes.Interfaces.Contracts.IView.ICreateEventContract
import hu.bme.aut.eventes.Presenter.CreateEventPresenter
import hu.bme.aut.eventes.databinding.CreateeventfragmentBinding


class CreateEventFragment : Fragment(),ICreateEventContract.IView {
    private lateinit var binding: CreateeventfragmentBinding
    private lateinit var  createEventPres: ICreateEventContract.IPresenter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = CreateeventfragmentBinding.inflate(inflater, container, false)
        createEventPres = CreateEventPresenter(this)
        binding.createDate.setOnClickListener{
            createEventPres.initDatePicker()
        }
        binding.createButton.setOnClickListener{
            createEventPres.create(
                binding.createName.text.toString(),
                binding.createDate.text.toString(),
                binding.createDesc.text.toString(),
                binding.createLocation.text.toString()
            )
            }

        return binding.root

    }

    override fun openDatepicker(mYear: Int, mMonth: Int,mDay: Int) {
        val datePickerDialog = DatePickerDialog(requireContext(),
            { view, year, month, dayOfMonth -> binding.createDate.text =
                year.toString() + "-" + String.format("%02d",month + 1) + "-" + String.format("%02d",dayOfMonth) },
            mYear,
            mMonth,
            mDay,
        )
        datePickerDialog.show()
    }
    override fun checkInput() : Boolean {
        var bad: Boolean
        try {
            bad = createEventPres.checkDate(binding.createDate.text.toString())
            if (bad) {
                Toast.makeText(
                    requireActivity(), "Date can't be in the past!",
                    Toast.LENGTH_SHORT
                ).show()
                binding.createDate.error = "The date can't be in the past"
            }
        } catch (e: Exception) {
            binding.createDate.error = "The proper date format is yyyy-mm-dd"
            bad = true
        }

        if (binding.createName.text.toString() == "") {
            binding.createName.error = "The name can't be empty"
            bad = true
        }
        if (binding.createLocation.text.toString() == "") {
            binding.createLocation.error = "The location can't be empty"
            bad = true
        }
        if (binding.createDesc.text.toString() == "") {
            binding.createDesc.error = "The description can't be empty"
            bad = true
        }
        return !bad
    }

    override fun finishCreation() {
        binding.createName.text = null
        binding.createDate.text = null
        binding.createDesc.text = null
        binding.createLocation.text = null
        Toast.makeText(
            requireActivity(), "You created an Event!",
            Toast.LENGTH_SHORT
        ).show()
    }

}