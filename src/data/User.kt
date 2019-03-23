package com.reviewer.data

import org.jetbrains.exposed.dao.*

interface IUser{
    val name: String
    val username: String
    val reviews: List<IReview>
}

object Users: IntIdTable() {
    val name = varchar("name", 50)
    val username = varchar("username", 20).uniqueIndex() // Todo not-null
}

class User(id: EntityID<Int>): IntEntity(id), GraphConverter<IUser> {

    companion object: IntEntityClass<User>(Users)

    var name by Users.name
    var username by Users.username
    val reviews by Review referrersOn Reviews.user

    override fun toGraphObject(): IUser {
        return object : IUser {
            override val name = this@User.name
            override val username = this@User.username
            override val reviews: List<IReview> = this@User.reviews.map { r -> r.toGraphObject() }
        }
    }
}