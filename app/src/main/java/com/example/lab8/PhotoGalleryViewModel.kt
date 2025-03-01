package com.example.lab8

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.lab8.network.GalleryItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private const val TAG = "PhotoGalleryViewModel"

class PhotoGalleryViewModel : ViewModel() {
    private val photoRepository = PhotoRepository()

    private val _galleryItems = MutableStateFlow<List<GalleryItem>>(emptyList())
    val galleryItems: StateFlow<List<GalleryItem>> = _galleryItems.asStateFlow()

    val photoPagingFlow: Flow<PagingData<GalleryItem>> = Pager(
        config = PagingConfig(
            pageSize = 100,  // Load 100 images per page
            enablePlaceholders = false
        ),
        pagingSourceFactory = { PhotoPagingSource(photoRepository) }
    ).flow.cachedIn(viewModelScope)

    init {
        Log.d(TAG, "ViewModel created")
    }

}
