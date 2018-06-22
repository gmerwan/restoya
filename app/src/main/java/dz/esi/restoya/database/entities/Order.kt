package dz.esi.restoya.database.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "order", foreignKeys = [(ForeignKey(entity = Collection::class,
        parentColumns = arrayOf("dish_id"),
        childColumns = arrayOf("id_dish"),
        onDelete = ForeignKey.CASCADE))])
data class Order (@ColumnInfo(name = "order_address") var address : String,
                  @ColumnInfo(name = "order_id") @PrimaryKey(autoGenerate = true) val id: Long,
                  @ColumnInfo(name = "id_dish")val idDish: Long)