package ru.fursa.unsplash.base.paging

import androidx.paging.Pager

fun <V : Any> finitePager(
    block: suspend (Int) -> List<V>
): Pager<Int, V> = Pager(
    config = defaultPagingConfig,
    pagingSourceFactory = { FinitePagedSource(totalPages = 1000, block) }
)

class FinitePagedSource<V : Any>(
    private var totalPages: Int = 100,
    private val block: suspend (Int) -> List<V>,
) : PagedSource<V>() {

    override fun getNextKey(currentPage: Int): Int? {
        return if (currentPage < totalPages) return currentPage + 1
        else null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, V> {
        val page = params.key ?: 1
        return try {
            val response = block.invoke(page)
            LoadResult.Page(response, getPreviousKey(page), getNextKey(page))
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}