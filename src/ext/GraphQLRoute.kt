package com.reviewer.ext

import com.reviewer.Schema
import com.reviewer.data.DatabaseServiceImpl
import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.response.respondText
import io.ktor.routing.Route
import io.ktor.locations.Location
import io.ktor.locations.post
import org.jetbrains.exposed.sql.Database

@Location("/graphql")
data class GraphQLRequest(val query: String = "", val variables: Map<String, Any>? = emptyMap())

fun Route.graphql() {

    val database = Database.connect(
        "jdbc:postgresql://localhost:5432/rvr",
        driver = "org.postgresql.Driver",
        user = "adnbsr",
        password = "postgres"
    )
    val databaseService = DatabaseServiceImpl(database)

    val appSchema = Schema(databaseService)

    post<GraphQLRequest> {

        val req  = call.receive<GraphQLRequest>()
        println(req.query)
        println(req.variables == null)

        try {
            val graphResult = appSchema.schema.execute(req.query, req.variables.toString() ?: null)
            call.respondText { graphResult }
        }catch (e: Exception) {
            call.respondText { e.localizedMessage }
        }
    }
}


