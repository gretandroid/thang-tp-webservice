package com.example.webservice.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.webservice.R
import com.example.webservice.databinding.FragmentItemBinding
import com.example.webservice.databinding.FragmentListBinding
import com.example.webservice.viewmodel.ItemViewModel
import com.example.webservice.viewmodel.ListViewModel

class FragmentItem : Fragment() {
    private lateinit var viewModel: ItemViewModel
    private lateinit var binding: FragmentItemBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentItemBinding.inflate(inflater, container, false)
        val args = FragmentItemArgs.fromBundle(requireArguments())
        Toast.makeText(context, "articleId: ${args.article.id} | userId: ${args.article.userid}", Toast.LENGTH_LONG).show()
        viewModel = ViewModelProvider(this).get(ItemViewModel::class.java);
        viewModel.onReveived(args.article)

        binding.model = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner
        return binding.root
    }

}