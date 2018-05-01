package dz.esi.restoya.map

import android.os.AsyncTask
import android.util.Log
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.GoogleMap



class GetNearbyPlacesData : AsyncTask<Any, String, String>() {

    private var googlePlacesData: String = ""
    private lateinit var mMap: GoogleMap
    private lateinit var url: String

    override fun doInBackground(vararg params: Any?): String {
        try {
            Log.d("GetNearbyPlacesData", "doInBackground entered")
            if(params[0] is GoogleMap)
                mMap = params[0] as GoogleMap
            if(params[1] is String)
                url = params[1] as String
            val downloadUrl = DownloadUrl()
            googlePlacesData = downloadUrl.readUrl(url)
            Log.d("GooglePlacesReadTask", "doInBackground Exit")
        } catch (e: Exception) {
            Log.d("GooglePlacesReadTask", e.toString())
        }

        return googlePlacesData
    }

    override fun onPostExecute(result: String) {
        Log.d("GooglePlacesReadTask", "onPostExecute Entered")
        val nearbyPlacesList: List<HashMap<String, String>>?
        val dataParser = DataParser()
        nearbyPlacesList = dataParser.parse(result)
        showNearbyPlaces(nearbyPlacesList)
        Log.d("GooglePlacesReadTask", "onPostExecute Exit")
    }

    private fun showNearbyPlaces(nearbyPlacesList: List<HashMap<String, String>>) {
        for (i in nearbyPlacesList.indices) {
            Log.d("onPostExecute", "Entered into showing locations")
            val markerOptions = MarkerOptions()
            val googlePlace = nearbyPlacesList[i]
            val lat = java.lang.Double.parseDouble(googlePlace["lat"])
            val lng = java.lang.Double.parseDouble(googlePlace["lng"])
            val placeName = googlePlace["place_name"]
            val vicinity = googlePlace["vicinity"]
            val latLng = LatLng(lat, lng)
            markerOptions.position(latLng)
            markerOptions.title("$placeName : $vicinity")
            mMap.addMarker(markerOptions)
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
            //move map camera
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
            mMap.animateCamera(CameraUpdateFactory.zoomTo(11f))
        }
    }
}