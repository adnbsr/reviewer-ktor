package com.reviewer

import com.reviewer.data.User
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction




fun main(args: Array<String>) {
    println("hello")

    Database.connect("jdbc:postgresql://localhost:5432/rvr", driver = "org.postgresql.Driver", user = "adnbsr", password = "postgres")

    transaction {


        SchemaUtils.create(User)

//        User.insert {
//            it[name] = "Bekir Basar"
//            it[username] = "bkr"
//        }

        User.select {User.username eq "bkr"}.forEach { row ->
            println(row[User.username])
        }

    }

}