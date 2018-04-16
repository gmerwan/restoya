package dz.esi.restoya.home.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import dz.esi.restoya.R

/**
 * A simple [Fragment] subclass.
 *
 */
class FeedFragment : Fragment() {

    companion object {
        /**
         * new instance pattern for fragment
         */
        @JvmStatic
        fun newInstance(): FeedFragment {
            val newsFragment = FeedFragment()
            val args = Bundle()
            newsFragment.arguments = args
            return newsFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

}
