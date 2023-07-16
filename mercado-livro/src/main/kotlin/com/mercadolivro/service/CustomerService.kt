package com.mercadolivro.service

import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.enums.Errors
import com.mercadolivro.exception.NotFoundException
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.CustomerRepository
import org.springframework.stereotype.Service
import kotlin.Exception

@Service
class CustomerService(
    val customerRepository: CustomerRepository,
    val bookService: BookService
) {

    fun getAll(name: String?): List<CustomerModel> {
        /** Se colocarmos o ponto de interrogação após uma variável dizemos
         * que isso só será feito se esta variável não for nula
         *  O let vai rodar apenas de a variável for diferente de nula
         * */
        name?.let {
            return customerRepository.findByNameContaining(it)
        }
        return customerRepository.findAll().toList()
    }

    fun create(customer: CustomerModel) {
        customerRepository.save(customer)
    }

    fun findById(id: Int): CustomerModel {
        return customerRepository.findById(id).orElseThrow{NotFoundException(Errors.ML1101.message.format(id),Errors.ML1101.code)}
    }

    fun update(customer: CustomerModel) {
        if(!customerRepository.existsById(customer.id!!)){
            throw Exception()
        }

        customerRepository.save(customer)
    }

    fun delete(id: Int) {
        val customer = findById(id)
        bookService.deleteByCustomer(customer)
        customer.status = CustomerStatus.INATIVO
        customerRepository.save(customer)
    }

    fun emailAvailabe(email: String): Boolean {
        // tem que ser negado porque vai retornar falso se não existir
       return !customerRepository.existsByEmail(email)

    }

}