package com.aws.dagger2.ui.main.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.aws.dagger2.SessionManager
import com.aws.dagger2.models.PostModel
import com.aws.dagger2.network.main.MainApi
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostsViewModel @Inject constructor(private val sessionManager: SessionManager, private val mainApi: MainApi) : ViewModel() {

    private var posts: MediatorLiveData<PostResource<out List<PostModel?>?>>? = null
    fun observePosts(): LiveData<PostResource<out List<PostModel?>?>> {
        if (posts == null) {
            posts = MediatorLiveData()
            posts?.value = PostResource.loading( null)

            val source = LiveDataReactiveStreams.fromPublisher(
                mainApi.getUserPost(sessionManager.getAuthUser().value!!.data!!.id)
                    .onErrorReturn {
                        return@onErrorReturn listOf(PostModel("",-1,"",-1))
                    }
                    .map {
                        if (it.isNotEmpty()) {
                            if (it[0].id == -1)
                                return@map PostResource.error("Could not get posts", null)
                            else
                                return@map PostResource.success(it)
                        } else {
                            return@map PostResource.error("Could not get posts", null)
                        }
                    }
                    .subscribeOn(Schedulers.io())
            )

            posts?.addSource(source) {
                posts?.value = it
                posts?.removeSource(source)
            }
        }

        return posts!!
    }

}