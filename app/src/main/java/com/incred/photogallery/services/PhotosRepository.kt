package com.incred.photogallery.services

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.incred.photogallery.PhotosApplication
import com.incred.photogallery.services.model.*
import com.incred.photogallery.utility.Constants
import com.incred.photogallery.utility.isNetworkConnected
import io.reactivex.CompletableObserver
import io.reactivex.MaybeObserver
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class PhotosRepository @Inject constructor() {

    private val TAG = "PhotosRepository"
    private val disposable: CompositeDisposable = CompositeDisposable()

    /**
     * This method is used for fetching data for images,
     * Return type is live data holder for Data wrapper to photoresult type
     */
    private fun fetchData(photosResultLiveDat: MutableLiveData<DataWrapper<PhotosResult>>) {
        PhotosApplication.getApp().apiInterface.getPhotos(
            Constants.API_KEY,
            Constants.USER_ID,
            "json",
            1
        ).subscribeOn(Schedulers.io())
            .doOnSuccess { if (it.stat == "ok") savePhotos(it.photos) }
            .map(object : Function<PhotosResult, DataWrapper<PhotosResult>> {
                override fun apply(p0: PhotosResult): DataWrapper<PhotosResult> {
                    return if (p0.stat == "ok") {
                        DataWrapper(p0, null)
                    } else {
                        DataWrapper(
                            null,
                            ErrorObject("Something went wrong")
                        )
                    }
                }
            })
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<DataWrapper<PhotosResult>> {
                override fun onSubscribe(d: Disposable) {
                    disposable.add(d)
                }

                override fun onError(e: Throwable) {
                    photosResultLiveDat.value =
                        DataWrapper(null, ErrorObject("java.net.UnknownHostException: Unable to resolve host \"api.flickr.com\": No address associated with hostname"))
                }

                override fun onSuccess(t: DataWrapper<PhotosResult>) {
                    photosResultLiveDat.value = t
                }

            })


    }

    /**
     * This method is used for fetching photos, first it checks if internet connected then it
     * fetches from server and store in db, else it fetches from db.
     */
    fun fetchPhotos(): MutableLiveData<DataWrapper<PhotosResult>> {
        val photosResultLiveData: MutableLiveData<DataWrapper<PhotosResult>> = MutableLiveData()
        if (PhotosApplication.getApp().isNetworkConnected()) {
            fetchData(photosResultLiveData)
        } else {
            PhotosApplication.getApp().database.photoDaoAccess().fetchAllPhotos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : MaybeObserver<List<Photos>> {
                    override fun onSuccess(t: List<Photos>) {
                        if (t.isEmpty()) {
                            fetchData(photosResultLiveData)
                        } else {
                            photosResultLiveData.value = DataWrapper(PhotosResult(t[0], "ok"), null)
                        }
                    }

                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable) {
                        disposable.add(d)
                    }

                    override fun onError(e: Throwable) {
                        photosResultLiveData.value =
                            DataWrapper(null, ErrorObject("Something Went wrong"))

                    }

                })
        }

        return photosResultLiveData
    }


    /**
     * Save photos in database
     */
    private fun savePhotos(photo: Photos) {
        val d: Disposable =
            PhotosApplication.getApp().database.photoDaoAccess().savePhotoResults(photo)
                .subscribeOn(Schedulers.io())
                .subscribe()
        disposable.add(d)
    }

    /**
     * This method is for fetching all the photos in all available photo sizes by passing the
     * id of that photo
     * First it will try to fetch photos from photo sizes datatable from db_photo_sizes database.
     * if it get data from db it update the view using livedata holder else it will fetch from
     * remote server for fetching the data.
     */
    fun fetchPhotosById(id: String): MutableLiveData<List<Size>> {
        val liveData: MutableLiveData<List<Size>> = MutableLiveData()
        PhotosApplication.getApp().database.photoDaoAccess().fetchPhotoById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : MaybeObserver<List<Size>> {
                override fun onSuccess(t: List<Size>) {
                    if (t.isEmpty()) {
                        fetchPhotoDetailsFromServer(liveData, id)
                    } else {
                        liveData.value = t
                    }
                }

                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                    disposable.add(d)
                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, "onError: ${e.localizedMessage}")
                }

            })
        return liveData

    }

    /**
     * This method is used for saving the photo in all available sizes in database.
     * it takes List of available sizes and id for that photo
     */
    private fun savePhotoSizes(photoSizesResult: List<Size>, id: String) {
        for (size in photoSizesResult) {
            size.photoId = id
            PhotosApplication.getApp().database.photoDaoAccess().savePhotoSizeResults(size)
                .subscribeOn(Schedulers.io())
                .subscribe(object : CompletableObserver {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {
                        disposable.add(d)
                    }

                    override fun onError(e: Throwable) {
                        Log.d(TAG, "onError: ${e.localizedMessage}")
                    }

                })

        }
    }

    /**
     * This is for fetching the photo details from server and
     * return livedata holder for list of available sizes
     */
    private fun fetchPhotoDetailsFromServer(
        photoDetailsResult: MutableLiveData<List<Size>>,
        id: String
    ) {

        PhotosApplication.getApp().apiInterface.fetchPhotoDetails(
            id,
            Constants.API_KEY,
            Constants.USER_ID,
            "json",
            1
        ).subscribeOn(Schedulers.io())
            .doOnSuccess { t -> if (t.stat == "ok") savePhotoSizes(t.sizes.size, id) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<PhotoSizesResult> {
                override fun onSuccess(t: PhotoSizesResult) {
                    if (t.stat == "ok") {
                        photoDetailsResult.value = t.sizes.size
                    }
                }

                override fun onSubscribe(d: Disposable) {
                    disposable.add(d)
                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, "onError: ${e.localizedMessage}")
                }

            })
    }

    /**
     * for disposing all the disposable of rxjava
     */
    fun onDispose() {
        disposable.dispose()
    }
}