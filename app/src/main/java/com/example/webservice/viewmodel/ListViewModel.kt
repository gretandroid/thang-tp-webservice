package com.example.webservice.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
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

class ListViewModel : ViewModel() {
    private val _listArticle = MutableLiveData<List<Article>>();

    private val _error = MutableLiveData<String>();
    private val _isVisible = MutableLiveData<Boolean>();

    public val listArticle: LiveData<List<Article>> = _listArticle;
    public val error: LiveData<String> = _error;
    public val isVisible: LiveData<Boolean> = _isVisible;

    fun fetchAllArticle() {
        viewModelScope.launch {
            _isVisible.value = true;
            try {
                val articles = RetrofitInstance.articleDao.getArticles();
                Log.d("App", articles.toString())
                _listArticle.value = articles
            } catch (e: Exception) {
                Log.d("App", e.toString());
                _error.value = e.toString()
            } finally {
                Thread.sleep(5000)
                _isVisible.value = false
            }
        }
    }


}