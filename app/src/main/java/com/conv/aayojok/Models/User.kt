package com.conv.aayojok.Models

data class User(
    var id : String,
    var name : String,
    var email : String,
    var age : String,
    var gender : String,
    var occupation : String?,
    var institution : String?,
    var phone : String,
    var nationality : String?,
    var photo : String?
)

object CurrentUser {

    var id:String = ""
    var name:String = ""
    var email:String = ""
    var photo:String = ""

    var age = ""
    var institution = ""
    var occupation = ""
    var gender = ""
    var phone = ""
    var nationality = ""

    fun getUser():String{
        return id
    }

    fun setUser(id:String){
        CurrentUser.id = id
    }
}