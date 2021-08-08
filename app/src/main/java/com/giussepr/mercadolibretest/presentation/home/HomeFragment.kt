package com.giussepr.mercadolibretest.presentation.home

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.giussepr.mercadolibretest.databinding.FragmentHomeBinding
import com.giussepr.mercadolibretest.presentation.base.BaseBindingFragment
import com.giussepr.mercadolibretest.presentation.home.adapter.MercadoLibreItemAdapter
import com.giussepr.mercadolibretest.presentation.model.MercadoLibreItemUiItem
import com.giussepr.mercadolibretest.presentation.util.GlideImageLoader
import com.giussepr.mercadolibretest.presentation.util.ResourcesManager
import javax.inject.Inject


class HomeFragment : BaseBindingFragment<FragmentHomeBinding>(), HomeView {

    @Inject lateinit var presenter: HomePresenter
    @Inject lateinit var resourcesManager: ResourcesManager
    @Inject lateinit var glideImageLoader: GlideImageLoader

    private lateinit var adapter: MercadoLibreItemAdapter
    private var lastVisibleItemPosition = -1

    override fun bindView(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding =
        FragmentHomeBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        presenter.onViewCreated()

        // TODO: Test code change it later
        presenter.searchItem("pinzas")
    }

    override fun loadItems(mercadoLibreItems: MutableList<MercadoLibreItemUiItem>) {
        adapter.submitList(mercadoLibreItems.toMutableList())
    }

    private fun setupRecyclerView() {
        val layoutManager = binding.rvMercadoLibreItems.layoutManager as LinearLayoutManager

        binding.rvMercadoLibreItems.adapter =
            MercadoLibreItemAdapter(requireContext(), resourcesManager, glideImageLoader).also {
                adapter = it
            }

        val itemDecoration = DividerItemDecoration(requireContext(), LinearLayout.VERTICAL)
        binding.rvMercadoLibreItems.addItemDecoration(itemDecoration)

        binding.rvMercadoLibreItems.addOnChildAttachStateChangeListener(object :
            RecyclerView.OnChildAttachStateChangeListener {
            override fun onChildViewAttachedToWindow(view: View) {
                // nothing to do
            }

            override fun onChildViewDetachedFromWindow(view: View) {
                val position = layoutManager.findLastCompletelyVisibleItemPosition()
                val handler = Handler()
                if (position > lastVisibleItemPosition) {
                    if (adapter.itemCount - position <= 5) {
                        handler.post {
                            presenter.loadNextPage()
                        }
                    }
                } else {
                    lastVisibleItemPosition = position
                }
            }
        })
    }
}
