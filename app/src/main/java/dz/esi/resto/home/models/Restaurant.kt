package dz.esi.resto.home.models

data class Restaurant (
        val _id : Int,
        val name : String,
        val address : String,
        val phone : Int,
        val email : String,
        val description : String,
        val facebook : String,
        val twitter : String,
        var images : ArrayList<String>
)