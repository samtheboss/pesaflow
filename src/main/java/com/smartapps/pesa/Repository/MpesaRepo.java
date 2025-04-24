package com.smartapps.pesa.Repository;

import com.smartapps.pesa.models.Invoices;
import com.smartapps.pesa.models.MpesaTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MpesaRepo  extends JpaRepository<MpesaTable, String> {
}
