package com.example

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.request.receive
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable


@Serializable
data class Task (val id: Int, val content: String, val isDone : Boolean)

@Serializable
data class TaskRequest(val content: String , val isDone : Boolean)

object TaskRepository {
    private val tasks = mutableListOf<Task>(
        Task(id = 1, content = "Learn Kotlin", isDone = true),
        Task(id = 2, content = "Build a REST API", isDone = false),
        Task(id = 3, content = "Write Unit Tests", isDone = false)
    )
    fun getall() : List<Task> = tasks

}

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello 672110148 sorawit !")
        }
        get("/tasks") {
            val tasks = TaskRepository.getall()
            call.respond(tasks)
            val status = call.request.queryParameters["status"]
            call.respondText("Fetched all tasks with status $status")
        }
    }
}