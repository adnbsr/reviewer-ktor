package com.reviewer.data

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable

interface IReview {
    val title: String
    val user: IUser
}

object Reviews: IntIdTable() {
    val title = varchar("title", 50)
    val text = varchar("text",600)
    val user = reference("user", Users)
}

class Review(id: EntityID<Int>): IntEntity(id), GraphConverter<IReview> {

    companion object : IntEntityClass<Review>(Reviews)

    var title by Reviews.title
    var text by Reviews.text
    var user by User referencedOn Reviews.user

    override fun toGraphObject(): IReview {

        println(this@Review.user)

        return object : IReview {
            override val title = this@Review.title
            override val user: IUser = this@Review.user.toGraphObject()
        }
    }
}
