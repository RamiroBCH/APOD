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
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.rama.PhylloticLink.RoomData
import com.rama.PhylloticLink.data.DatasourceImpl
import com.rama.PhylloticLink.data.FavItems
import com.rama.PhylloticLink.databinding.FragmentFavoritesBinding
import com.rama.PhylloticLink.domain.RepoImpl
import com.rama.PhylloticLink.vo.Resource

class FavoritesFragment : Fragment(), FavoritesAdapter.OnFavPhotoClickListener{
    private val viewModel by activityViewModels<ApodViewModel> {
        VMFactory(RepoImpl(DatasourceImpl(RoomData.getDatabase(requireActivity().applicationContext))))
        //AppDatabase.getDatabase(requireActivity().applicationContext)
    }
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritesBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRV()
        setupObservers()
    }

    private fun setupObservers(){
        viewModel.getFavItems().observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.rvFavPhotos.adapter = FavoritesAdapter(requireContext(), result.data,this)
                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), "Error ${result.exception}", Toast.LENGTH_SHORT ).show()
                }
            }
        })
    }

    private fun setupRV(){
        binding.rvFavPhotos.layoutManager = GridLayoutManager(requireContext(),1,
            GridLayoutManager.VERTICAL,false)
        binding.rvFavPhotos.addItemDecoration(
            DividerItemDecoration(requireContext(),
                DividerItemDecoration.VERTICAL)
        )

    }

    override fun onFavPhotoClick(favphoto: FavItems) {
        viewModel.setFavItem(favphoto)
        val action = FavoritesFragmentDirections.actionFavoritesFragmentToDetailFragment()
        findNavController().navigate(action)
    }
}