package com.example

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
@Table
class Greeting {

    @Column(name = "text", length = 100)
    var text: String? = null

    @Column(name = "id", length = 36)
    @Id
    @GeneratedValue(generator = "GreetingPKGen")
    @GenericGenerator(name = "GreetingPKGen", strategy = "uuid2")
    var id: String? = null
}
