package dz.esi.restoya.database.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "collection", foreignKeys = [(ForeignKey(entity = Restaurant::class,
        parentColumns = arrayOf("restaurant_id"),
        childColumns = arrayOf("id_restaurant"),
        onDelete = ForeignKey.CASCADE))])
data class Collection (@ColumnInfo(name = "collection_name")var name : String,
                       @ColumnInfo(name = "collection_id") @PrimaryKey(autoGenerate = true) val id: Long,
                       @ColumnInfo(name = "id_restaurant")val idRestaurant: Long)