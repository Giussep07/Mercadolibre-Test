package com.giussepr.mercadolibretest.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.giussepr.mercadolibretest.databinding.FragmentHomeBinding
import com.giussepr.mercadolibretest.presentation.base.BaseBindingFragment
import javax.inject.Inject

class HomeFragment : BaseBindingFragment<FragmentHomeBinding>(), HomeView {

    @Inject
    lateinit var presenter: HomePresenter

    override fun bindView(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding =
        FragmentHomeBinding.inflate(inflater, container, false)
}
