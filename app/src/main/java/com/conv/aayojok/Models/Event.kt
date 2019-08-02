package com.conv.aayojok.Models

data class Event (
    val id:String,
    val name:String,
    val location:String,
    val start_date:String,
    val end_date:String,
    val description:String,
    val longitude:String,
    val latitude:String,
    val code:String,
    val rpCode:String
)

object EventSupplier{

    var eventList:MutableList<Event> = ArrayList()

    fun addEvent(e:Event){
        eventList.add(e)
    }
    fun getList():MutableList<Event>{
        return  eventList
    }




}

object CurrentEvent{


        var id = ""
        var name = ""
        var location = ""
        var startDate = ""
        var endDate = ""
        var description = ""
        var longitude:String = ""
        var latitude:String = ""
        var code = ""
        var rpCode = ""

        fun setEventDetails(
            id:String,
            name:String,
            location:String,
            startDate:String,
            endDate:String,
            description:String,
            longitude:String,
            latitude:String,
            code: String,
            rpCode: String

        ){
            this.id = id
            this.name = name
            this.location = location
            this.startDate = startDate
            this.endDate = endDate
            this.description = description
            this.longitude = longitude
            this.latitude = latitude
            this.code = code
            this.rpCode = rpCode
        }



}
