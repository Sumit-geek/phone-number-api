package com.belong.phone.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CustomerDto {

  private UUID id;
  private List<Phone> phoneNos;

  public static CustomerDto mapCustomer(Customer customer) {
    return CustomerDto.builder()
        .id(customer.getId())
        .phoneNos(customer.getPhoneNos())
        .build();
  }
}
