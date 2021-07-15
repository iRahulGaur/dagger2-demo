package com.aws.dagger2.ui.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.aws.dagger2.R
import com.aws.dagger2.databinding.FragmentProfileBinding
import com.aws.dagger2.models.UserModel
import com.aws.dagger2.ui.auth.AuthResource
import com.aws.dagger2.util.UtilsManager
import com.aws.dagger2.viewModels.ViewModelProvidersFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ProfileFragment : DaggerFragment() {

    companion object {
        private const val TAG = "ProfileFragment"
    }

    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: ProfileViewModel

    @Inject
    lateinit var providersFactory: ViewModelProvidersFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel =  ViewModelProvider(this, providersFactory).get(ProfileViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.getAuthUser().removeObservers(viewLifecycleOwner)
        viewModel.getAuthUser().observe(viewLifecycleOwner) {
            if (it != null) {
                when (it.status) {
                    AuthResource.AuthStatus.AUTHENTICATED -> {
                        setUserDetails(it.data)
                    }

                    AuthResource.AuthStatus.ERROR -> {
                        setErrorDetails(it.message)
                    }
                    else -> {
                        UtilsManager.log(TAG, "subscribeObservers: else in user subscribe")
                    }
                }
            }
        }
    }

    private fun setErrorDetails(message: String?) {
        val errorString = "error"
        binding.email.text = message
        binding.username.text = errorString
        binding.website.text = errorString
    }

    private fun setUserDetails(data: UserModel?) {
        binding.email.text = data?.email
        binding.username.text = data?.username
        binding.website.text = data?.website
    }

}