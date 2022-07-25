package hu.bme.aut.eventes.View.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import hu.bme.aut.eventes.View.Fragments.EventFragment
import hu.bme.aut.eventes.View.Fragments.ProfileFragment

class EventWOChatAdapter(fm: FragmentManager): FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getItem(position: Int): Fragment = when(position){
            0 -> EventFragment()
            else -> ProfileFragment()
        }

        override fun getCount() : Int = NUM_PAGES

        companion object{
            const val NUM_PAGES = 1
        }
    }
