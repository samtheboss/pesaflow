package com.smartapps.pesa.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PesaFlowViewModel {
    private String apiClientID;
    private String serviceID;
    private String billDesc;
    private String currency;
    private String billRefNumber;
    private String clientMSISDN;
    private String clientName;
    private String clientIDNumber;
    private String clientEmail;
    private String callBackURLOnSuccess;
    private String amountExpected;
    private String notificationURL;
    private String secureHash;
    private String format;
    private String sendSTK;
    private String pictureURL;

}
