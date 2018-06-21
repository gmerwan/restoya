package dz.esi.restoya.home.models

data class SpecialCollection (
        var name : String,
        var image : Int,
        var restaurants : ArrayList<Restaurant>
)