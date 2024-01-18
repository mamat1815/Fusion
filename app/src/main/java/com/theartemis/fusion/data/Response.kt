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

) {
    constructor() : this("", "", "", "")
}

@Parcelize
data class TeamEntity(
    val teamId: String,
    val description: String,
    val title: String,
    val teamImg: String,
    val members: Map<String, MemberEntity>,
    val projects: Map<String, ProjectEntity>
) : Parcelable {
    @Parcelize
    data class MemberEntity(
        val userId: String,
        val displayName: String
    ) : Parcelable {
        constructor() : this("", "")
    }

    @Parcelize
    data class ProjectEntity(
        val projectId: String,
        val projectDescription: String,
        val projectName: String,
        val projectSdg: String,
        val projectStatus: String,
        val projectTasks: Map<String, String>
    ) : Parcelable {
        constructor() : this("", "", "", "", "", mapOf())
    }
}


