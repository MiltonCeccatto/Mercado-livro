package com.mercadolivro.exception

import com.mercadolivro.controller.response.ErrorResponse
import com.mercadolivro.controller.response.FieldErrorResponse
import com.mercadolivro.enums.Errors
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.AccessDeniedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest


@ControllerAdvice
class ControllerAdvice {

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(ex: NotFoundException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val erro =  ErrorResponse(
            httpCode = HttpStatus.NOT_FOUND.value(),
            message = ex.message,//podemos nos mesmos passar a mensagem e criar um error code para que possamos identificar o erro
            internalCode = ex.errorCode,
            errors = null
        )

        return ResponseEntity(erro, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequestException(ex: BadRequestException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val erro =  ErrorResponse(
            httpCode = HttpStatus.BAD_REQUEST.value(),
            message = ex.message,
            internalCode = ex.errorCode,
            errors = null
        )

        return ResponseEntity(erro, HttpStatus.BAD_REQUEST)
    }


    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(ex: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        val erro =  ErrorResponse(
            httpCode = HttpStatus.UNPROCESSABLE_ENTITY.value(),
            Errors.ML0001.message,
            Errors.ML0001.code,
            errors = ex.bindingResult.fieldErrors.map{ FieldErrorResponse(
                it.defaultMessage ?: "Invalid",
                it.field
            ) }
        )

        return ResponseEntity(erro, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(AccessDeniedException::class)
    fun handleAccessDeniedException(ex: AccessDeniedException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val erro =  ErrorResponse(
            httpCode = HttpStatus.FORBIDDEN.value(),
            message = Errors.ML0000.message,//podemos nos mesmos passar a mensagem e criar um error code para que possamos identificar o erro
            internalCode = Errors.ML0000.code,
            errors = null
        )

        return ResponseEntity(erro, HttpStatus.FORBIDDEN)
    }

}