package com.rama.PhylloticLink.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rama.PhylloticLink.base.BaseViewHolder
import com.rama.PhylloticLink.data.FavItems
import com.rama.PhylloticLink.databinding.FavoriteRowBinding

class FavoritesAdapter(
    private val context: Context,
    private val photoFav: List<FavItems>,
    private val itemClickListenerFav: OnFavPhotoClickListener): RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnFavPhotoClickListener {
        fun onFavPhotoClick(favphoto:FavItems)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = FavoriteRowBinding.inflate(LayoutInflater.from(context),parent,false)
        val holder = FavViewHolder(itemBinding)
        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is FavoritesAdapter.FavViewHolder -> holder.bind(photoFav[position], position)
        }
    }

    override fun getItemCount(): Int {
        return photoFav.size
    }

    inner class FavViewHolder(val binding: FavoriteRowBinding) : BaseViewHolder<FavItems>(binding.root) {
        override fun bind(item: FavItems, position: Int) = with(binding) {
            Glide.with(context).load(item.url).centerCrop().into(favPreview)
            date.text = item.date
            val id = item.id
            titleId.text = id.toString()
            itemView.setOnClickListener{ itemClickListenerFav.onFavPhotoClick(item)}

        }

    }

}