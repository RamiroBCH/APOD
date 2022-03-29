package com.rama.PhylloticLink.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.rama.PhylloticLink.R
import com.rama.PhylloticLink.RoomData
import com.rama.PhylloticLink.data.DatasourceImpl
import com.rama.PhylloticLink.databinding.FragmentHomeBinding
import com.rama.PhylloticLink.domain.RepoImpl


class HomeFragment : Fragment() {
    private val viewModel by activityViewModels<ApodViewModel> {
        VMFactory(RepoImpl(DatasourceImpl(RoomData.getDatabase(requireActivity().applicationContext))))
    }

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnApod.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_astronomyPictureOfDayFragment)
        }
        binding.btnMarsPhotos.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_marsPhotoFragment)
        }
        binding.btnFav.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_favoritesFragment)
        }
    }
}