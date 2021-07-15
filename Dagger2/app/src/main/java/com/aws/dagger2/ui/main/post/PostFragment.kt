package com.aws.dagger2.ui.main.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.aws.dagger2.R
import com.aws.dagger2.databinding.FragmentPostBinding
import com.aws.dagger2.util.UtilsManager
import com.aws.dagger2.viewModels.ViewModelProvidersFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class PostFragment : DaggerFragment() {

    companion object {
        private const val TAG = "PostFragment"
    }

    private lateinit var binding: FragmentPostBinding
    private lateinit var viewModel: PostsViewModel

    @Inject
    lateinit var providersFactory: ViewModelProvidersFactory

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

        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.observePosts().removeObservers(viewLifecycleOwner)
        viewModel.observePosts().observe(viewLifecycleOwner) {
            if (it != null) {
                UtilsManager.log(TAG, "subscribeObservers: ${it.data}")
            }
        }
    }
}