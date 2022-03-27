package com.rama.apod.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rama.apod.base.BaseViewHolder
import com.rama.apod.data.model.Photo
import com.rama.apod.databinding.PhotosRowBinding

class PhotoMarsAdapter(
    private val context: Context,
    private val photosMars: List<Photo>,
    private val itemClickListener:OnPhotoClickListener):
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnPhotoClickListener{
        fun onPhotoClick(photo:Photo)
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

    inner class MainViewHolder(val binding: PhotosRowBinding) : BaseViewHolder<Photo>(binding.root) {
        override fun bind(item: Photo, position: Int) = with(binding) {
            Glide.with(context).load(item.img_src).centerCrop().into(imgPhoto)
            date.text = item.earth_date
            val id = "id: " + item.id.toString()
            idPhoto.text = id
            itemView.setOnClickListener{ itemClickListener.onPhotoClick(item)}
        }
    }
}