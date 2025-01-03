package com.ilyass.wsmulticonnectorspringsecurityjwt.service;

import com.ilyass.wsmulticonnectorspringsecurityjwt.dtos.customer.*;

import java.util.List;

public interface ICustomerService {
    List<CustomerDto> getAllCustomers();

    AddCustomerResponse createCustomer(AddCustomerRequest addCustomerRequest);

    UpdateCustomerResponse updateCustomer(String identityRef, UpdateCustomerRequest updateCustomerRequest);

    CustomerDto getCustomByIdentity(String identity);

    String deleteCustomerByIdentityRef(String identityRef);
}
