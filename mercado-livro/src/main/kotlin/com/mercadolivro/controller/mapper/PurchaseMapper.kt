package com.mercadolivro.controller.mapper

import com.mercadolivro.controller.request.PostPurchaseRequest
import com.mercadolivro.model.PurchaseModel
import com.mercadolivro.service.BookService
import com.mercadolivro.service.CustomerService
import org.springframework.stereotype.Component

// usamos o componente pois a classe vai funcionar como um service porém ela não é de fato um service
// e precisamos que o spring saiba o que fazer com a classe
@Component
class PurchaseMapper(
    private val bookService: BookService,
    private val customerService: CustomerService
) {
    fun toModel(request: PostPurchaseRequest): PurchaseModel {
        val customer = customerService.findById(request.customerId)
        val books = bookService.findAllByIds(request.bookIds)
        return PurchaseModel(
            customer = customer,
            books = books.toMutableList(),
            price = books.sumOf { it.price },
        )
    }
}