package com.rama.apod.ui

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.rama.apod.R
import com.rama.apod.RoomData
import com.rama.apod.data.Datasource
import com.rama.apod.data.FavItems
import com.rama.apod.databinding.FragmentDetailBinding
import com.rama.apod.databinding.FragmentMarsPhotosBinding
import com.rama.apod.domain.RepoImpl

class DetailFragment : Fragment() {
    private val viewModel by activityViewModels<ApodViewModel> {
        VMFactory(RepoImpl(Datasource(RoomData.getDatabase(requireActivity().applicationContext))))
    }
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
    }

    fun setDetailsApod() {
        var apodDetail = viewModel.apod
        Glide.with(requireContext()).load(apodDetail.hdurl).centerCrop().into(binding.imgDetail)
        binding.txtTitulo.text = apodDetail.title
        binding.txtDate.text = apodDetail.date
        binding.txtDetail.text = apodDetail.explanation
        binding.btnFav.visibility = View.VISIBLE
        binding.btnFav.setOnClickListener {
            viewModel.saveFavorite(
                FavItems(
                    apodDetail.title.toInt(),
                    apodDetail.date,
                    apodDetail.hdurl,
                    apodDetail.explanation
                )
            )
            Toast.makeText(requireContext(), "Guardado en Favoritos", Toast.LENGTH_SHORT).show()
        }
    }

    fun setDetailsMars() {
        var detail = viewModel.photoMars
        Glide.with(requireContext()).load(detail.img_src).centerCrop().into(binding.imgDetail)
        binding.txtTitulo.text = detail.id.toString()
        binding.txtDate.text = detail.earth_date
        binding.txtDetail.text = detail.sol.toString()
        binding.btnFav.visibility = View.VISIBLE
        binding.btnFav.setOnClickListener {
            viewModel.saveFavorite(
                FavItems(
                    detail.id.toString().toInt(),
                    detail.earth_date,
                    detail.img_src,
                    detail.sol.toString()
                )
            )
            Toast.makeText(requireContext(), "Guardado en Favoritos", Toast.LENGTH_SHORT).show()
        }
    }

    fun setDetailsFav() {
        var favDetail = viewModel.fav
        Glide.with(requireContext()).load(favDetail.url).centerCrop().into(binding.imgDetail)
        binding.txtTitulo.text = favDetail.id.toString()
        binding.txtDate.text = favDetail.date
        binding.txtDetail.text = favDetail.details
        binding.btnFav.visibility = View.GONE
    }

}