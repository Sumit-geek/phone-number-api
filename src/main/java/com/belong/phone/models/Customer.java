package com.belong.phone.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
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
