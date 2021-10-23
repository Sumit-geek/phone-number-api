package com.belong.phone.component.repository;

import com.belong.phone.component.ComponentTest;
import com.belong.phone.models.Customer;
import com.belong.phone.repository.CustomerRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@ComponentTest
@Sql(scripts = {"/db/test_data/data_setup.sql"}, executionPhase = BEFORE_TEST_METHOD)
@Sql(scripts = {"/db/test_data/data_cleanup.sql"}, executionPhase = AFTER_TEST_METHOD)
class CustomerRepositoryTest {

  @Autowired
  private CustomerRepo customerRepo;

  private final UUID uuid = UUID.fromString("0522415f-0f7a-42b1-a072-3dd6f45beccb");

  @Test
  void findByCustomerId_givenCustomerId_ReturnPhoneNumberList() {
    Optional<Customer> customer = customerRepo.findById(uuid);
    assertThat(customer.get().getId(), is(uuid));
    assertThat(customer.get().getPhoneNos().size(), is(2));
  }
}
