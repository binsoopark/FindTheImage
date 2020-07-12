package net.joyfulworld.findtheimage.ui.find

import androidx.lifecycle.ViewModel
import net.joyfulworld.base.data.remote.api.PixabaySearchAPI

class FindViewModel(private val api: PixabaySearchAPI) : ViewModel() {

//    private val _text = MutableLiveData<String>().apply {
//        value = "This is find Fragment"
//    }
//    val text: LiveData<String> = _text
    private var query: String = ""
        get() = if (field.isEmpty()) "LOVE" else field

    private val _refreshing: NotNullMutableLiveData
}