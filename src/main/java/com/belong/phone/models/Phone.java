package com.belong.phone.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "phone_numbers")
@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class Phone {

  @Id
  private UUID id;
  @ManyToOne()
  @JoinColumn(name="customer_id")
  private Customer customer;
  @Valid
  private String phoneNo;
  @Enumerated(EnumType.STRING)
  private Status status;

  @CreatedDate
  private LocalDateTime createdTime;
}
