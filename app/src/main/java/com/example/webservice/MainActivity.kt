package com.example.webservice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.webservice.databinding.ActivityMainBinding
import com.example.webservice.model.Article
import com.example.webservice.webservice.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.allArticlesButton.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    val articles = RetrofitInstance.instance.getArticles();
                    Log.d("App", articles.toString())
                }
            }

        binding.insertArticleButton.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val article = RetrofitInstance.instance.createArticle(Article(0, 1, "Retrofit", "Insert"));
                Log.d("App", article.toString())
            }
        }

        binding.updateArticleButton.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val article = RetrofitInstance.instance.updateArticle(23, Article(0, 1, "Retrofit", "Update"));
                Log.d("App", article.toString())
            }
        }

    }
}