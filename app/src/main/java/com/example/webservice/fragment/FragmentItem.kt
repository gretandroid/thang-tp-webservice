package com.example.webservice.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
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
        setHasOptionsMenu(true)  // enable menu for this fragment
        val args = FragmentItemArgs.fromBundle(requireArguments())
//        Toast.makeText(context, "articleId: ${args.article.id} | userId: ${args.article.userid}", Toast.LENGTH_LONG).show()
        viewModel = ViewModelProvider(this).get(ItemViewModel::class.java);

        binding.model = viewModel
        binding.lifecycleOwner = this
        viewModel.onReveived(args.article)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val dtoArticle = viewModel.article.value?.copy()
        val dtoPerson = viewModel.person.value?.copy()
        when(item.itemId) {
            R.id.editItemMenu -> {
                dtoArticle?.id = 0
                Navigation
                    .findNavController(binding.root)
                    .navigate(FragmentItemDirections.actionFragmentItemToFragmentEdit(dtoArticle!!, dtoPerson!!))
            }
            R.id.newItemMenu -> {
                Navigation
                    .findNavController(binding.root)
                    .navigate(FragmentItemDirections.actionFragmentItemToFragmentEdit(dtoArticle!!, dtoPerson!!))
            }
            else -> return false
        }
        return super.onOptionsItemSelected(item)
    }

}