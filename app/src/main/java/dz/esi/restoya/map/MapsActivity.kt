package dz.esi.restoya.map

import android.Manifest
import android.os.Bundle

import dz.esi.restoya.R
import android.widget.Toast
import android.content.pm.PackageManager
import android.location.Location
import android.support.v4.content.ContextCompat
import android.support.v4.app.ActivityCompat
import android.os.Build
import android.support.v4.app.FragmentActivity
import android.util.Log
import android.widget.Button

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

class MapsActivity : FragmentActivity(), OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {
    private var mMap: GoogleMap? = null
    var latitude: Double = 0.toDouble()
    var longitude: Double = 0.toDouble()
    private val PROXIMITY_RADIUS = 10000
    var mGoogleApiClient: GoogleApiClient? = null
    lateinit var mLastLocation: Location
    var mCurrLocationMarker: Marker? = null
    lateinit var mLocationRequest: LocationRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission()
        }

        //Check if Google Play Services Available or not
        if (!CheckGooglePlayServices()) {
            Log.d("onCreate", "Finishing test case since Google Play Services are not available")
            finish()
        } else {
            Log.d("onCreate", "Google Play Services available.")
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun CheckGooglePlayServices(): Boolean {
        val googleAPI = GoogleApiAvailability.getInstance()
        val result = googleAPI.isGooglePlayServicesAvailable(this)
        if (result != ConnectionResult.SUCCESS) {
            if (googleAPI.isUserResolvableError(result)) {
                googleAPI.getErrorDialog(this, result,
                        0).show()
            }
            return false
        }
        return true
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap!!.mapType = GoogleMap.MAP_TYPE_HYBRID

        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient()
                mMap!!.isMyLocationEnabled = true
            }
        } else {
            buildGoogleApiClient()
            mMap!!.isMyLocationEnabled = true
        }

        val btnRestaurant : Button = findViewById(R.id.btnRestaurant)
        btnRestaurant.setOnClickListener {
            val restaurant = "restaurant1"
            Log.d("onClick", "Button is Clicked")
            mMap!!.clear()
            val url = getUrl(latitude, longitude, restaurant)
            val dataTransfer = arrayOfNulls<Any>(2)
            dataTransfer[0] = mMap
            dataTransfer[1] = url
            Log.d("onClick", url)
            val getNearbyPlacesData = GetNearbyPlacesData()
            getNearbyPlacesData.execute(mMap, url)
            Toast.makeText(this, "Nearby Restaurants", Toast.LENGTH_LONG).show()
        }
    }

    @Synchronized
    protected fun buildGoogleApiClient() {
        mGoogleApiClient = GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build()
        mGoogleApiClient!!.connect()
    }

    override fun onConnected(bundle: Bundle?) {
        mLocationRequest = LocationRequest()
        mLocationRequest.setInterval(1000)
        mLocationRequest.setFastestInterval(1000)
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)
        if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this)
        }
    }

    private fun getUrl(latitude: Double, longitude: Double, nearbyPlace: String): String {

        val googlePlacesUrl = StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?")
        googlePlacesUrl.append("location=$latitude,$longitude")
        googlePlacesUrl.append("&radius=$PROXIMITY_RADIUS")
        googlePlacesUrl.append("&type=$nearbyPlace")
        googlePlacesUrl.append("&sensor=true")
        googlePlacesUrl.append("&key=" + resources.getString(R.string.google_maps_key))
        Log.d("getUrl", googlePlacesUrl.toString())
        return googlePlacesUrl.toString()
    }

    override fun onConnectionSuspended(i: Int) {

    }

    override fun onLocationChanged(location: Location) {
        Log.d("onLocationChanged", "entered")

        mLastLocation = location
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker!!.remove()
        }

        //Place current location marker
        latitude = location.getLatitude()
        longitude = location.getLongitude()
        val latLng = LatLng(location.getLatitude(), location.getLongitude())
        val markerOptions = MarkerOptions()
        markerOptions.position(latLng)
        markerOptions.title("Current Position")
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA))
        mCurrLocationMarker = mMap!!.addMarker(markerOptions)

        //move map camera
        mMap!!.moveCamera(CameraUpdateFactory.newLatLng(latLng))
        mMap!!.animateCamera(CameraUpdateFactory.zoomTo(11f))
        Toast.makeText(this@MapsActivity, "Your Current Location", Toast.LENGTH_LONG).show()

        Log.d("onLocationChanged", String.format("latitude:%.3f longitude:%.3f", latitude, longitude))

        //stop location updates
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this)
            Log.d("onLocationChanged", "Removing Location Updates")
        }
        Log.d("onLocationChanged", "Exit")

    }

    override fun onConnectionFailed(connectionResult: ConnectionResult) {

    }

    val MY_PERMISSIONS_REQUEST_LOCATION = 99
    fun checkLocationPermission(): Boolean {
        if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        MY_PERMISSIONS_REQUEST_LOCATION)


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        MY_PERMISSIONS_REQUEST_LOCATION)
            }
            return false
        } else {
            return true
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_LOCATION -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted. Do the
                    // contacts-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                                    Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient()
                        }
                        mMap!!.isMyLocationEnabled = true
                    }

                } else {

                    // Permission denied, Disable the functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show()
                }
                return
            }
        }// other 'case' lines to check for other permissions this app might request.
        // You can add here other case statements according to your requirement.
    }
}
