package com.oguzbzclr.task_tracker.controller;

import com.oguzbzclr.task_tracker.domain.dto.TaskDto;
import com.oguzbzclr.task_tracker.domain.entities.Task;
import com.oguzbzclr.task_tracker.mappers.TaskMapper;
import com.oguzbzclr.task_tracker.services.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/task-lists/{task_list_id}/tasks")
public class TasksController {
    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TasksController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @GetMapping
    public List<TaskDto> listTasks(@PathVariable("task_list_id") UUID taskListId){
        return taskService.listTasks(taskListId).stream()
                .map(taskMapper::toDto)
                .toList();
    }

    @PostMapping
    public TaskDto createTask(@PathVariable("task_list_id") UUID taskListId, @RequestBody TaskDto taskDto){
        Task createdTask = taskService.createTask(taskListId, taskMapper.fromDto(taskDto));
        return taskMapper.toDto(createdTask);

    }

    @GetMapping(path = "/{task_id}")
    public Optional<TaskDto> getTask(@PathVariable("task_list_id") UUID task_list_id, @PathVariable("task_id") UUID taskId){
        return taskService.getTask(task_list_id, taskId).map(taskMapper::toDto);

    }

    @PutMapping(path = "/{task_id}")
    public TaskDto updateTask(@PathVariable("task_list_id") UUID task_list_id,
                              @PathVariable("task_id") UUID taskId,
                              @RequestBody TaskDto taskDto){
        Task updatedTask = taskService.updateTask(task_list_id, taskId, taskMapper.fromDto(taskDto));
        return taskMapper.toDto(updatedTask);
    }

    @DeleteMapping("/{task_id}")
    public void deleteTask(@PathVariable("task_list_id") UUID task_list_id, @PathVariable("task_id") UUID taskId){
        taskService.deleteTask(task_list_id, taskId);
    }

}
