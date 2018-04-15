package dz.esi.resto.start

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import dz.esi.resto.home.activities.HomeActivity
import dz.esi.resto.R
import org.jetbrains.anko.startActivity

class SplachScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splach_screen)

        Handler().postDelayed({
            startActivity<HomeActivity>()
        }, 3000)
    }
}
