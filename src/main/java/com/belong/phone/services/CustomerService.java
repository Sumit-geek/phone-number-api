package com.belong.phone.services;

import com.belong.phone.common.HttpHeader;
import com.belong.phone.exceptions.DataNotFoundException;
import com.belong.phone.models.Customer;
import com.belong.phone.models.CustomerDto;
import com.belong.phone.models.Phone;
import com.belong.phone.repository.CustomerRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {
    private final CustomerRepo customerRepo;

    public CustomerDto getPhoneNumberForCustomer(HttpHeaders httpHeaders, String customerId) {
        String correlationId = httpHeaders.getFirst(HttpHeader.X_CORRELATION_ID);
        log.debug("Request received for customerId: {} with correlationId: {}, ", customerId, correlationId);
        return customerRepo.findById(UUID.fromString(customerId)).map(CustomerDto::mapCustomer).orElseGet(() -> {
            log.warn("No data found for customerId: {} with correlationId: {}", customerId, correlationId);
            return null;
        });
    }

    @Transactional
    public boolean activatePhoneNumber(HttpHeaders httpHeaders, String customerId, CustomerDto customerIn) {
        String correlationId = httpHeaders.getFirst(HttpHeader.X_CORRELATION_ID);
        Customer customer = customerRepo.findById(UUID.fromString(customerId)).orElseThrow(() ->
                new DataNotFoundException("Matching customer not found for correlation Id: " + correlationId));

        boolean updated = false;
        Phone requestedPhone = customerIn.getPhoneNos().get(0);
        List<Phone> phones = customer.getPhoneNos();
        for (Phone phone : phones) {
            if (phone.getPhoneNo().equals(requestedPhone.getPhoneNo())) {
                phone.setStatus(requestedPhone.getStatus());
                updated = true;
                break;
            }
        };

        if (!updated) {
            throw new DataNotFoundException("Matching phone number not found for given customer " + customerId);
        }
        customerRepo.save(customer);
        return updated;
    }
}
