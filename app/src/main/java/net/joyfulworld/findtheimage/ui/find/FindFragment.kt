package net.joyfulworld.findtheimage.ui.find

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import net.joyfulworld.findtheimage.R
import net.joyfulworld.findtheimage.databinding.FragmentFindBinding
import net.joyfulworld.findtheimage.ui.BindingFragment
import org.koin.androidx.viewmodel.ext.android.getViewModel

class FindFragment : BindingFragment<FragmentFindBinding>() {

    @LayoutRes
    override fun getLayoutResId() = R.layout.fragment_find

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding.vm = getViewModel()
        binding.lifecycleOwner = this

        return view
    }


}
