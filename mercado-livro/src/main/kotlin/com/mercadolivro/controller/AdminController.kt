package com.mercadolivro.controller

import org.springframework.web.bind.annotation.*

/**
 * Link para os tipos de retorno de uma API
 * https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status */
@RestController
@RequestMapping("admin")
class AdminController() {

    @GetMapping("/report")
    fun report(): String {
        return "This is a report. Only admin can see it!"
    }
    }
