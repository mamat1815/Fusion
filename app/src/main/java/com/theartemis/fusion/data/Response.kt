package com.theartemis.fusion.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val uid: String,
    val displayName: String,
    val profileImg: String,
): Parcelable

@Parcelize
data class Post(
    val id: String,
    val userId: String,
    val sdgs: String,
    val title: String,
    val description: String,
    val img: String,
    val username: String,
    val userImg: String
) : Parcelable {
    constructor() : this("", "", "", "", "","","","")
}

data class Comment (
    val id: String,
    val uidPost: String,
    val userId: String,
    val comment: String,

)
