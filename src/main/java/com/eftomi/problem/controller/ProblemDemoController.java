package com.eftomi.problem.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Contact;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eftomi.problem.dto.Task;
import com.eftomi.problem.problems.TaskNotFoundProblem;

@RestController
@RequestMapping("/tasks")
public class ProblemDemoController {

    private static final Map<Long, Task> MY_TASKS;

    static {
        MY_TASKS = new HashMap<>();
        MY_TASKS.put(1L, new Task(1L, "My first task"));
        MY_TASKS.put(2L, new Task(2L, "My second task"));
    }

    @ApiOperation(value = "List of tasks", notes = "", response = Contact.class)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Task> getTasks() {
        return new ArrayList<>(MY_TASKS.values());
    }

    @ApiOperation(value = "Find task by id", notes = "Response with problem library", response = Contact.class)
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Task getTasks(@ApiParam(value = "ID value for the task you find", required = true)
                             @PathVariable("id") Long taskId) {
        if (MY_TASKS.containsKey(taskId)) {
            return MY_TASKS.get(taskId);
        } else {
            throw new TaskNotFoundProblem(taskId);  //This is from problem library.
        }
    }

    @ApiOperation(value = "Put item into list", notes = "", response = Contact.class)
    @PutMapping("/{id}")
    public void updateTask(@ApiParam(value = "ID value for the task you want to add the list", required = true)
                               @PathVariable("id") Long id) {
        throw new UnsupportedOperationException();
    }

    @ApiOperation(value = "Delete list", notes = "", response = Contact.class)
    @DeleteMapping("/{id}")
    public void deleteTask(@ApiParam(value = "Value of id to delete", required = true)
            @PathVariable("id") Long id) {
        throw new AccessDeniedException("You can't delete this task");
    }

}
