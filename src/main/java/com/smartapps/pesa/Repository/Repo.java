package com.smartapps.pesa.Repository;

import com.smartapps.pesa.models.PesaflowPaymentNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Repo  extends JpaRepository<PesaflowPaymentNotification, Long> {
}
