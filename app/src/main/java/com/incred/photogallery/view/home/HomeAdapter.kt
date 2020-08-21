package com.incred.photogallery.view.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.incred.photogallery.R
import com.incred.photogallery.services.model.Photo
import com.incred.photogallery.services.model.Size
import com.incred.photogallery.utility.loadImage
import com.incred.photogallery.view.OnListItemClick
import com.incred.photogallery.viewmodel.HomeActivityViewModel

class HomeAdapter(private val activity: HomeActivity) :
    RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {

    private var data: List<Photo>? = null

    private var mViewModel: HomeActivityViewModel? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(activity).inflate(R.layout.item_photos, parent, false)
        return MyViewHolder(activity, view, mViewModel)
    }

    fun setData(list: List<Photo>) {
        data = list
        notifyDataSetChanged()
    }

    fun setViewModel(homeActivityViewModel: HomeActivityViewModel) {
        mViewModel = homeActivityViewModel
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindData(data?.get(position))
    }

    class MyViewHolder(
        private val activity: HomeActivity,
        view: View,
        mViewModel: HomeActivityViewModel?
    ) : RecyclerView.ViewHolder(view) {

        var thumbImage: ImageView = view.findViewById(R.id.item_image)
        private val homeActivityViewModel = mViewModel
        private var photo: Photo? = null

        init {
            itemView.setOnClickListener {
                (activity as OnListItemClick).onListItemClicked(photo)
            }

        }


        fun bindData(photos: Photo?) {
            this.photo = photos
            thumbImage.setImageBitmap(null)
            homeActivityViewModel?.fetchPhotoDetails(photos?.id ?: "")
            homeActivityViewModel?.getPhotoSizes()
                ?.observe(activity,
                    Observer<List<Size>> { t ->
                        if (t.isNotEmpty()) {
                            var imageUrl: String? = null
                            for (s in t) {
                                if (s.label == "Thumbnail") {
                                    imageUrl = s.source
                                    break
                                }
                            }
                            thumbImage.loadImage(imageUrl ?: t[0].source)
                        }
                    })
        }
    }
}