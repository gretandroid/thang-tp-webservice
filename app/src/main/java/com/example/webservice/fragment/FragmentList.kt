package com.example.webservice.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.webservice.databinding.FragmentListBinding
import com.example.webservice.model.Article
import com.example.webservice.recyclerview.ArticleAdapter
import com.example.webservice.viewmodel.ListViewModel


class FragmentList : Fragment(), ArticleAdapter.ArticleAdapterListener {
    private lateinit var viewModel: ListViewModel
    private lateinit var binding: FragmentListBinding
    private val listArticle = mutableListOf<Article>();
    private var adapter: ArticleAdapter? = null;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentListBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java);
        binding.recyclerView.addItemDecoration(
                DividerItemDecoration(binding.recyclerView.getContext(),
        DividerItemDecoration.VERTICAL
    )
)
        // subcribe update UI
        viewModel.listArticle.observe(this.viewLifecycleOwner) { articles ->
            listArticle.clear();
            listArticle.addAll(articles);

            if (adapter === null) {
                adapter = ArticleAdapter(listArticle, this);
                binding.recyclerView.layoutManager = LinearLayoutManager(this.context);
                binding.recyclerView.adapter = adapter;
            } else {
                adapter?.notifyDataSetChanged();
            }
        }
        viewModel.error.observe(this.viewLifecycleOwner) { message ->
            binding.errorTextView.setText(message);
        }
        viewModel.isVisible.observe(this.viewLifecycleOwner) { visible ->
            if (visible) {

                binding.mainProgressBar.visibility = View.VISIBLE;
            } else {
                binding.mainProgressBar.visibility = View.GONE;
            }

        }

        // subcribe UI event
        binding.allArticleButton.setOnClickListener {
            viewModel.fetchAllArticle();
        }

        return binding.root;
    }

    override fun onClick(view: View?, article: Article?) {
        Navigation
            .findNavController(binding.root)
            .navigate(FragmentListDirections.actionFragmentListToFragmentItem(article!!))
    }

}