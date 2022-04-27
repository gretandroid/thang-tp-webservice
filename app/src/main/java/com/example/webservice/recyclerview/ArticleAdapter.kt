package com.example.webservice.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.webservice.R
import com.example.webservice.databinding.RowBinding
import com.example.webservice.model.Article

class ArticleAdapter(private val listArticle : List<Article>?) : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val root = inflater.inflate(R.layout.row, parent, false);
        return ViewHolder(root);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            with(holder.binding) {
                titleText.setText(listArticle?.get(position)?.title ?: "");
                contentText.setText(listArticle?.get(position)?.content ?: "");
            }
    }

    override fun getItemCount(): Int {
        return listArticle?.size ?: 0;
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val binding = RowBinding.bind(itemView);
    }
}
