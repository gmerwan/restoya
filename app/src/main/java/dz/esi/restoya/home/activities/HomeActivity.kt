package dz.esi.restoya.home.activities

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.TabLayout
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import dz.esi.restoya.R
import dz.esi.restoya.about.AboutActivity
import dz.esi.restoya.home.adapters.HomeViewPagerAdapter
import dz.esi.restoya.home.models.Restaurant
import dz.esi.restoya.map.MapsActivity
import dz.esi.restoya.settings.SettingsActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.content_home.*
import org.jetbrains.anko.share
import org.jetbrains.anko.startActivity

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var pagerAdapter: HomeViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            startActivity<MapsActivity>()
        }

        pagerAdapter = HomeViewPagerAdapter(supportFragmentManager)
        home_viewPager.adapter = pagerAdapter

        val tabLayout = findViewById<View>(R.id.tabs) as TabLayout
        tabLayout.setupWithViewPager(home_viewPager)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the Menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home, menu)
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_settings -> {
                startActivity<SettingsActivity>()
            }
            R.id.nav_about -> {
                startActivity<AboutActivity>()
            }
            R.id.nav_share -> {
                share(getString(R.string.share_description), getString(R.string.share_subject))
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}