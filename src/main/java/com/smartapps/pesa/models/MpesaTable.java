package com.smartapps.pesa.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity(name = "bo_tblsmsinput")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MpesaTable {
    @Id
    String SMSCODE;
    String MSGCODE;
    String SMSTEXT;
    String SENDERNUMBER;
    String SENDDATE;
    String SENDTIME;
    double SMSAMOUNT;
    String SMSFIRSTNAME;
    String SMSLASTNAME;
    String MSISDN;
    String TRANSACTIONTYPE;

}
