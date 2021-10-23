package com.belong.phone.unit.services;

import com.belong.phone.models.Phone;
import com.belong.phone.models.PhoneDto;
import com.belong.phone.repository.PhoneRepo;
import com.belong.phone.services.PhoneService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PhoneServiceTest {

  @InjectMocks
  private PhoneService phoneService;

  @Mock
  private PhoneRepo phoneRepo;

  @Test
  void getPhoneNumber_thenReturnAllPhoneNumbers() {
    List<Phone> phoneNumbers = createPhoneNumbers();
    when(phoneRepo.findAll()).thenReturn(phoneNumbers);
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add("X_CORRELATION_ID", "1111111");
    List<PhoneDto> result = phoneService.getPhoneNumbers(httpHeaders);
    assertThat(result.size(), is(3));
    assertThat(result.get(0).getPhoneNo(), is("1111"));
    assertThat(result.get(1).getPhoneNo(), is("2222"));
    assertThat(result.get(2).getPhoneNo(), is("3333"));
  }

  private List<Phone> createPhoneNumbers() {
    return Arrays.asList(Phone.builder()
                    .phoneNo("1111")
                    .status("active").build(),
            Phone.builder()
            .phoneNo("2222")
            .status("inActive").build(),
            Phone.builder()
                    .phoneNo("3333")
                    .status("active").build());
  }
}