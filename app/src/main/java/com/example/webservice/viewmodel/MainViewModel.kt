package com.example.webservice.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.webservice.model.Article
import com.example.webservice.webservice.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Boolean.FALSE
import java.lang.Boolean.TRUE

class MainViewModel : ViewModel() {
    private val _listArticle = MutableLiveData<List<Article>>();

    private val _error = MutableLiveData<String>();
    private val _isVisible = MutableLiveData<Boolean>();

    public val listArticle = _listArticle;
    public val error = _error;
    public val isVisible = _isVisible;

    fun fetchAllArticle() {
        viewModelScope.launch {
            var visible : Boolean = TRUE;
            var errorMessage : String? = null;
            var result = mutableListOf<Article>();

            _isVisible.value = visible;
            withContext(Dispatchers.IO) {
                Thread.sleep(3000);
                try {
                    val articles = RetrofitInstance.instance.getArticles();
                    Log.d("App", articles.toString())
                    result.addAll(articles)
                } catch (e: Exception) {
                    Log.d("App", e.toString());
                    errorMessage = e.toString();
                } finally {
                    visible = FALSE;
                }
            }
            _listArticle.value = result;
            _error.value = errorMessage;
            _isVisible.value = visible;

        }
    }


}