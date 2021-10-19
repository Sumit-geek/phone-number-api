package com.belong.phone.component.repository;

import com.belong.phone.component.ComponentTest;
import com.belong.phone.models.Phone;
import com.belong.phone.repository.PhoneRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@ComponentTest
@Sql(scripts = {"/db/test_data/data_setup.sql"}, executionPhase = BEFORE_TEST_METHOD)
@Sql(scripts = {"/db/test_data/data_cleanup.sql"}, executionPhase = AFTER_TEST_METHOD)
class PhoneRepositoryTest {

  @Autowired
  private PhoneRepo phoneRepo;

  @Test
  void findByCustomerId_givenInvalidCustomerId_ReturnPhoneNumberList() {
    Optional<List<Phone>> optionalPhoneNumbers = Optional.of(phoneRepo.findAll());
    assertThat(optionalPhoneNumbers.get().size(), is(4));
  }
}
