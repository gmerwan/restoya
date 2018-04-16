package dz.esi.restoya.home.models

data class Restaurant (
        var _id : Int,
        var favorite : Boolean,
        var name : String,
        var address : String,
        var phone : Int,
        var email : String,
        var description : String,
        var facebook : String,
        var twitter : String,
        var images : ArrayList<String>
)