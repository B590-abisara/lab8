package com.example.lab8

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.lab8.databinding.FragmentPhotoGalleryBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PhotoGalleryFragment : Fragment(R.layout.fragment_photo_gallery) {

    private val viewModel: PhotoGalleryViewModel by viewModels()
    private var _binding: FragmentPhotoGalleryBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: PhotoListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPhotoGalleryBinding.bind(view)

        adapter = PhotoListAdapter()
        binding.photoGrid.layoutManager = GridLayoutManager(context, 3) // Grid layout with 3 columns
        binding.photoGrid.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.photoPagingFlow.collectLatest { pagingData ->
                adapter.submitData(pagingData)  // Submitting paginated data
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
