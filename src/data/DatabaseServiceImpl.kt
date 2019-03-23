package com.reviewer.data

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction

class DatabaseServiceImpl(val database: Database) : DatabaseService {

    override fun getReviews(): List<IReview> {
        return transaction(database) {
            Review
                .all()
                .map { review -> review.toGraphObject() }
                .toList()
        }
    }

    override fun getUsers(): List<IUser> {
        return transaction(database) {
            User
                .all()
                .map { user -> user.toGraphObject() }
                .toList()
        }
    }
}