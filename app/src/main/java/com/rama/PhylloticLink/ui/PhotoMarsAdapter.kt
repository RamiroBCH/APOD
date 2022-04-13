package com.rama.PhylloticLink.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rama.PhylloticLink.base.BaseViewHolder
import com.rama.PhylloticLink.databinding.PhotosRowBinding
import com.rama.PhylloticLink.domain.DModels

class PhotoMarsAdapter(
    private val context: Context,
    private val photosMars: List<DModels>,
    private val itemClickListener:OnPhotoClickListener):
    RecyclerView.Adapter<BaseViewHolder<*>>() {


    interface OnPhotoClickListener{
        fun onPhotoClick(photo: DModels)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = PhotosRowBinding.inflate(LayoutInflater.from(context), parent, false)
        val holder = MainViewHolder(itemBinding)
        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(photosMars[position], position)
        }
    }

    override fun getItemCount(): Int {
        return photosMars.size
    }

    inner class MainViewHolder(val binding: PhotosRowBinding) : BaseViewHolder<DModels>(binding.root) {
        override fun bind(item: DModels, position: Int) = with(binding) {
            Glide.with(context).load(item.url).centerCrop().into(imgPhoto)
            date.text = item.date
            val id = item.id.toString()
            idPhoto.text = id
            itemView.setOnClickListener{ itemClickListener.onPhotoClick(item)}
        }
    }
}