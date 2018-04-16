package dz.esi.restoya.home.models

data class Collection (
        val _id : Int,
        val name : String,
        var image : String,
        var restaurants : ArrayList<Restaurant>
)