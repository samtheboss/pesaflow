package com.smartapps.pesa.models;

import lombok.Data;


public class PaymentStatus {
    private String api_client_id;
    private String ref_no;

    public String getApi_client_id() {
        return api_client_id;
    }

    public void setApi_client_id(String api_client_id) {
        this.api_client_id = api_client_id;
    }

    public String getRef_no() {
        return ref_no;
    }

    public void setRef_no(String ref_no) {
        this.ref_no = ref_no;
    }
}
