package net.joyfulworld.findtheimage.ui.find

import android.util.Log
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import net.joyfulworld.base.data.remote.api.PixabaySearchAPI
import net.joyfulworld.base.data.remote.domain.HitImage
import net.joyfulworld.base.extension.with
import net.joyfulworld.findtheimage.util.NotNullMutableLiveData
import java.lang.Exception

class FindViewModel(private val api: PixabaySearchAPI) : ViewModel() {

    private val disposables: CompositeDisposable = CompositeDisposable()

    private var query: String = ""
        get() = if (field.isEmpty()) "LOVE" else field

    private val _refreshing: NotNullMutableLiveData<Boolean> = NotNullMutableLiveData(false)
    val refreshing: NotNullMutableLiveData<Boolean>
        get() = _refreshing

    private val _items: NotNullMutableLiveData<List<HitImage>> = NotNullMutableLiveData(arrayListOf())
    val items: NotNullMutableLiveData<List<HitImage>>
        get() = _items

    fun addToDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    fun doSearch() {
        val params = mutableMapOf<String, String>().apply {
            this["q"] = query
        }

        addToDisposable(api.search(params).with()
            .doOnSubscribe { _refreshing.value = true }
            .doOnSuccess { _refreshing.value = false }
            .doOnError { _refreshing.value = false }
            .subscribe({
                _items.value = it.hitImages
            }, {
                Log.e("FindViewModel", "error: $it")
            })
        )
    }

    fun onQueryChange(query: CharSequence) {
        this.query = query.toString()
    }

    fun showBigImage(item: HitImage) {
        val disposable = Observable.create((ObservableOnSubscribe<HitImage> { e->
            e.onNext(item)
            e.onError(Throwable("error: observable create error"))
        })).doOnSubscribe { _refreshing.value = true }
            .doOnError { _refreshing.value = false }
            .subscribe()
        addToDisposable(disposable)
    }
}