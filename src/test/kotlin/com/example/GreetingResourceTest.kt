package com.example

import com.github.database.rider.cdi.api.DBRider
import com.github.database.rider.core.api.dataset.DataSet
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
@DBRider
class GreetingResourceTest {

    @DataSet(value = ["greetings.yml"])
    @Test
    fun testHelloEndpoint() {
        given()
          .`when`().get("/resteasy/hello")
          .then()
             .statusCode(200)
             .body(`is`("Hello World"))
    }

}
