package com.smartapps.pesa.Repository;

import com.smartapps.pesa.models.PesaflowTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepo extends JpaRepository<PesaflowTransaction, Long> {
}
