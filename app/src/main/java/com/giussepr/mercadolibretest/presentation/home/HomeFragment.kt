package com.giussepr.mercadolibretest.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import com.giussepr.mercadolibretest.databinding.FragmentHomeBinding
import com.giussepr.mercadolibretest.presentation.base.BaseBindingFragment
import com.giussepr.mercadolibretest.presentation.home.adapter.MercadoLibreItemAdapter
import com.giussepr.mercadolibretest.presentation.model.MercadoLibreItemUi
import com.giussepr.mercadolibretest.presentation.util.GlideImageLoader
import com.giussepr.mercadolibretest.presentation.util.ResourcesManager
import javax.inject.Inject


class HomeFragment : BaseBindingFragment<FragmentHomeBinding>(), HomeView {

    @Inject lateinit var presenter: HomePresenter
    @Inject lateinit var resourcesManager: ResourcesManager
    @Inject lateinit var glideImageLoader: GlideImageLoader

    private lateinit var adapter: MercadoLibreItemAdapter

    override fun bindView(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding =
        FragmentHomeBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        // TODO: Test code change it later
        presenter.searchItem("pinzas")
    }

    override fun loadItems(mercadoLibreItems: List<MercadoLibreItemUi>) {
        adapter.submitList(mercadoLibreItems)
    }

    private fun setupRecyclerView() {
        binding.rvMercadoLibreItems.adapter = MercadoLibreItemAdapter(requireContext(), resourcesManager, glideImageLoader).also {
            adapter = it
        }

        val itemDecoration = DividerItemDecoration(requireContext(), LinearLayout.VERTICAL)
        binding.rvMercadoLibreItems.addItemDecoration(itemDecoration)
    }
}
