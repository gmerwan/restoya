package dz.esi.restoya.home.models

import android.content.res.Resources
import dz.esi.restoya.R
import java.io.Serializable

data class Restaurant (
        var favorite : Boolean,
        var name : String,
        var images : ArrayList<Int>,
        val latitude : String = "35.6737893",
        val longitude : String = "-0.6568275",
        val address : String = Resources.getSystem().getString(R.string.restau_address),
        val phone : String = Resources.getSystem().getString(R.string.restau_phone_number),
        val email : String = Resources.getSystem().getString(R.string.restau_email),
        val description : String = Resources.getSystem().getString(R.string.description),
        val facebook : String = Resources.getSystem().getString(R.string.restau_facebook),
        val twitter : String = Resources.getSystem().getString(R.string.restau_twitter)
) : Serializable