package dz.esi.restoya.home.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import dz.esi.restoya.home.fragments.SpecialCollectionFragment
import dz.esi.restoya.home.fragments.FeedFragment


class HomeViewPagerAdapter (fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {

        return if (position == 0) {
            FeedFragment.newInstance()
        } else {
            SpecialCollectionFragment.newInstance()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        // return null to show no title.
        return if (position == 0) {
            "Restaurants"
        } else {
            "Collections"
        }

    }
}