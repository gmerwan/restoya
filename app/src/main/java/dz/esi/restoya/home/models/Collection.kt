package dz.esi.restoya.home.models

data class Collection (
        var _id : Int,
        var name : String,
        var image : Int,
        var restaurants : ArrayList<Restaurant>
)