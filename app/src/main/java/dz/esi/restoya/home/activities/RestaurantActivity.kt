package dz.esi.restoya.home.activities

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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
import android.content.pm.PackageManager



class RestaurantActivity : AppCompatActivity() {

    lateinit var restaurant: Restaurant

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        restaurant = intent.extras.get("restaurant") as Restaurant
        supportActionBar!!.title = restaurant.name

        restaurantImage.setImageResource(restaurant.images[0])

        fab.setOnClickListener {
            startActivity<ReservationActivity>()

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the Menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.restaurant, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_gallery -> {
                startActivity<GalleryActivity>("restaurant" to restaurant)
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

            }
            R.id.email -> {
                email(restaurant.email, "Subject", "I'm email body.")

            }
            R.id.facebook -> {
                browse(getFacebookPageURL(this))

            }
            R.id.twitter -> {
                browse(restaurant.twitter)

            }
            R.id.menu -> {
                startActivity<MenuActivity>("restaurant" to restaurant)

            }
        }
    }

    private fun getFacebookPageURL(context: Context): String {
        val packageManager = context.packageManager
        return try {
            val versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode
            if (versionCode >= 3002850) { //newer versions of fb app
                "fb://facewebmodal/f?href=https://www.facebook.com/icttowers"
            } else {
                "https://www.facebook.com/icttowers"
            }
        } catch (e: PackageManager.NameNotFoundException) {
            "https://www.facebook.com/icttowers" //normal web url
        }

    }
}
