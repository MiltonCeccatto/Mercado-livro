package com.mercadolivro.event.listeners

import com.mercadolivro.event.PurchaseEvent
import com.mercadolivro.service.BookService
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class UpdateSouldBookListener(
    private val bookService: BookService
) {

    @Async
    @EventListener
    fun listen(purchaseEvent: PurchaseEvent){
        bookService.purchase(purchaseEvent.purchaseModel.books)
    }
}