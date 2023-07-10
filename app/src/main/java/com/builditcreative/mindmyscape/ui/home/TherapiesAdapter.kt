package com.builditcreative.mindmyscape.ui.home

import android.graphics.Bitmap
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.builditcreative.mindmyscape.R
import com.builditcreative.mindmyscape.data.Data
import com.builditcreative.mindmyscape.databinding.ItemTherapiesBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition


class TherapiesAdapter(private val data: List<Data>) :
    RecyclerView.Adapter<TherapiesAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemTherapiesBinding) : RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.Q)
        fun bind(data: Data) {
            Glide.with(binding.root.context)
                .load(data.profile)
                .transition(DrawableTransitionOptions.withCrossFade())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.ic_launcher_foreground)
                .into(binding.ivImage)

            Glide
                .with(binding.root.context.applicationContext)
                .asBitmap()
                .load(data.profile)
                .into(object : SimpleTarget<Bitmap?>(100, 100) {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap?>?
                    ) {
                        resource.getColor(4,4).toArgb().let {
                            binding.ivImage.setBackgroundColor(it)
                        }
                    }
                })


            binding.tvTitle.text = data.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemTherapiesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

}