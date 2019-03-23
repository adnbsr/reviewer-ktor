package com.reviewer.data

interface DatabaseService {
    fun getUsers() : List<IUser>
    fun getReviews(): List<IReview>
}