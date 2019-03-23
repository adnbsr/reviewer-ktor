package com.reviewer

import com.github.pgutkowski.kgraphql.KGraphQL
import com.reviewer.data.DatabaseService
import com.reviewer.data.IReview
import com.reviewer.data.IUser

class Schema(val service: DatabaseService) {

    val schema = KGraphQL.schema {

        configure {
            useDefaultPrettyPrinter = true
        }

        query("users") {

            description = "List all users"

            resolver { ->
                service.getUsers()
            }
        }

        query("reviews") {

            resolver { ->
                service.getReviews()
            }
        }

        // TYPES
        type<IUser> {
            description = "User Type"
        }

        type<IReview>()

    }
}