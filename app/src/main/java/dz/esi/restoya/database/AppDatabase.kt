package dz.esi.restoya.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import dz.esi.restoya.database.daos.CollectionDao
import dz.esi.restoya.database.daos.DishDao
import dz.esi.restoya.database.daos.OrderDao
import dz.esi.restoya.database.daos.RestaurantDao
import dz.esi.restoya.database.daos.ReservationDao
import dz.esi.restoya.database.entities.Collection
import dz.esi.restoya.database.entities.Dish
import dz.esi.restoya.database.entities.Restaurant

@Database(entities = [(Restaurant::class), (Collection::class), (Dish::class)], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun restaurantDao(): RestaurantDao

    abstract fun collectionDao(): CollectionDao

    abstract fun dishDao(): DishDao

    abstract fun orderDao(): OrderDao

    abstract fun reservationDao(): ReservationDao

    companion object {

        /**
         * The only instance
         */
        private var sInstance: AppDatabase? = null
        private const val DB_NAME = "database.db"

        /**
         * Gets the singleton instance of SampleDatabase.
         *
         * @param context The context.
         * @return The singleton instance of SampleDatabase.
         */
        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (sInstance == null) {
                sInstance = Room
                        .databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
            }
            return sInstance!!
        }
    }

}