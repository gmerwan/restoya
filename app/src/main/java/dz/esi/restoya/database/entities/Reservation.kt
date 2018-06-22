package dz.esi.restoya.database.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "reservation", foreignKeys = [(ForeignKey(entity = Restaurant::class,
        parentColumns = arrayOf("restaurant_id"),
        childColumns = arrayOf("id_restaurant"),
        onDelete = ForeignKey.CASCADE))])
data class Reservation (@ColumnInfo(name = "reservation_number")var number : Int,
                        @ColumnInfo(name = "reservation_date")var date : String,
                        @ColumnInfo(name = "reservation_id") @PrimaryKey(autoGenerate = true) val id: Long,
                        @ColumnInfo(name = "id_restaurant")val idRestaurant: Long)