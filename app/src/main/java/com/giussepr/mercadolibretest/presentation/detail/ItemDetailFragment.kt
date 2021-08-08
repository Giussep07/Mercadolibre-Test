package com.giussepr.mercadolibretest.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.giussepr.mercadolibretest.databinding.FragmentItemDetailBinding
import com.giussepr.mercadolibretest.presentation.base.BaseBindingFragment

class ItemDetailFragment : BaseBindingFragment<FragmentItemDetailBinding>(), ItemDetailView {

    private val args: ItemDetailFragmentArgs by navArgs()

    override fun bindView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentItemDetailBinding {
        return FragmentItemDetailBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvTitle.text = args.mercadoLibreItem.title
    }

}
