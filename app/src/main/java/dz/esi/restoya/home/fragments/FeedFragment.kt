package dz.esi.restoya.home.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import dz.esi.restoya.R
import dz.esi.restoya.home.adapters.RestaurantAdapter
import dz.esi.restoya.home.models.Restaurant
import kotlinx.android.synthetic.main.fragment_feed.*

/**
 * A simple [Fragment] subclass.
 *
 */
class FeedFragment : Fragment() {

    private val restaurants: ArrayList<Restaurant> = ArrayList()
    private val images: ArrayList<Int> = ArrayList()

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
        val view = inflater.inflate(R.layout.fragment_feed, container, false)
        initRestaurants()
        val recyclerView = view.findViewById<RecyclerView>(R.id.feed_recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        //feed_recyclerView.layoutManager = GridLayoutManager(context)
        recyclerView.adapter = RestaurantAdapter(restaurants, requireContext())
        return view
    }

    private fun initRestaurants() {
        images.add(R.drawable.restaurant)
        restaurants.add(Restaurant(0,false, "McDonalds", "Algiers • Algeria",
                "0699178859", "email@restaurant.dz", "description",
                "URL", "URL",images))
        restaurants.add(Restaurant(0,false, "KFC", "Oran • Algeria",
                "0699178859", "email@restaurant.dz", "description",
                "URL", "URL",images))
        restaurants.add(Restaurant(0,false, "Koul W Thenna", "Annaba • Algeria",
                "0699178859", "email@restaurant.dz", "description",
                "URL", "URL",images))
        restaurants.add(Restaurant(0,false, "Ramadan", "Rome • Italy",
                "0699178859", "email@restaurant.dz", "description",
                "URL", "URL",images))
    }
}
