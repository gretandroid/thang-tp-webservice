package com.example.webservice.webservice

import com.example.webservice.model.Article
import com.example.webservice.model.Person
import retrofit2.http.*

interface DaoPerson {
    @GET("persons")
    suspend fun getPersons() : List<Person>

    @GET("persons/{id}")
    suspend fun getPersonById(@Path("id") personId:Int) : Person

    @POST("persons")
    suspend fun createPerson(@Body person: Person) : Person

    @PUT("persons/{id}")
    suspend fun updatePerson(@Path("id") personId:Int, @Body person: Person) : Person

    @DELETE("persons/{id}")
    suspend fun deletePerson(@Path("id") personId:Int)
}