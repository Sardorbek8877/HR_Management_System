package com.bek.hr_management_system.payload;

import com.bek.hr_management_system.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaryDto {

    @NotNull
    private UUID toUserId;

    @NotNull
    private Double monthlyAmount;

    @NotNull
    private Integer monthNumber;

    @NotNull
    private boolean isPaid;
}
