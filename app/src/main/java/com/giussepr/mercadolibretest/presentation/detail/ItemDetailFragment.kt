package com.giussepr.mercadolibretest.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import androidx.viewpager.widget.ViewPager
import com.giussepr.mercadolibretest.R
import com.giussepr.mercadolibretest.databinding.FragmentItemDetailBinding
import com.giussepr.mercadolibretest.presentation.base.BaseBindingFragment
import com.giussepr.mercadolibretest.presentation.detail.adapter.ItemPicturesViewPagerAdapter
import com.giussepr.mercadolibretest.presentation.model.MercadoLibreItemUi
import com.giussepr.mercadolibretest.presentation.model.itemDetailInfo.ItemPictureUiModel
import com.giussepr.mercadolibretest.presentation.util.GlideImageLoader
import com.giussepr.mercadolibretest.presentation.util.ResourcesManager
import java.text.NumberFormat
import javax.inject.Inject

class ItemDetailFragment : BaseBindingFragment<FragmentItemDetailBinding>(), ItemDetailView {

    @Inject lateinit var presenter: ItemDetailPresenter
    @Inject lateinit var imageLoader: GlideImageLoader
    @Inject lateinit var resourcesManager: ResourcesManager

    private val args: ItemDetailFragmentArgs by navArgs()

    override fun bindView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentItemDetailBinding {
        return FragmentItemDetailBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvInstallments.post {
            presenter.onViewCreated(args.mercadoLibreItem)
        }
    }

    override fun setupItemDetailInfo(mercadoLibreItem: MercadoLibreItemUi, conditionSold: String) {
        binding.tvConditionSold.text = conditionSold
        binding.tvTitle.text = mercadoLibreItem.title
        imageLoader.load(mercadoLibreItem.thumbnail, binding.ivItemImage)

        val numberFormat = NumberFormat.getInstance()
        numberFormat.maximumFractionDigits = 0

        binding.tvPrice.text = resourcesManager.getString(R.string.mercado_libre_item_price, numberFormat.format(mercadoLibreItem.price))

        binding.tvInstallments.isVisible = mercadoLibreItem.installments != null
        mercadoLibreItem.installments?.let {
            binding.tvInstallments.text = resourcesManager.getString(R.string.item_detail_installments, it.quantity)
        }
        binding.tvStockAvailable.text = resourcesManager.getQuantityString(R.plurals.stock_available, mercadoLibreItem.availableQuantity, mercadoLibreItem.availableQuantity)
    }

    override fun setupViewPager(pictures: List<ItemPictureUiModel>) {
        val adapter = ItemPicturesViewPagerAdapter(requireContext(), pictures, imageLoader)
        binding.vpImages.adapter = adapter

        binding.ivItemImage.isInvisible = true
        binding.vpImages.isVisible = true

        binding.vpImages.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                presenter.onPageSelected(position)
            }
        })
    }

    override fun setViewPagerIndicator(indicator: String) {
        binding.tvViewPagerPicturesIndicator.isVisible = true
        binding.tvViewPagerPicturesIndicator.text = indicator
    }
}
