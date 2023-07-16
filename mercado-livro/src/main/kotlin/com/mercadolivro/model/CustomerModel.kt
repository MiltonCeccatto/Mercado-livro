package com.mercadolivro.model

import com.mercadolivro.enums.CustomerStatus
import javax.persistence.*

@Entity(name = "customer")
data class CustomerModel(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column
    var name: String,

    @Column//caso o nome da coluna for diferente da variável  precisa ser especificado entre parenteses do Colunm
    var email: String,

    @Column
    @Enumerated(EnumType.STRING)
    var status: CustomerStatus


)