package dz.esi.restoya.home.activities

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import dz.esi.restoya.R
import dz.esi.restoya.home.models.Restaurant
import kotlinx.android.synthetic.main.activity_restaurant.*
import org.jetbrains.anko.startActivity

class RestaurantActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant)
        setSupportActionBar(toolbar)

        val restaurant = intent.extras.get("restaurant") as Restaurant
        toolbar.title = restaurant.name

        val appBarLayout: AppBarLayout = this.findViewById(R.id.app_bar)
        val displayWidth = resources.displayMetrics.widthPixels
        val params = appBarLayout.layoutParams
        params.height = displayWidth
        params.width = displayWidth
        appBarLayout.layoutParams = params

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the Menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.collections, menu)
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
}
