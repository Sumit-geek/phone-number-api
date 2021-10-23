package com.belong.phone.models;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity(name = "customers")
@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class Customer {
    @Id
    private UUID id;
    @OneToMany(targetEntity=Phone.class, mappedBy="customer",
            cascade= CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Phone> phoneNos;

    @CreatedDate
    private LocalDateTime createdTime;
}
