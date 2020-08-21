package com.incred.photogallery.view.home

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.incred.photogallery.R
import com.incred.photogallery.di.components.DaggerHomeActivityComponents
import com.incred.photogallery.di.modules.HomeActivityContextModule
import com.incred.photogallery.services.model.DataWrapper
import com.incred.photogallery.services.model.Photo
import com.incred.photogallery.services.model.PhotosResult
import com.incred.photogallery.utility.SpaceItemDecoration
import com.incred.photogallery.view.OnListItemClick
import com.incred.photogallery.viewmodel.HomeActivityViewModel
import com.incred.photogallery.viewmodel.HomeActivityViewModelFactory
import javax.inject.Inject


class HomeActivity : AppCompatActivity(), OnListItemClick {

    override fun onListItemClicked(photo: Photo?) {
        Toast.makeText(activityContext, photo?.title, Toast.LENGTH_LONG).show()
    }

    @Inject
    lateinit var activityContext: HomeActivity
    @Inject
    lateinit var homeAdapter: HomeAdapter
    @Inject
    lateinit var mFactory: HomeActivityViewModelFactory

    lateinit var mHomeActivityViewModel: HomeActivityViewModel
    lateinit var gridLayoutManager: GridLayoutManager
    lateinit var photoRclView: RecyclerView
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeActivityCompatActivity =
            DaggerHomeActivityComponents.builder().homeActivityContextModule(
                HomeActivityContextModule(this)
            ).build()
        homeActivityCompatActivity.inject(this)

        initView()

        mHomeActivityViewModel =
            ViewModelProviders.of(this, mFactory).get(HomeActivityViewModel::class.java)
        homeAdapter.setViewModel(mHomeActivityViewModel)

        showProgressBar()
        mHomeActivityViewModel.fetchPhotos()

        mHomeActivityViewModel.getPhotos()?.observe(this, Observer<DataWrapper<PhotosResult>> {
            hideProgressBar()
            if (it.data?.photos?.photo?.isNotEmpty() == true) {
                homeAdapter.setData(it?.data.photos.photo)
            } else {
                Toast.makeText(activityContext, it?.errorObject?.error, Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun initView() {
        progressBar = findViewById(R.id.progress_circular)
        photoRclView = findViewById(R.id.rl_view)
        gridLayoutManager = GridLayoutManager(this, 3)
        photoRclView.layoutManager = gridLayoutManager
        val spacingInPixels =
            resources.getDimensionPixelSize(R.dimen.spacing)
        photoRclView.addItemDecoration(SpaceItemDecoration(spacingInPixels))
        photoRclView.adapter = homeAdapter

    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        if (progressBar.isVisible)
            progressBar.visibility = View.GONE

    }
}
