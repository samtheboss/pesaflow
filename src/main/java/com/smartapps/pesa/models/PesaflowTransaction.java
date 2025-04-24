package com.smartapps.pesa.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.cfg.Unsafe;

import java.math.BigDecimal;

@Entity(name = "notifications")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PesaflowTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;
    private String secureHash;
    private String phoneNumber;
    private String clientInvoiceRef;
    private String invoiceNumber;
    private double invoiceAmount;
    private double amountPaid;
    private double lastPaymentAmount;
    private String currency;
    private String paymentDate;
    private String paymentChannel;
    @Column(unique = true, nullable = false)
    private String nestedPaymentReference;
    private String nestedPaymentDate;
    private String nestedInsertedAt;
    private double nestedAmount;

}
