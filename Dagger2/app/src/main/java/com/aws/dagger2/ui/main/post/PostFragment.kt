package com.aws.dagger2.ui.main.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aws.dagger2.R
import com.aws.dagger2.databinding.FragmentPostBinding
import com.aws.dagger2.util.ProgressManager
import com.aws.dagger2.util.VerticalSpacingItemDecoration
import com.aws.dagger2.viewModels.ViewModelProvidersFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class PostFragment : DaggerFragment() {

    private lateinit var binding: FragmentPostBinding
    private lateinit var viewModel: PostsViewModel

    @Inject
    lateinit var providersFactory: ViewModelProvidersFactory

    @Inject
    lateinit var postRecyclerAdapter: PostRecyclerAdapter

    @Inject
    lateinit var verticalSpacingItemDecoration: VerticalSpacingItemDecoration

    @Inject
    lateinit var progressManager: ProgressManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_post, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel =  ViewModelProvider(this, providersFactory).get(PostsViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        initRecyclerView()
        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.observePosts().removeObservers(viewLifecycleOwner)
        viewModel.observePosts().observe(viewLifecycleOwner) {
            if (it != null) {
                when (it.status) {
                    PostResource.PostStatus.LOADING -> {
                        progressManager.showProgressDialog(requireActivity(), "Getting your data")
                    }
                    PostResource.PostStatus.SUCCESS -> {
                        progressManager.dismissDialog()
                        postRecyclerAdapter.setPosts(it.data!!)
                    }
                    PostResource.PostStatus.ERROR -> {
                        progressManager.dismissDialog()
                        Toast.makeText(requireActivity(), it.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerView.addItemDecoration(verticalSpacingItemDecoration)
        binding.recyclerView.adapter = postRecyclerAdapter
    }
}