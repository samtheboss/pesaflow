package com.smartapps.pesa.Repository;

import com.smartapps.pesa.models.Invoices;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PesaFlowInvoiceRepo extends JpaRepository<Invoices, String> {

}
