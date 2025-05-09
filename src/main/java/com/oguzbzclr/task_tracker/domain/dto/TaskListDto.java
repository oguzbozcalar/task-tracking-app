package com.oguzbzclr.task_tracker.domain.dto;

import com.oguzbzclr.task_tracker.domain.entities.Task;

import java.util.List;
import java.util.UUID;

public record TaskListDto(
        UUID id,
        String title,
        String description,
        Integer count,
        Double progress,
        List<TaskDto> tasks
) {
}
