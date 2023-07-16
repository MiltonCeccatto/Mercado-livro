package com.mercadolivro.validation

import com.mercadolivro.service.CustomerService
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

//ConstraintValidator<EmailAvailable, String>
// nesta linha estamos dizendo que o tipo a ser validado é uma string
class EmailAvailableValidator( var customerService: CustomerService): ConstraintValidator<EmailAvailable, String> {
    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        if(value.isNullOrEmpty()) {
            return false
        }
        return customerService.emailAvailabe(value)

    }

}
