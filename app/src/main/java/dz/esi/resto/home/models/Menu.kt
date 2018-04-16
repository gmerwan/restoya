package dz.esi.resto.home.models

data class Menu (
        val _id : Int,
        val name : String,
        var image : String,
        var restaurant : Int,
        var plats : ArrayList<Plat>
)