package dz.esi.restoya.database.daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import dz.esi.restoya.database.entities.Dish

@Dao
interface DishDao {

    @Query("select * from dish")
    fun getAllDishes(): LiveData<List<Dish>>

    @Query("select * from dish where dish_id = :id")
    fun findDishById(id: Long): LiveData<Dish>

    @Insert(onConflict = REPLACE)
    fun insertDish(dish: Dish)

    @Update(onConflict = REPLACE)
    fun updateDish(dish: Dish)

    @Delete
    fun deleteDish(dish: Dish)
}