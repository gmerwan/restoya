package dz.esi.restoya.database.daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import dz.esi.restoya.database.entities.Order

@Dao
interface OrderDao {

    @Query("select * from `order`")
    fun getAllOrders(): LiveData<List<Order>>

    @Query("select * from `order` where order_id = :id")
    fun findOrderById(id: Long): LiveData<Order>

    @Insert(onConflict = REPLACE)
    fun insertOrder(order: Order)

    @Update(onConflict = REPLACE)
    fun updateOrder(order: Order)

    @Delete
    fun deleteOrder(order: Order)
}