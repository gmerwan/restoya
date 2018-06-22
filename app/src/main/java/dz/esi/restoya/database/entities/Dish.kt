package dz.esi.restoya.database.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "dish", foreignKeys = [(ForeignKey(entity = Collection::class,
        parentColumns = arrayOf("collection_id"),
        childColumns = arrayOf("id_collection"),
        onDelete = ForeignKey.CASCADE))])
data class Dish (@ColumnInfo(name = "dish_name") var name : String,
                 @ColumnInfo(name = "dish_image") var image : Int,
                 @ColumnInfo(name = "dish_id") @PrimaryKey(autoGenerate = true) val id: Long,
                 @ColumnInfo(name = "id_collection")val idCollection: Long)