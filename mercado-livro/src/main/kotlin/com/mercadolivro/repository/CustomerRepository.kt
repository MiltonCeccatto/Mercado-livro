package com.mercadolivro.repository

import com.mercadolivro.model.CustomerModel
import org.springframework.data.repository.CrudRepository
/** A tipagem da classe mostra o primeiro parametro que vai ser nossa entity e o segundo é o tipo do nosso id */
interface CustomerRepository : CrudRepository<CustomerModel, Int>{

    /** Pesquisar mais em spring like queries */
    fun findByNameContaining(name: String): List<CustomerModel>
    fun existsByEmail(email: String): Boolean

}