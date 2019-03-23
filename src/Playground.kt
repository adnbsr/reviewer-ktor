package com.reviewer

import com.reviewer.data.Review
import com.reviewer.data.Reviews
import com.reviewer.data.User
import com.reviewer.data.Users
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction


fun main(args: Array<String>) {
    println("hello")

    Database.connect("jdbc:postgresql://localhost:5432/rvr", driver = "org.postgresql.Driver", user = "adnbsr", password = "postgres")

    transaction {


        SchemaUtils.create(Users, Reviews)
//
//        val hasan = User.new {
//            name = "Hasan Basar"
//            username = "hsnbsr"
//        }
//
//        val r = Review.new {
//            title = "Title"
//            text = "Text"
//            user = hasan
//        }

        User.find { Users.username eq "hsnbsr" }.forEach { user ->
           println(user.reviews.toString())
        }

        val hsn = User[28]
        println(hsn.reviews.count())

    }

}