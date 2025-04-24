package com.smartapps.pesa.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class PesaflowPaymentNotification {
    @JsonProperty("status")
    private String status;

    @JsonProperty("secure_hash")
    private String secureHash;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("payment_reference")
    private List<PaymentReference> paymentReference;

    @JsonProperty("payment_date")
    private String paymentDate;

    @JsonProperty("payment_channel")
    private String paymentChannel;

    @JsonProperty("last_payment_amount")
    private String lastPaymentAmount;

    @JsonProperty("invoice_number")
    private String invoiceNumber;

    @JsonProperty("invoice_amount")
    private String invoiceAmount;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("client_invoice_ref")
    private String clientInvoiceRef;

    @JsonProperty("amount_paid")
    private String amountPaid;


}
