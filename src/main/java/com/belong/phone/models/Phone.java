package com.belong.phone.models;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "phone")
@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class Phone {

  @Id
  private UUID id;
  @ManyToOne()
  @JoinColumn(name="id")
  private Customer customer;
  private String phoneNo;
  private String status;

  @CreatedDate
  private LocalDateTime createdTime;
}
