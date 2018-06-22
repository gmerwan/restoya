package dz.esi.restoya.database.daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import dz.esi.restoya.database.entities.Reservation

@Dao
interface ReservationDao {

    @Query("select * from reservation")
    fun getAllReservations(): LiveData<List<Reservation>>

    @Query("select * from reservation where reservation_id = :id")
    fun findReservationById(id: Long): LiveData<Reservation>

    @Insert(onConflict = REPLACE)
    fun insertReservation(reservation: Reservation)

    @Update(onConflict = REPLACE)
    fun updateReservation(reservation: Reservation)

    @Delete
    fun deleteReservation(reservation: Reservation)
}