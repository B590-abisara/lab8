package com.example.lab8

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab8.network.GalleryItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private const val TAG = "PhotoGalleryViewModel"

class PhotoGalleryViewModel : ViewModel() {
    private val photoRepository = PhotoRepository()

    private val _galleryItems = MutableStateFlow<List<GalleryItem>>(emptyList())
    val galleryItems: StateFlow<List<GalleryItem>> = _galleryItems.asStateFlow()

    init {
        Log.d(TAG, "ViewModel created")
        viewModelScope.launch {
            if (_galleryItems.value.isNotEmpty()) {
                Log.d(TAG, "Skipping fetch, data already loaded")
                return@launch
            }
            try {
                val items = photoRepository.searchPhotos("birds")
                _galleryItems.value = items
                Log.d(TAG, "Items received: $items")
            } catch (ex: Exception) {
                Log.e(TAG, "Failed to fetch gallery items", ex)
            }
        }
    }

}
