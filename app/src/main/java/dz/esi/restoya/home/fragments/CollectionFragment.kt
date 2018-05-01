package dz.esi.restoya.home.fragments


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import dz.esi.restoya.R
import dz.esi.restoya.home.adapters.CollectionAdapter
import dz.esi.restoya.home.models.Collection
import dz.esi.restoya.home.models.Restaurant

/**
 * A simple [Fragment] subclass.
 *
 */
class CollectionFragment : Fragment() {

    private val collections: ArrayList<Collection> = ArrayList()
    private val restaurants: ArrayList<Restaurant> = ArrayList()
    private val images: ArrayList<Int> = ArrayList()

    companion object {
        /**
         * new instance pattern for fragment
         */
        @JvmStatic
        fun newInstance(): CollectionFragment {
            val newsFragment = CollectionFragment()
            val args = Bundle()
            newsFragment.arguments = args
            return newsFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_collection, container, false)
        initRestaurants()
        initCollections()
        val recyclerView = view.findViewById<RecyclerView>(R.id.collection_recyclerView)
        if (isTablet(context)) {
            recyclerView.layoutManager = GridLayoutManager(context, 3)
        } else {
            recyclerView.layoutManager = LinearLayoutManager(context)
        }
        recyclerView.adapter = CollectionAdapter(collections, requireContext())
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

    private fun initCollections() {
        collections.add(Collection(0,"Diabetics Food", R.drawable.diabetics, restaurants))
        collections.add(Collection(1,"Vegetarian Food", R.drawable.vegetarian, restaurants))
    }

    private fun isTablet(context : Context?): Boolean {
        return try {
            // Compute screen size
            val dm = context!!.resources.displayMetrics
            val screenWidth = dm.widthPixels / dm.xdpi
            val screenHeight = dm.heightPixels / dm.ydpi
            val size = Math.sqrt(Math.pow(screenWidth.toDouble(), 2.0) + Math.pow(screenHeight.toDouble(), 2.0))
            // Tablet devices should have a screen size greater than 6 inches
            size >= 6
        } catch (t: Throwable) {
            Log.d("TAG", "Failed to compute screen size", t)
            false
        }

    }
}
