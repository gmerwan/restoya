package dz.esi.restoya.home.fragments


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import dz.esi.restoya.R
import dz.esi.restoya.home.adapters.RestaurantAdapter
import dz.esi.restoya.home.models.Restaurant
import kotlinx.android.synthetic.main.fragment_feed.*
import android.util.DisplayMetrics
import android.util.Log


/**
 * A simple [Fragment] subclass.
 *
 */
class FavoriteFragment : Fragment() {

    private val restaurants: ArrayList<Restaurant> = ArrayList()
    private val images: ArrayList<Int> = ArrayList()

    companion object {
        /**
         * new instance pattern for fragment
         */
        @JvmStatic
        fun newInstance(): FavoriteFragment {
            val newsFragment = FavoriteFragment()
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
        if (isTablet(context)) {
            recyclerView.layoutManager = GridLayoutManager(context, 3)
        } else {
            recyclerView.layoutManager = LinearLayoutManager(context)
        }
        recyclerView.adapter = RestaurantAdapter(restaurants, requireContext())
        return view
    }

    private fun initRestaurants() {
        images.add(R.drawable.restaurant)
        restaurants.add(Restaurant(0,true, "McDonalds", "Algiers • Algeria",
                "0699178859", "email@restaurant.dz", "description",
                "URL", "URL",images))
        restaurants.add(Restaurant(0,true, "Koul W Thenna", "Annaba • Algeria",
                "0699178859", "email@restaurant.dz", "description",
                "URL", "URL",images))
    }

    fun isTablet(context : Context?): Boolean {
        try {
            // Compute screen size
            val dm = context!!.resources.displayMetrics
            val screenWidth = dm.widthPixels / dm.xdpi
            val screenHeight = dm.heightPixels / dm.ydpi
            val size = Math.sqrt(Math.pow(screenWidth.toDouble(), 2.0) + Math.pow(screenHeight.toDouble(), 2.0))
            // Tablet devices should have a screen size greater than 6 inches
            return size >= 6
        } catch (t: Throwable) {
            Log.d("TAG", "Failed to compute screen size", t)
            return false
        }

    }
}
