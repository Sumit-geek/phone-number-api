package com.belong.phone.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class PhoneDto {

  private String phoneNo;
  private String status;

  public static PhoneDto mapPhoneNumber(Phone phoneNumber) {
    return PhoneDto.builder()
        .phoneNo(phoneNumber.getPhoneNo())
        .status(phoneNumber.getStatus())
        .build();
  }
}
