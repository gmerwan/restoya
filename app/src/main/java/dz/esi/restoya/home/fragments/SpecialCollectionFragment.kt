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
import dz.esi.restoya.home.adapters.SpecialCollectionAdapter
import dz.esi.restoya.home.models.SpecialCollection
import dz.esi.restoya.home.models.Restaurant

/**
 * A simple [Fragment] subclass.
 *
 */
class SpecialCollectionFragment : Fragment() {

    private val collections: ArrayList<SpecialCollection> = ArrayList()
    private val restaurants: ArrayList<Restaurant> = ArrayList()
    private val images: ArrayList<Int> = ArrayList()
    private val images1: ArrayList<Int> = ArrayList()
    private val images2: ArrayList<Int> = ArrayList()
    private val images3: ArrayList<Int> = ArrayList()

    companion object {
        /**
         * new instance pattern for fragment
         */
        @JvmStatic
        fun newInstance(): SpecialCollectionFragment {
            val newsFragment = SpecialCollectionFragment()
            val args = Bundle()
            newsFragment.arguments = args
            return newsFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_special_collection, container, false)
        initRestaurants()
        initSpecialCollections()
        val recyclerView = view.findViewById<RecyclerView>(R.id.collection_recyclerView)
        if (isTablet(context)) {
            recyclerView.layoutManager = GridLayoutManager(context, 3)
        } else {
            recyclerView.layoutManager = LinearLayoutManager(context)
        }
        recyclerView.adapter = SpecialCollectionAdapter(collections, requireContext())
        return view
    }

    private fun initRestaurants() {
        images.add(R.drawable.restaurant1)
        images.add(R.drawable.restaurant1)
        images.add(R.drawable.restaurant1)

        images1.add(R.drawable.restaurant2)
        images1.add(R.drawable.restaurant2)
        images1.add(R.drawable.restaurant2)

        images2.add(R.drawable.restaurant3)
        images2.add(R.drawable.restaurant3)
        images2.add(R.drawable.restaurant3)

        images3.add(R.drawable.restaurant4)
        images3.add(R.drawable.restaurant4)
        images3.add(R.drawable.restaurant4)

        restaurants.add(Restaurant(false, "McDonalds", images))
        restaurants.add(Restaurant(false, "KFC", images1))
        restaurants.add(Restaurant(false, "Koul W Thenna", images2))
        restaurants.add(Restaurant(false, "Ramadan", images3))
    }

    private fun initSpecialCollections() {
        collections.add(SpecialCollection("Diabetics Food", R.drawable.diabetics, restaurants))
        collections.add(SpecialCollection("Vegetarian Food", R.drawable.vegetarian, restaurants))
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
