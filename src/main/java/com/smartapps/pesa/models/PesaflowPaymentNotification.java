package com.smartapps.pesa.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "paymentDetails")
public class PesaflowPaymentNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty("payment_channel")
    private String paymentChannel;
    @JsonProperty("client_invoice_ref")
    private String clientInvoiceRef;
    @JsonProperty("payment_reference")
    private String paymentReference;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("amount_paid")
    private String amountPaid;
    @JsonProperty("convenience_fee")
    private String convenienceFee;
    @JsonProperty("invoice_amount")
    private String invoiceAmount;
    @JsonProperty("status")
    private String status;
    @JsonProperty("invoice_number")
    private String invoiceNumber;
    @JsonProperty("payment_date")
    private String paymentDate;
    @JsonProperty("token_hash")
    private String tokenHash;
    @JsonProperty("last_payment_amount")
    private String lastPaymentAmount;



}
