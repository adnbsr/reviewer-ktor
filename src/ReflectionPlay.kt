package com.reviewer

import com.reviewer.data.User
import org.jetbrains.exposed.sql.SizedIterable
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.full.*

class Student(var surname: String) {
    val name: String = "Hasan"
}

fun main(args: Array<String>) {
    val klass: KClass<User> = User::class
    klass.declaredMemberProperties.forEach{ properties ->
        val stringType = String::class.starProjectedType
        if (properties.returnType == stringType) {
            println(properties.name)
        }
    }
}