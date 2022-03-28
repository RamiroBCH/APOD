package com.rama.apod.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.rama.apod.RoomData
import com.rama.apod.data.DatasourceImpl
import com.rama.apod.data.model.ItemApod
import com.rama.apod.databinding.FragmentApodBinding
import com.rama.apod.domain.RepoImpl
import com.rama.apod.vo.Resource

class ApodFragment : Fragment() {
    private val viewModel by activityViewModels<ApodViewModel> {
        VMFactory(RepoImpl(DatasourceImpl(RoomData.getDatabase(requireActivity().applicationContext))))
    }
    private var _binding: FragmentApodBinding? = null
    private val binding get() = _binding!!

    lateinit var apodTD: ItemApod

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentApodBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getApodToView()
        binding.apodImg.setOnClickListener {
            viewModel.setAppod(apodTD)
            val action = ApodFragmentDirections.actionAstronomyPictureOfDayFragmentToDetailFragment()
            findNavController().navigate(action)
        }
    }

    private fun getApodToView() {
        viewModel.getApod.observe(viewLifecycleOwner, Observer{ apod ->
            when(apod){
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    apodTD = apod.data
                    binding.progressBar.visibility = View.GONE
                    Glide.with(requireContext()).load(apod.data.hdurl).centerCrop().into(binding.apodImg)
                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), "Error ${apod.exception}", Toast.LENGTH_SHORT ).show()
                }
            }
        })
    }

}
