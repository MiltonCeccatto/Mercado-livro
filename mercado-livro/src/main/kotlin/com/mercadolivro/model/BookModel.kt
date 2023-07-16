package com.mercadolivro.model

import com.mercadolivro.enums.BookStatus
import com.mercadolivro.enums.Errors
import com.mercadolivro.exception.BadRequestException
import java.math.BigDecimal
import javax.persistence.*

@Entity(name = "book")
data class BookModel(
    /** Este é o próprio construtor da classe*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column//caso o nome da coluna for diferente da variável  precisa ser especificado entre parenteses do Colunm
    var name: String,

    @Column
    var price: BigDecimal,// bigdecimal é próprio para se trabalhar com valores monetários

    @ManyToOne
    @JoinColumn(name = "customer_id")
    var customer: CustomerModel? = null

){
    @Column
    @Enumerated(EnumType.STRING)
    var status: BookStatus? = null
        set(value){
            if(field == BookStatus.CANCELADO || field == BookStatus.DELETADO){
                throw BadRequestException(Errors.ML1002.message.format(field),Errors.ML1002.code)
            }
            field = value
        }

    constructor(
        id: Int? = null,
        name: String,
        price: BigDecimal,
        customer: CustomerModel? = null,
        status: BookStatus?
    ): this(id, name, price, customer){
        this.status = status
    }
}