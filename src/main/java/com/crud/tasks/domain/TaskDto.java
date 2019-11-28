package com.crud.tasks.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class TaskDto {
    private Long id;
    private String title;
    private String content;
}
