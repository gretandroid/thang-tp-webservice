package com.example.webservice.webservice

import com.example.webservice.model.Article
import retrofit2.http.*

interface DaoArticle {
    @GET("articles")
    suspend fun getArticles() : List<Article>

    @POST("articles")
    suspend fun createArticle(@Body article: Article) : Article

    @PUT("articles/{id}")
    suspend fun updateArticle(@Path("id") articleId:Int, @Body article: Article) : Article

    @DELETE("articles/{id}")
    suspend fun deleteArticle(@Path("id") articleId:Int)
}