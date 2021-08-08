package com.giussepr.mercadolibretest.data.repository

import com.giussepr.mercadolibretest.data.network.api.MercadoLibreApi
import com.giussepr.mercadolibretest.domain.repository.MercadoLibreRepository
import javax.inject.Inject

class MercadoLibreRepositoryImpl @Inject constructor(
    private val mercadoLibreApi: MercadoLibreApi
) : MercadoLibreRepository {}
