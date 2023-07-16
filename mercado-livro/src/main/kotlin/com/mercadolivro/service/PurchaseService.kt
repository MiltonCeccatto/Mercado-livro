package com.mercadolivro.service

import com.mercadolivro.event.PurchaseEvent
import com.mercadolivro.model.PurchaseModel
import com.mercadolivro.repository.PurchaseRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class PurchaseService(
        private val purchaseRepository: PurchaseRepository,
        private val applicationEventPublicher: ApplicationEventPublisher,
) {
        fun create(purchaseModel: PurchaseModel){
                purchaseRepository.save(purchaseModel)
                // applicationEventPublicher serve para disparar um evento
                // this é quem esta disparando esse evento
                applicationEventPublicher.publishEvent(PurchaseEvent(this, purchaseModel))
        }

        fun update(purchaseModel: PurchaseModel) {
                purchaseRepository.save(purchaseModel)
        }
}
