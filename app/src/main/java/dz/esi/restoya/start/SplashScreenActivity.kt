package dz.esi.restoya.start

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import dz.esi.restoya.home.activities.HomeActivity
import dz.esi.restoya.R
import org.jetbrains.anko.startActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splach_screen)

        Handler().postDelayed({
            startActivity<HomeActivity>()
            finish()
        }, 1500)
    }

    override fun onStop() {
        super.onStop()
        finish()
    }
}
