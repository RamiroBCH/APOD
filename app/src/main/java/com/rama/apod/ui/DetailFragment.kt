package com.rama.apod.ui

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.room.Ignore
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
        binding.btnFav.setOnClickListener {
            when (detConf) {
                "mars" -> viewModel.saveFavorite(
                    FavItems(
                        viewModel.photoMars.id.toString(),
                        viewModel.photoMars.earth_date,
                        viewModel.photoMars.img_src,
                        viewModel.photoMars.sol.toString()
                    )
                )
                "apod" -> viewModel.saveFavorite(
                    FavItems(
                        viewModel.apod.title,
                        viewModel.apod.date,
                        viewModel.apod.hdurl,
                        viewModel.apod.explanation
                    )
                )
            }

            Toast.makeText(requireContext(), "Guardado en Favoritos", Toast.LENGTH_SHORT).show()
        }
    }

    fun setDetailsApod() {
        Glide.with(requireContext()).load(viewModel.apod.hdurl).centerCrop().into(binding.imgDetail)
        binding.txtTitulo.text = viewModel.apod.title
        binding.txtDate.text = viewModel.apod.date
        binding.txtDetail.text = viewModel.apod.explanation
        binding.btnFav.visibility = View.VISIBLE
    }

    fun setDetailsMars() {
        Glide.with(requireContext()).load(viewModel.photoMars.img_src).centerCrop().into(binding.imgDetail)
        binding.txtTitulo.text = viewModel.photoMars.id.toString()
        binding.txtDate.text = viewModel.photoMars.earth_date
        binding.txtDetail.text = viewModel.photoMars.sol.toString()
        binding.btnFav.visibility = View.VISIBLE

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