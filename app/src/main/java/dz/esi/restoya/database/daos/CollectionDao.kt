package dz.esi.restoya.database.daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import dz.esi.restoya.database.entities.Collection

@Dao
interface CollectionDao {

    @Query("select * from collection")
    fun getAllCollections(): LiveData<List<Collection>>

    @Query("select * from collection where collection_id = :id")
    fun findCollectionById(id: Long): LiveData<Collection>

    @Insert(onConflict = REPLACE)
    fun insertCollection(collection: Collection)

    @Update(onConflict = REPLACE)
    fun updateCollection(collection: Collection)

    @Delete
    fun deleteCollection(collection: Collection)
}