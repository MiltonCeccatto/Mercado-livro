package com.mercadolivro.controller.response

data class PageResponse<T> (
    var itens: List<T> = listOf(),
    var totalPages: Int,
    var totalItens: Long,
    var currentPage: Int
)