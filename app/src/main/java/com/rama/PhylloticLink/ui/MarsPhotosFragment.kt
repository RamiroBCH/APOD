package com.rama.PhylloticLink.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.rama.PhylloticLink.databinding.FragmentMarsPhotosBinding
import com.rama.PhylloticLink.domain.DModels
import com.rama.PhylloticLink.vo.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MarsPhotosFragment : Fragment(), PhotoMarsAdapter.OnPhotoClickListener {
    private val viewModel by activityViewModels<ApodViewModel>()
    private var _binding: FragmentMarsPhotosBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMarsPhotosBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setSol(100)
        setupSearch()
        setupRecyclerView()
        setupObservers()
    }
    private fun setupObservers(){
        viewModel.showPhotosList.observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.rvMarsPhotos.adapter = PhotoMarsAdapter(requireContext(), result.data,this)
                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), "Error ${result.exception}", Toast.LENGTH_SHORT ).show()
                }
            }
        })
    }

    private fun setupSearch(){
        binding.search.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                viewModel.setSol(p0!!.toInt())
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })
    }

    private fun setupRecyclerView(){
        binding.rvMarsPhotos.layoutManager = GridLayoutManager(requireContext(),1,GridLayoutManager.VERTICAL,false)
        binding.rvMarsPhotos.addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))

    }

    override fun onPhotoClick(photo: DModels) {
        viewModel.setPhoto(photo)
        val action = MarsPhotosFragmentDirections.actionMarsPhotosFragmentToDetailFragment()
        findNavController().navigate(action)
    }
}