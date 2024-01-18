package com.theartemis.fusion.utility

import com.theartemis.fusion.data.TeamEntity

object DataDummy {

    fun generateDummyTeams(): List<TeamEntity> {
        val teams = mutableListOf<TeamEntity>()

        repeat(5) {
            val teamId = "TeamId-$it"
            val team = TeamEntity(
                teamId = teamId,
                description = "Deskripsi tim ke-$it",
                title = "Tim $it",
                teamImg = "image-$it.png",
                members = generateDummyMembers(),
                projects = mapOf(
                    "project1" to generateDummyProject(teamId, "Project 1")
                )
            )
            teams.add(team)
        }

        return teams
    }

    private fun generateDummyMembers(): Map<String, TeamEntity.MemberEntity> {
        val members = mutableMapOf<String, TeamEntity.MemberEntity>()

        repeat(2) {
            val userId = "UserId-$it"
            val member = TeamEntity.MemberEntity(
                userId = userId,
                displayName = "Anggota $it"
            )
            members[userId] = member
        }

        return members
    }

    private fun generateDummyProject(teamId: String, projectName: String): TeamEntity.ProjectEntity {
        return TeamEntity.ProjectEntity(
            projectId = "ProjectId-$teamId",
            projectDescription = "Deskripsi Proyek $teamId",
            projectName = projectName,
            projectSdg = "SDG Proyek $teamId",
            projectStatus = "Status Proyek $teamId",
            projectTasks = generateDummyTasks()
        )
    }

    private fun generateDummyTasks(): Map<String, String> {
        val tasks = mutableMapOf<String, String>()

        repeat(2) {
            val taskId = "TaskId-$it"
            tasks[taskId] = "Tugas $it"
        }

        return tasks
    }


}