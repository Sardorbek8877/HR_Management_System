package com.bek.hr_management_system.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {

    private String taskName;

    private String description;

    private Date deadline;

    private Integer employeeId;
}
