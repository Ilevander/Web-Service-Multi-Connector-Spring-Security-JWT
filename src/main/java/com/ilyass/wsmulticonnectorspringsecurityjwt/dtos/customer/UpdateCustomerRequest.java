package com.ilyass.wsmulticonnectorspringsecurityjwt.dtos.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UpdateCustomerRequest {
    private String username;
    private String firstname;
    private String lastname;
}
