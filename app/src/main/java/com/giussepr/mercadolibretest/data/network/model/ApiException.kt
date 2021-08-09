package com.giussepr.mercadolibretest.data.network.model

class ApiException (val statusCode: Int, val errorCode: String): RuntimeException()
