package com.giussepr.mercadolibretest.presentation.home

import android.os.Bundle
import android.os.Handler
import android.view.*
import android.widget.LinearLayout
import androidx.appcompat.widget.SearchView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.giussepr.mercadolibretest.R
import com.giussepr.mercadolibretest.databinding.FragmentHomeBinding
import com.giussepr.mercadolibretest.presentation.base.BaseBindingFragment
import com.giussepr.mercadolibretest.presentation.home.adapter.MercadoLibreItemAdapter
import com.giussepr.mercadolibretest.presentation.model.MercadoLibreItemUi
import com.giussepr.mercadolibretest.presentation.model.MercadoLibreItemUiItem
import com.giussepr.mercadolibretest.presentation.util.GlideImageLoader
import com.giussepr.mercadolibretest.presentation.util.KeyboardManager
import com.giussepr.mercadolibretest.presentation.util.ResourcesManager
import javax.inject.Inject


class HomeFragment : BaseBindingFragment<FragmentHomeBinding>(), HomeView,
    SearchView.OnQueryTextListener, MercadoLibreItemAdapter.MercadoLibreItemListener {

    @Inject lateinit var presenter: HomePresenter
    @Inject lateinit var resourcesManager: ResourcesManager
    @Inject lateinit var glideImageLoader: GlideImageLoader
    @Inject lateinit var keyboardManager: KeyboardManager

    private lateinit var adapter: MercadoLibreItemAdapter
    private lateinit var searchView: SearchView
    private var lastVisibleItemPosition = -1

    override fun bindView(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding =
        FragmentHomeBinding.inflate(inflater, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        presenter.onViewCreated()
    }

    override fun onDestroyView() {
        presenter.onDestroyView()
        super.onDestroyView()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home, menu)
        searchView = menu.findItem(R.id.item_menu_search).actionView as SearchView
        searchView.setIconifiedByDefault(true)
        searchView.setOnQueryTextListener(this)
    }

    override fun loadItems(mercadoLibreItems: MutableList<MercadoLibreItemUiItem>) {
        adapter.submitList(mercadoLibreItems.toMutableList())
    }

    override fun showLoading() {
        binding.pbLoading.isVisible = true
    }

    override fun hideLoading() {
        binding.pbLoading.isGone = true
    }

    override fun clearItems() {
        adapter.submitList(null)
    }

    override fun navigateToItemDetail(item: MercadoLibreItemUi) {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToItemDetailFragment(item))
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        keyboardManager.hideKeyboard(requireView())
        presenter.searchItem(query)
        return true
    }

    override fun onQueryTextChange(query: String): Boolean {
        // nothing to do
        return false
    }

    override fun onItemClicked(item: MercadoLibreItemUi) {
        presenter.onItemClicked(item)
    }

    private fun setupRecyclerView() {
        val layoutManager = binding.rvMercadoLibreItems.layoutManager as LinearLayoutManager

        binding.rvMercadoLibreItems.adapter =
            MercadoLibreItemAdapter(requireContext(), resourcesManager, glideImageLoader, this).also {
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
