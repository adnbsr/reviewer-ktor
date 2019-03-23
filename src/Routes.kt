@file:JvmName("Routes")
package com.reviewer

import com.reviewer.ext.graphql
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.features.toLogString
import io.ktor.html.respondHtml
import io.ktor.http.ContentType
import io.ktor.http.content.default
import io.ktor.http.content.static
import io.ktor.locations.Location
import io.ktor.locations.locations
import io.ktor.locations.url
import io.ktor.request.queryString
import io.ktor.request.receive
import io.ktor.request.receiveText
import io.ktor.locations.post as locationPost
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import kotlinx.css.Color
import kotlinx.css.body
import kotlinx.css.em
import kotlinx.css.p
import kotlinx.html.body
import kotlinx.html.h1
import kotlinx.html.li
import kotlinx.html.ul

@Suppress("unused")
fun Application.routes() {
    routing {

        graphql()

        get("/verify") {
            call.respond("Verify")
        }

        get("/") {
            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
        }

        get("/html-dsl") {
            call.respondHtml {
                body {
                    h1 { +"HTML" }
                    ul {
                        for (n in 1..10) {
                            li { +"$n" }
                        }
                    }
                }
            }
        }

        get("/styles.css") {
            call.respondCss {
                body {
                    backgroundColor = Color.red
                }
                p {
                    fontSize = 2.em
                }
                rule("p.myclass") {
                    color = Color.blue
                }
            }
        }

        static("/graphql") {
            default("index.html")
        }

    }
}