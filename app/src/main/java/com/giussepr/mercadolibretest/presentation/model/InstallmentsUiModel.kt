package com.giussepr.mercadolibretest.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class InstallmentsUiModel(
    val quantity: Int,
    val amount: Double
) : Parcelable
