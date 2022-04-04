package com.rama.PhylloticLink.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.rama.PhylloticLink.data.NormalizedItem
import com.rama.PhylloticLink.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private val viewModel by activityViewModels<ApodViewModel>()
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val detConf = viewModel.detailsConfigure
        when (detConf) {
            "mars" -> setDetailsMars()
            "apod" -> setDetailsApod()
            "fav" -> setDetailsFav()
        }
        binding.btnFav.setOnClickListener {
            when (detConf) {
                "mars" -> viewModel.saveFavorite(
                    NormalizedItem(
                        viewModel.photoMars.id.toString(),
                        viewModel.photoMars.earth_date,
                        viewModel.photoMars.img_src,
                        viewModel.photoMars.sol.toString()
                    )
                )
                "apod" -> viewModel.saveFavorite(
                    NormalizedItem(
                        viewModel.apod.title,
                        viewModel.apod.date,
                        viewModel.apod.hdurl,
                        viewModel.apod.explanation
                    )
                )
            }

            Toast.makeText(requireContext(), "Guardado en Favoritos", Toast.LENGTH_SHORT).show()
        }
        binding.delete.setOnClickListener {
            when(detConf){
                "fav" -> deleteFav(viewModel.fav)
            }

        }
    }

    private fun setDetailsApod() {
        Glide.with(requireContext()).load(viewModel.apod.hdurl).into(binding.imgDetail)
        binding.txtTitulo.text = viewModel.apod.title
        binding.txtDate.text = viewModel.apod.date
        binding.txtDetail.text = viewModel.apod.explanation
        binding.btnFav.visibility = View.VISIBLE
        binding.delete.visibility = View.GONE
    }

    private fun setDetailsMars() {
        Glide.with(requireContext()).load(viewModel.photoMars.img_src).into(binding.imgDetail)
        binding.txtTitulo.text = viewModel.photoMars.id.toString()
        binding.txtDate.text = viewModel.photoMars.earth_date
        binding.txtDetail.text = viewModel.photoMars.sol.toString()
        binding.btnFav.visibility = View.VISIBLE
        binding.delete.visibility = View.GONE
    }

    private fun setDetailsFav() {
        val favDetail = viewModel.fav
        Glide.with(requireContext()).load(favDetail.url).into(binding.imgDetail)
        binding.txtTitulo.text = favDetail.id
        binding.txtDate.text = favDetail.date
        binding.txtDetail.text = favDetail.details
        binding.btnFav.visibility = View.GONE
        binding.delete.visibility = View.VISIBLE
    }

    private fun deleteFav(normalizedItem: NormalizedItem){
        viewModel.deleteFavorite(normalizedItem)
    }
}