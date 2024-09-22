package com.kasymzhan.springMongo.controller

import com.kasymzhan.springMongo.model.Task
import com.kasymzhan.springMongo.repository.TaskRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/tasks")
class TasksController {
    @Autowired
    private val _taskRepository: TaskRepository? = null

    @GetMapping
    fun getTasks(model: Model): String {
        val tasks = _taskRepository?.findAll() ?: emptyList()
        tasks.forEach { println(it) }
        model.addAttribute("tasks", tasks)
        return "tasks_list"
    }

    @GetMapping("/create")
    fun createTaskPage(): String {
        return "task_creation"
    }

    @PostMapping
    @ResponseBody
    fun addTask(@RequestBody task: Task): ResponseEntity<Map<String, String>> {
        println("creating task $task")
        _taskRepository?.save(task)
        return ResponseEntity(emptyMap(), HttpStatus.CREATED)
    }
}