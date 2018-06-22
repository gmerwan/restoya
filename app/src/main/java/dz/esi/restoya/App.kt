package dz.esi.restoya

import android.app.Application
import dz.esi.restoya.database.AppDatabase
import dz.esi.restoya.database.entities.Restaurant
import org.jetbrains.anko.doAsync
import java.security.Provider

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        doAsync {
            val database = AppDatabase.getInstance(context = this@App)

//            if (database.restaurantDao().getAllRestaurants().isEmpty()) {
//                val restaurants: MutableList<Restaurant> = mutableListOf()
//                for (index: Int in 0..20) {
//                    val restaurant = Restaurant(index, "Name" + index, "Surname" + index)
//                    restaurants.add(index, restaurant)
//                }
//                database.restau rantDao().insertAll(restaurants = restaurants)
//            }
//
//            if (database.providerDao().all.isEmpty()) {
//                val providers: MutableList<Provider> = mutableListOf()
//                for (index: Int in 0..20) {
//                    val provider = Provider(index, "Provider " + index)
//                    providers.add(index, provider)
//                }
//
//                database.providerDao().insertAll(providers = providers)
//            }
        }
    }

}