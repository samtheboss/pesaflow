package com.smartapps.pesa.models;

import lombok.*;


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

    public PesaFlowViewModel() {
    }

    public PesaFlowViewModel(String apiClientID, String serviceID, String billDesc, String currency, String billRefNumber, String clientMSISDN, String clientName, String clientIDNumber, String clientEmail, String callBackURLOnSuccess, String amountExpected, String notificationURL, String secureHash, String format, String sendSTK, String pictureURL) {
        this.apiClientID = apiClientID;
        this.serviceID = serviceID;
        this.billDesc = billDesc;
        this.currency = currency;
        this.billRefNumber = billRefNumber;
        this.clientMSISDN = clientMSISDN;
        this.clientName = clientName;
        this.clientIDNumber = clientIDNumber;
        this.clientEmail = clientEmail;
        this.callBackURLOnSuccess = callBackURLOnSuccess;
        this.amountExpected = amountExpected;
        this.notificationURL = notificationURL;
        this.secureHash = secureHash;
        this.format = format;
        this.sendSTK = sendSTK;
        this.pictureURL = pictureURL;
    }

    public String getApiClientID() {
        return apiClientID == null ? "" : apiClientID;
    }

    public void setApiClientID(String apiClientID) {
        this.apiClientID = apiClientID;
    }

    public String getServiceID() {
        return serviceID == null ? "" : serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    public String getBillDesc() {
        return billDesc == null ? "" : billDesc;
    }

    public void setBillDesc(String billDesc) {
        this.billDesc = billDesc;
    }

    public String getCurrency() {
        return currency == null ? "" : currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getBillRefNumber() {
        return billRefNumber == null ? "" : billRefNumber;
    }

    public void setBillRefNumber(String billRefNumber) {
        this.billRefNumber = billRefNumber;
    }

    public String getClientMSISDN() {
        return clientMSISDN == null ? "" : clientMSISDN;
    }

    public void setClientMSISDN(String clientMSISDN) {
        this.clientMSISDN = clientMSISDN;
    }

    public String getClientName() {
        return clientName == null ? "" : clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientIDNumber() {
        return clientIDNumber == null ? "" : clientIDNumber;
    }

    public void setClientIDNumber(String clientIDNumber) {
        this.clientIDNumber = clientIDNumber;
    }

    public String getClientEmail() {
        return clientEmail == null ? "" : clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getCallBackURLOnSuccess() {
        return callBackURLOnSuccess == null ? "" : callBackURLOnSuccess;
    }

    public void setCallBackURLOnSuccess(String callBackURLOnSuccess) {
        this.callBackURLOnSuccess = callBackURLOnSuccess;
    }

    public String getAmountExpected() {
        return amountExpected == null ? "" : amountExpected;
    }

    public void setAmountExpected(String amountExpected) {
        this.amountExpected = amountExpected;
    }

    public String getNotificationURL() {
        return notificationURL == null ? "" : notificationURL;
    }

    public void setNotificationURL(String notificationURL) {
        this.notificationURL = notificationURL;
    }

    public String getSecureHash() {
        return secureHash == null ? "" : secureHash;
    }

    public void setSecureHash(String secureHash) {
        this.secureHash = secureHash;
    }

    public String getFormat() {
        return format == null ? "" : format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getSendSTK() {
        return sendSTK == null ? "" : sendSTK;
    }

    public void setSendSTK(String sendSTK) {
        this.sendSTK = sendSTK;
    }

    public String getPictureURL() {
        return pictureURL == null ? "" : pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    @Override
    public String toString() {
        return "PesaFlowViewModel{" +
                "apiClientID='" + apiClientID + '\'' +
                ", serviceID='" + serviceID + '\'' +
                ", billDesc='" + billDesc + '\'' +
                ", currency='" + currency + '\'' +
                ", billRefNumber='" + billRefNumber + '\'' +
                ", clientMSISDN='" + clientMSISDN + '\'' +
                ", clientName='" + clientName + '\'' +
                ", clientIDNumber='" + clientIDNumber + '\'' +
                ", clientEmail='" + clientEmail + '\'' +
                ", callBackURLOnSuccess='" + callBackURLOnSuccess + '\'' +
                ", amountExpected='" + amountExpected + '\'' +
                ", notificationURL='" + notificationURL + '\'' +
                ", secureHash='" + secureHash + '\'' +
                ", format='" + format + '\'' +
                ", sendSTK='" + sendSTK + '\'' +
                ", pictureURL='" + pictureURL + '\'' +
                '}';
    }
}
