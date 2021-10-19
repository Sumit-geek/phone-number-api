package com.belong.phone.unit.services;

import com.belong.phone.common.HttpHeader;
import com.belong.phone.exceptions.DataNotFoundException;
import com.belong.phone.models.Customer;
import com.belong.phone.models.CustomerDto;
import com.belong.phone.models.Phone;
import com.belong.phone.repository.CustomerRepo;
import com.belong.phone.services.CustomerService;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

  @InjectMocks
  private CustomerService customerService;

  private final UUID uuid = UUID.randomUUID();

  @Mock
  private CustomerRepo customerRepo;

  @Test
  void getPhoneNumberForCustomerId_thenReturnAllPhoneNumbersForTheCustomer() {

    Optional<Customer> customer = createCustomer();
    when(customerRepo.findById(any())).thenReturn(customer);
    HttpHeaders httpHeaders = getHttpHeaders();
    assertThat(customerService.getPhoneNumberForCustomer(httpHeaders, uuid.toString()).getPhoneNos().size(), is(2));
  }

  @Test
  void activatePhoneNumberForCustomerId_theReturnSuccessStatus() {
    HttpHeaders httpHeaders = getHttpHeaders();
    when(customerRepo.findById(any())).thenReturn(createCustomer());
    assertThat(customerService.activatePhoneNumber(httpHeaders, uuid.toString(), createCustomerDto("1111", "2222")), is(true));
  }

  @Test
  void activatePhoneNumberForCustomerId_ifPhoneNumberNotFound_thenThrowsDataNotFound() {
    HttpHeaders httpHeaders = getHttpHeaders();
    when(customerRepo.findById(any())).thenReturn(createCustomer());
    try {
      customerService.activatePhoneNumber(httpHeaders, uuid.toString(), createCustomerDto("11113", null));
    } catch (DataNotFoundException ex) {
      // TODO: this can be improve
    }
  }

  @Test
  void activatePhoneNumberForCustomerId_DbThrowsException_thenThrowsDataNotFoundException() {
    HttpHeaders httpHeaders = getHttpHeaders();
    when(customerRepo.findById(any())).thenThrow(new DataNotFoundException("Matching customer not found for correlation Id: " + httpHeaders.getFirst(HttpHeader.X_CORRELATION_ID)));
    try {
      customerService.activatePhoneNumber(httpHeaders, uuid.toString(), createCustomerDto("1111", "2222"));
    } catch (DataNotFoundException ex) {
      // TODO: this can be improve
    }
  }

  private HttpHeaders getHttpHeaders() {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add("X_CORRELATION_ID", "1111111");
    return httpHeaders;
  }

  private Optional<Customer> createCustomer() {
    List<Phone> phones = Arrays.asList(Phone.builder()
                    .phoneNo("1111")
                    .status("active").build(),
            Phone.builder()
                    .phoneNo("2222")
                    .status("inActive").build());
    return Optional.of(Customer.builder().id(uuid).phoneNos(phones).build());
  }

  private CustomerDto createCustomerDto(String phoneNumber1, String phoneNumber2) {
    List<Phone> phones = new ArrayList<>();

    phones.add(Phone.builder()
                    .phoneNo(phoneNumber1)
                    .status("active").build());
    if (Strings.isNotBlank(phoneNumber2)) {
        phones.add(Phone.builder()
              .phoneNo(phoneNumber2)
              .status("inActive").build());
    }
    return CustomerDto.builder().id(uuid).phoneNos(phones).build();
  }
}