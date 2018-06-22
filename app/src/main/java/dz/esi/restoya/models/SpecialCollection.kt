package dz.esi.restoya.models

import dz.esi.restoya.database.entities.Restaurant

data class SpecialCollection (
        var name : String,
        var image : Int,
        var restaurants : ArrayList<Restaurant>
)