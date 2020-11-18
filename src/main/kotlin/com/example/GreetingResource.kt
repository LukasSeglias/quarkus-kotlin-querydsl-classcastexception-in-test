package com.example

import com.querydsl.jpa.impl.JPAQueryFactory
import javax.inject.Inject
import javax.persistence.EntityManager
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/resteasy/hello")
class GreetingResource @Inject constructor(val em: EntityManager) {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    fun hello(): String {
        val qGreeting = QGreeting.greeting

        val queryFactory = JPAQueryFactory(em)

        val greeting = queryFactory
                .select(qGreeting.text)
                .from(qGreeting)
                .fetchFirst()

        return greeting ?: "Default greeting"
    }
}
