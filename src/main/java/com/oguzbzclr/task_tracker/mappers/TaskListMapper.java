package com.oguzbzclr.task_tracker.mappers;

import com.oguzbzclr.task_tracker.domain.dto.TaskListDto;
import com.oguzbzclr.task_tracker.domain.entities.Task;
import com.oguzbzclr.task_tracker.domain.entities.TaskList;

public interface TaskListMapper {
    TaskList fromDto(TaskListDto taskListDto);
    TaskListDto toDto(TaskList taskList);
}
