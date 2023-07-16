package com.mercadolivro.enums

enum class Errors(val code: String, val message:String) {
    /** Deve haver um range de erros
     * entre 1000 e 1100 são referentes a book
     * entre 1100 e 1200 são referentes a customer
     * */
    ML0001("ML-0001", "Invalid Request"),
    ML1001("ML-1001", "Book [%s] not exists"),
    ML1002("ML-1002", "Cannot update book with status [%s]"),
    ML1101("ML-1101", "Customer [%s] not exists")
}