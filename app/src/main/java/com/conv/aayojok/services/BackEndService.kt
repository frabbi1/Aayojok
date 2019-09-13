package com.conv.aayojok.services



import com.conv.aayojok.Models.*
import retrofit2.Call
import retrofit2.http.*

interface BackEndService {
    /*@POST("participants/add")
    fun addParticipant(@Body newParticipant: Participant): Call<Participant>



    @PUT("participants/update/{id}")
    fun updateParticipant(@Path("id") id:String, @Body newParticipant: Participant): Call<Participant>

    @GET("participants/check/{id}")
    fun checkNewUser(@Path("id") id:String): Call<Participant>

    @GET("participants/{id}")
    fun getParticipant(@Path("id") id:String): Call<Participant>

    @GET("participants/all/{id}")
    fun getAllParticipants(@Path("id" )id:String):Call<List<Participant>>




    @GET("file/photos/{id}")
    fun getPhoto(@Path("id" )id:String): Call<List<String>>





    */



    @POST("participants/add")
    fun addParticipant(@Body newParticipant: User): Call<User>

    @GET("participants/check/{id}")
    fun checkNewUser(@Path("id") id:String): Call<User>

    @GET("event/joined/{id}")
    fun getJoinedEvents(@Path("id" )id:String):Call<List<Event>>

    @GET("file/resources/{id}")
    fun getFile(@Path("id" )id:String): Call<List<File>>

    @GET("file/photos/{id}")
    fun getPhoto(@Path("id" )id:String): Call<List<String>>

    @GET("event/nearme/{id}")
    fun getPlace(@Path("id" )id:String): Call<List<Place>>

    @GET("event/fetch")
    fun fetchEvent(@Query("id") id : String, @Query("code") code:String): Call<Event>

    @POST("event/register")
    fun register(@Query("e_id")eventID:String, @Query("p_id")pid:String): Call<String>

    @GET("participants/{id}")
    fun getParticipant(@Path("id") id:String): Call<User>

    @GET("participants/all/{id}")
    fun getAllParticipants(@Path("id" )id:String):Call<List<User>>

    @GET("event/schedule/{id}")
    fun getSchedule(@Path("id" )id:String):Call<String>

    @GET("event/rpinfo/{id}")
    fun getRPinfo(@Path("id" )id:String):Call<List<RP>>

    @GET("event/notification/{id}")
    fun getNotification(@Path("id" )id:String): Call<List<String>>

    @PUT("participants/update/{id}")
    fun updateParticipant(@Path("id") id:String, @Body newParticipant: User): Call<User>

}