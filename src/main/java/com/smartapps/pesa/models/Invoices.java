package com.smartapps.pesa.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "payflowInvoives")
@Data
public class Invoices {
    @Id
    private String invoice_number;
    private String invoice_link;
    private String commission;
    private String amount_net;
    private String amount_expected;
}
