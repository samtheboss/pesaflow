package com.smartapps.pesa.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentStatus {
    private String api_client_id;
    private String ref_no;


}
