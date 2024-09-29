package com.kasymzhan.springMongo.controller

import com.kasymzhan.springMongo.model.Task
import com.kasymzhan.springMongo.repository.TaskRepository
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import kotlin.math.log

@Controller
@RequestMapping("/tasks")
class TasksController {
    @Autowired
    private val _taskRepository: TaskRepository? = null

    @GetMapping
    fun getTasks(model: Model): String {
        val tasks = _taskRepository?.findAll() ?: emptyList()
        model.addAttribute("tasks", tasks)
        return "tasks_list"
    }

    @GetMapping("/create")
    fun createTaskPage(): String {
        return "task_creation"
    }

    @PostMapping("/create")
    @ResponseBody
    fun addTask(@RequestBody task: Task): HttpStatus {
        _taskRepository?.save(task)
        return HttpStatus.CREATED
    }

    @PutMapping("/{id}/{isChecked}")
    fun markTask(@PathVariable id: String, @PathVariable isChecked: Boolean): ResponseEntity<String> {
        val task = _taskRepository?.findByIdOrNull(ObjectId(id))
        return if (task != null) {
            task.isDone = isChecked
            _taskRepository?.save(task)
            ResponseEntity.ok("Task updated successfully")
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found")
        }
    }
}