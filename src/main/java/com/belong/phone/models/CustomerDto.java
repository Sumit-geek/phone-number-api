package com.belong.phone.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CustomerDto {

  private UUID id;
  private List<PhoneDto> phoneNos;

  public static CustomerDto mapCustomer(Customer customer) {
    return CustomerDto.builder()
        .id(customer.getId())
        .phoneNos(customer.getPhoneNos()
                .stream().map(PhoneDto::mapPhoneNumber).collect(Collectors.toList()))
        .build();
  }
}
