package com.mercadolivro.model

import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.enums.Role
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
    var status: CustomerStatus,

    @Column
    val password: String,

    @CollectionTable(name = "customer_roles", joinColumns =[JoinColumn(name = "customer_id")] )// é uma tabela que não tem id
    @ElementCollection(targetClass = Role::class, fetch = FetchType.EAGER)// FetchType.EAGER isso quer dizer que toda vez que vc buscar um customer vc vai trazer estes dados também
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    var roles: Set<Role> = setOf()
)