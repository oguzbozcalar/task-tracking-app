package com.oguzbzclr.task_tracker.mappers;

import com.oguzbzclr.task_tracker.domain.dto.TaskDto;
import com.oguzbzclr.task_tracker.domain.entities.Task;

public interface TaskMapper {
    Task fromDto(TaskDto taskDto);
    TaskDto toDto(Task task);

}
