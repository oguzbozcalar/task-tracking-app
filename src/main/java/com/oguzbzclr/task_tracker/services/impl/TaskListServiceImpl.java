package com.oguzbzclr.task_tracker.services.impl;

import com.oguzbzclr.task_tracker.domain.entities.TaskList;
import com.oguzbzclr.task_tracker.repositories.TaskListRepository;
import com.oguzbzclr.task_tracker.services.TaskListService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskListServiceImpl implements TaskListService {

    private final TaskListRepository taskListRepository;

    public TaskListServiceImpl(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<TaskList> listTaskLists() {
        return taskListRepository.findAll();
    }

    @Override
    public TaskList createTaskList(TaskList taskList) {
        if (null != taskList.getId()){
            throw new IllegalArgumentException("Task list already has an ID !");
        }
        if(null == taskList.getTitle() || taskList.getTitle().isBlank()){
            throw new IllegalArgumentException("Task list title must be present !");
        }
        LocalDateTime now = LocalDateTime.now();

        return taskListRepository.save(new TaskList(
                null,
                taskList.getTitle(),
                taskList.getDescription(),
                now,
                now,
                null


        ));

    }

    @Override
    public Optional<TaskList> getTaskList(UUID id) {
        return taskListRepository.findById(id);

    }

    @Override
    public TaskList updateTaskList(UUID taskListId, TaskList taskList) {
         if(null == taskList.getId()){
             throw new IllegalArgumentException("Task list must have an ID");
         }

         if(!Objects.equals(taskList.getId(), taskListId)){
             throw new IllegalArgumentException("Attempting to change task list ID, this is not permitted.");
         }

         TaskList existingTaskList = taskListRepository.findById(taskListId).orElseThrow(() ->
                 new IllegalArgumentException("Task List not found."));
         existingTaskList.setTitle(taskList.getTitle());
         existingTaskList.setDescription(taskList.getDescription());
         existingTaskList.setUpdated(LocalDateTime.now());

         return taskListRepository.save(existingTaskList);

    }

    @Override
    public void deleteTaskList(UUID taskListId) {
        taskListRepository.deleteById(taskListId);
    }


}
