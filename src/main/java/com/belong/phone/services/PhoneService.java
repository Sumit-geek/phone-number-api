package com.belong.phone.services;

import com.belong.phone.models.Phone;
import com.belong.phone.models.PhoneDto;
import com.belong.phone.repository.PhoneRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PhoneService {

    private final PhoneRepo phoneRepo;
    public static final String X_CORRELATION_ID = "X_CORRELATION_ID";

    public List<PhoneDto> getPhoneNumbers(HttpHeaders requestHeaders) {
        String correlationId = requestHeaders.getFirst(X_CORRELATION_ID);
        log.debug("Fetching all phone numbers for request with correlationId: {}", correlationId);
        List<Phone> phoneNumbers = phoneRepo.findAll();
        return phoneNumbers.stream().parallel().map(PhoneDto::mapPhoneNumber).collect(Collectors.toList());
    }
}
