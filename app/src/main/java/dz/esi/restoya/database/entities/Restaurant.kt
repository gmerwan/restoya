package dz.esi.restoya.database.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.PrimaryKey
import android.content.res.Resources
import dz.esi.restoya.R
import java.io.Serializable

@Entity(tableName = "restaurant")
data class Restaurant (
        @ColumnInfo(name = "restaurant_favorite")var favorite : Boolean,
        @ColumnInfo(name = "restaurant_name")var name : String,
        @ColumnInfo(name = "restaurant_images")var images : ArrayList<Int>,
        @ColumnInfo(name = "restaurant_latitude")val latitude : String = "35.6737893",
        @ColumnInfo(name = "restaurant_longitude")val longitude : String = "-0.6568275",
        @ColumnInfo(name = "restaurant_address")val address : String = Resources.getSystem().getString(R.string.restau_address),
        @ColumnInfo(name = "restaurant_phone")val phone : String = Resources.getSystem().getString(R.string.restau_phone_number),
        @ColumnInfo(name = "restaurant_email")val email : String = Resources.getSystem().getString(R.string.restau_email),
        @ColumnInfo(name = "restaurant_description")val description : String = Resources.getSystem().getString(R.string.description),
        @ColumnInfo(name = "restaurant_facebook")val facebook : String = Resources.getSystem().getString(R.string.restau_facebook),
        @ColumnInfo(name = "restaurant_twitter")val twitter : String = Resources.getSystem().getString(R.string.restau_twitter),
        @ColumnInfo(name = "restaurant_id") @PrimaryKey(autoGenerate = true) val id: Long
) : Serializable