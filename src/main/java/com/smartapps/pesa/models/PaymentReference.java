package com.smartapps.pesa.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentReference {
    @JsonProperty("payment_reference")
    private String paymentReference;

    @JsonProperty("payment_date")
    private String paymentDate;

    @JsonProperty("inserted_at")
    private String insertedAt;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("amount")
    private String amount;
}
