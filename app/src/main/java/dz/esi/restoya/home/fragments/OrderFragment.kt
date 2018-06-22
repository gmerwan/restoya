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
import android.util.Log
import dz.esi.restoya.home.adapters.OrderAdapter
import dz.esi.restoya.database.entities.Dish


/**
 * A simple [Fragment] subclass.
 *
 */
class OrderFragment : Fragment() {

    private val dishes: ArrayList<Dish> = ArrayList()

    companion object {
        /**
         * new instance pattern for fragment
         */
        @JvmStatic
        fun newInstance(): OrderFragment {
            val newsFragment = OrderFragment()
            val args = Bundle()
            newsFragment.arguments = args
            return newsFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_feed, container, false)
        initMenus()
        val recyclerView = view.findViewById<RecyclerView>(R.id.feed_recyclerView)
        if (isTablet(context)) {
            recyclerView.layoutManager = GridLayoutManager(context, 2)
        } else {
            recyclerView.layoutManager = LinearLayoutManager(context)
        }
        recyclerView.adapter = OrderAdapter(dishes, requireContext())
        return view
    }

    private fun initMenus() {
        dishes.add(Dish("Lentil Soup", R.drawable.principal))
        dishes.add(Dish("Grilled Beef Salad", R.drawable.principal))
        dishes.add(Dish("Lemon & Mint", R.drawable.principal))
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
