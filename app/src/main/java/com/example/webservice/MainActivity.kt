package com.example.webservice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.webservice.databinding.ActivityMainBinding
import com.example.webservice.model.Article
import com.example.webservice.recyclerview.ArticleAdapter
import com.example.webservice.viewmodel.MainViewModel
import com.example.webservice.webservice.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private val listArticle = mutableListOf<Article>();
    private var adapter: ArticleAdapter? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java);

        // subcribe update UI
        viewModel.listArticle.observe(this) { articles ->
            listArticle.clear();
            listArticle.addAll(articles);

            if (adapter === null) {
                adapter = ArticleAdapter(listArticle);
                binding.recyclerView.layoutManager = LinearLayoutManager(this);
                binding.recyclerView.adapter = adapter;
            } else {
                adapter?.notifyDataSetChanged();
            }
        }
        viewModel.error.observe(this) { message ->
            binding.errorTextView.setText(message);
        }
        viewModel.isVisible.observe(this) { visible ->
            if (visible) {

                binding.mainProgressBar.visibility = VISIBLE;
            }
            else {
                binding.mainProgressBar.visibility = GONE;
            }

        }

        // subcribe UI event
        binding.allArticleButton.setOnClickListener {
            viewModel.fetchAllArticle();
        }


//        binding.allArticlesButton.setOnClickListener {
//                CoroutineScope(Dispatchers.Main).launch {
//                    var progressBarVisibility = true;
//                    var errorMessage : String? = null;
//                    binding.progressBar.visibility = VISIBLE;
//                    withContext(Dispatchers.IO) {
//                        Thread.sleep(3000);
//                        try {
//                            val articles = RetrofitInstance.instance.getArticles();
//                            Log.d("App", articles.toString())
//                        } catch (e: Exception) {
//                            Log.d("App", e.toString());
//                            errorMessage = e.toString();
//                        } finally {
//                            progressBarVisibility = false;
//                        }
//                    }
//                    if (!progressBarVisibility) {
//                        binding.progressBar.visibility = GONE;
//                    }
//                    if (errorMessage !== null) {
//                        Toast.makeText(binding.root.context, errorMessage, Toast.LENGTH_LONG).show()
//                    };
//                }
//            }
//
//        binding.insertArticleButton.setOnClickListener {
//            CoroutineScope(Dispatchers.IO).launch {
//                try {
//                    val article = RetrofitInstance.instance.createArticle(Article(0, 1, "Retrofit", "Insert"));
//                    Log.d("App", article.toString())
//
//                }
//                catch (e: Exception) {
//                    Log.d("App", e.toString())
//
//                }
//            }
//        }
//
//        binding.updateArticleButton.setOnClickListener {
//            CoroutineScope(Dispatchers.IO).launch {
//                val article = RetrofitInstance.instance.updateArticle(23, Article(23, 1, "Retrofit", "Update2"));
//                Log.d("App", article.toString())
//            }
//        }
//
//        binding.deleteArticleButton.setOnClickListener {
//            CoroutineScope(Dispatchers.IO).launch {
//                RetrofitInstance.instance.deleteArticle(23);
//                Log.d("App", "Deleted an article");
//            }
//        }

    }
}