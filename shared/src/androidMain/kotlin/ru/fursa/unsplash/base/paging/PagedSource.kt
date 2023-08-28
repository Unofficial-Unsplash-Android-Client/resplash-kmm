package ru.fursa.unsplash.base.paging

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState

val defaultPagingConfig = PagingConfig(enablePlaceholders = false, pageSize = 30)

abstract class PagedSource<V : Any> : PagingSource<Int, V>() {

    abstract fun getNextKey(currentPage: Int): Int?

    fun getPreviousKey(currentPage: Int) = if (currentPage == 1) null else currentPage - 1

    override fun getRefreshKey(state: PagingState<Int, V>): Int? {
        return state.anchorPosition?.let { pos ->
            state.closestPageToPosition(pos)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(pos)?.nextKey?.minus(1)
        }
    }
}
