package dz.esi.restoya.home.activities

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import dz.esi.restoya.R
import dz.esi.restoya.home.models.Restaurant
import dz.esi.restoya.map.MapsActivity
import kotlinx.android.synthetic.main.activity_restaurant.*
import org.jetbrains.anko.browse
import org.jetbrains.anko.email
import org.jetbrains.anko.makeCall
import org.jetbrains.anko.startActivity

class RestaurantActivity : AppCompatActivity() {

    lateinit var restaurant: Restaurant

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant)
        setSupportActionBar(toolbar)

        val restaurant = intent.extras.get("restaurant") as Restaurant
        supportActionBar!!.title = restaurant.name

        val appBarLayout: AppBarLayout = findViewById(R.id.app_bar)
        val displayWidth = resources.displayMetrics.widthPixels
        val params = appBarLayout.layoutParams
        params.height = displayWidth
        params.width = displayWidth
        appBarLayout.layoutParams = params

        fab.setOnClickListener { view ->
            startActivity<ReservationActivity>()
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the Menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.gallery, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_favorite -> {
                startActivity<FavoriteActivity>()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun onClickMethod(view: View) {
        when (view.id) {
            R.id.location -> {
                startActivity<MapsActivity>()

            }
            R.id.phone -> {
                makeCall(restaurant.phone)
                Log.d("error", restaurant.phone)

            }
            R.id.email -> {
                email(restaurant.email, "Subject", "I'm email body.")

            }
            R.id.facebook -> {
                browse(restaurant.facebook)

            }
            R.id.twitter -> {
                browse(restaurant.twitter)

            }
            R.id.menu -> {
                startActivity<FavoriteActivity>()

            }
        }
    }
}
