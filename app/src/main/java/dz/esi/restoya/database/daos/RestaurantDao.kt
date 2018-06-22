package dz.esi.restoya.database.daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import dz.esi.restoya.database.entities.Restaurant

@Dao
interface RestaurantDao {

    @Query("select * from restaurant")
    fun getAllRestaurants(): LiveData<List<Restaurant>>

    @Query("select * from restaurant where restaurant_id = :id")
    fun getRestaurantById(id: Long): LiveData<Restaurant>

    @Query("select * from restaurant where restaurant_name = :name")
    fun getRestaurantByName(name: String): LiveData<Restaurant>

    @Insert(onConflict = REPLACE)
    fun insertRestaurant(restaurant: Restaurant)

    @Update(onConflict = REPLACE)
    fun updateRestaurant(restaurant: Restaurant)

    @Delete
    fun deleteRestaurant(restaurant: Restaurant)
}