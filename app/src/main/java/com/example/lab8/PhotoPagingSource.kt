package com.example.lab8
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.lab8.PhotoRepository
import com.example.lab8.network.GalleryItem

class PhotoPagingSource(
    private val repository: PhotoRepository
) : PagingSource<Int, GalleryItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GalleryItem> {
        return try {
            val page = params.key ?: 1  // Start from page 1
            val response = repository.fetchPhotos(page)  // Fetch data for the page

            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page - 1,  // Previous page (null if first page)
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GalleryItem>): Int? {
        return state.anchorPosition
    }
}
