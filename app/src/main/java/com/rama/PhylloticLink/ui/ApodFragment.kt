package com.rama.PhylloticLink.ui

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
import com.rama.PhylloticLink.R
import com.rama.PhylloticLink.data.ApodEntities
import com.rama.PhylloticLink.data.model.ItemApod
import com.rama.PhylloticLink.databinding.FragmentApodBinding
import com.rama.PhylloticLink.vo.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ApodFragment : Fragment() {
    private val viewModel by activityViewModels<ApodViewModel>()
    private var _binding: FragmentApodBinding? = null
    private val binding get() = _binding!!

    lateinit var data: ItemApod

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
            viewModel.setAppod(data)
            findNavController().navigate(R.id.action_astronomyPictureOfDayFragment_to_detailFragment)
        }
    }

    private fun getApodToView() {
        viewModel.getApod.observe(viewLifecycleOwner, Observer{ apod ->
            when(apod){
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    data = apod.data
                    binding.progressBar.visibility = View.GONE
                    Glide.with(requireContext()).load(data.url).centerCrop().into(binding.apodImg)
                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), "Error ${apod.exception}", Toast.LENGTH_LONG ).show()

                }
            }
        })
    }

}
