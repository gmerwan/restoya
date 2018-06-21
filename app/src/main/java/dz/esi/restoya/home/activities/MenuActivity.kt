package dz.esi.restoya.home.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import dz.esi.restoya.R
import dz.esi.restoya.home.expandable.CollectionView
import dz.esi.restoya.home.expandable.DishView
import dz.esi.restoya.home.models.*
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.content_menu.*
import org.jetbrains.anko.startActivity


class MenuActivity : AppCompatActivity() {

    lateinit var restaurant: Restaurant

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        restaurant = intent.extras.get("restaurant") as Restaurant
        supportActionBar!!.title = restaurant.name

        restaurantImage.setImageResource(restaurant.images[0])

        expandableView.addView(CollectionView(this, Collection("Soups")))
        expandableView.addView(DishView(this, Dish("Cream Of Broccoli", R.drawable.principal)))
        expandableView.addView(DishView(this, Dish("Lentil Soup", R.drawable.principal)))
        expandableView.addView(CollectionView(this, Collection("Salads")))
        expandableView.addView(DishView(this, Dish("Spinach Salad", R.drawable.principal)))
        expandableView.addView(DishView(this, Dish("Grilled Beef Salad", R.drawable.principal)))
        expandableView.addView(CollectionView(this, Collection("Sandwiches")))
        expandableView.addView(DishView(this, Dish("Grilled Chicken", R.drawable.principal)))
        expandableView.addView(DishView(this, Dish("Smoked Turkey", R.drawable.principal)))
        expandableView.addView(CollectionView(this, Collection("Burgers")))
        expandableView.addView(DishView(this, Dish("Cheese Burger", R.drawable.principal)))
        expandableView.addView(DishView(this, Dish("Mexican Burger", R.drawable.principal)))
        expandableView.addView(CollectionView(this, Collection("Pasta")))
        expandableView.addView(CollectionView(this, Collection("Pizza")))
        expandableView.addView(DishView(this, Dish("Pepperoni Pizza", R.drawable.principal)))
        expandableView.addView(DishView(this, Dish("Mushroom Pizza", R.drawable.principal)))
        expandableView.addView(DishView(this, Dish("Margarita Pizza", R.drawable.principal)))
        expandableView.addView(CollectionView(this, Collection("Deserts")))
        expandableView.addView(DishView(this, Dish("Carrot Cake", R.drawable.principal)))
        expandableView.addView(DishView(this, Dish("Brownies", R.drawable.principal)))
        expandableView.addView(DishView(this, Dish("Chocolate Cake", R.drawable.principal)))
        expandableView.addView(CollectionView(this, Collection("Fresh Juices")))
        expandableView.addView(DishView(this, Dish("Orange Juice", R.drawable.principal)))
        expandableView.addView(DishView(this, Dish("Kiwi", R.drawable.principal)))
        expandableView.addView(DishView(this, Dish("Lemon & Mint", R.drawable.principal)))
        expandableView.addView(CollectionView(this, Collection("Sodas")))
        expandableView.addView(DishView(this, Dish("Pepsi", R.drawable.principal)))
        expandableView.addView(DishView(this, Dish("Diet 7-UP", R.drawable.principal)))
        expandableView.addView(DishView(this, Dish("Miranda", R.drawable.principal)))
        expandableView.addView(DishView(this, Dish("Water", R.drawable.principal)))

    }

    override fun onCreateOptionsMenu(menu: android.view.Menu): Boolean {
        // Inflate the Menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.order, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            R.id.action_order -> {
                startActivity<OrderActivity>("restaurant" to restaurant)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
