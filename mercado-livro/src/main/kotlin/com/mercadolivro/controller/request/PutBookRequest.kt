package com.mercadolivro.controller.request

import java.math.BigDecimal

data class PutBookRequest(
    /** como eles podem ser vazios não precisa ser adicionada uma validação */
    var name: String?,

    var price: BigDecimal?
)
