package com.conv.aayojok.Models

data class Place (
    val lon:String,
    val lat:String,
    val loc:String

)

object CurrentPlace{

    var lon:String = ""
    var lat:String = ""
    var loc:String = ""
}
