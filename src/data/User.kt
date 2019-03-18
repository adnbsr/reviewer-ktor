package com.reviewer.data

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object User: Table() {
    val id: Column<Int> = integer("id").primaryKey().autoIncrement()
    val name: Column<String> = varchar("name", 50)
    val username: Column<String> = varchar("username", 20) // Todo not-null
}
