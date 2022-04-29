package com.example.webservice.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.webservice.model.Article
import com.example.webservice.model.Person
import com.example.webservice.webservice.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Boolean.FALSE
import java.lang.Boolean.TRUE

class ItemViewModel : ViewModel() {
    private val _article = MutableLiveData<Article>()
    private val _person = MutableLiveData<Person>()
    private val _isVisible = MutableLiveData<Boolean>();

    public val article : LiveData<Article> = _article
    public val person : LiveData<Person> = _person
    public val isVisible : LiveData<Boolean> = _isVisible

    fun setArticle(article : Article) {
        _article.value = article;
    }

    fun setPerson(person : Person) {
        _person.value = person;
    }

    fun onReveived(article : Article) {
        viewModelScope.launch {
            var person : Person? = null
            var visible : Boolean = TRUE;
            _isVisible.value = visible;
            withContext(Dispatchers.IO) {
                Thread.sleep(3000);
                try {
                    person = RetrofitInstance.personDao.getPersonById(article.userid);
                    Log.d("App", person.toString())

                } catch (e: Exception) {
                    Log.d("App", e.toString());
                } finally {
                    visible = FALSE;
                }
            }
            _isVisible.value = visible;
            setArticle(article)
            if (person !== null) {
                setPerson(person!!)
            }
        }
    }
}