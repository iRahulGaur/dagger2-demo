package com.aws.dagger2.ui.main.post

class PostResource<T>(
    val status: PostStatus, val data: T, val message: String?
) {
    enum class PostStatus {
        SUCCESS, ERROR, LOADING
    }

    companion object {
        fun <T> success(data: T?): PostResource<T?> {
            return PostResource(PostStatus.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): PostResource<T?> {
            return PostResource(PostStatus.ERROR, data, msg)
        }

        fun <T> loading(data: T?): PostResource<T?> {
            return PostResource(PostStatus.LOADING, data, null)
        }
    }
}