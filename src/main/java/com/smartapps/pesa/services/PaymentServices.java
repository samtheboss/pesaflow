package com.smartapps.pesa.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartapps.pesa.models.*;
import com.smartapps.pesa.utils.Utils;
import org.springframework.stereotype.Service;

import java.time.*;

@Service
public class PaymentServices {
    public PesaflowTransaction mapToEntity(PesaflowPaymentNotification notification) {
        PesaflowTransaction transaction = new PesaflowTransaction();
        transaction.setStatus(notification.getStatus());
        transaction.setSecureHash(notification.getSecureHash());
        transaction.setPhoneNumber(notification.getPhoneNumber());
        transaction.setClientInvoiceRef(notification.getClientInvoiceRef());
        transaction.setInvoiceNumber(notification.getInvoiceNumber());
        transaction.setInvoiceAmount(Double.parseDouble(notification.getInvoiceAmount()));
        transaction.setAmountPaid(Double.parseDouble(notification.getAmountPaid()));
        transaction.setLastPaymentAmount(Double.parseDouble(notification.getLastPaymentAmount()));
        transaction.setCurrency(notification.getCurrency());
        transaction.setPaymentDate(notification.getPaymentDate());
        transaction.setPaymentChannel(notification.getPaymentChannel());
        if (notification.getPaymentReference() != null && !notification.getPaymentReference().isEmpty()) {
            PaymentReference ref = notification.getPaymentReference().get(0);
            transaction.setNestedPaymentReference(ref.getPaymentReference());
            transaction.setNestedPaymentDate(ref.getPaymentDate());
            transaction.setNestedInsertedAt(ref.getInsertedAt());
            transaction.setNestedAmount(Double.parseDouble(ref.getAmount()));
        }
        return transaction;
    }



    public MpesaTable saveMobileMoney(PesaflowPaymentNotification notification) {
        MpesaTable mobTrans = new MpesaTable();
        mobTrans.setMSISDN(notification.getPhoneNumber());
        mobTrans.setSMSAMOUNT(Double.parseDouble(notification.getAmountPaid()));
        mobTrans.setTRANSACTIONTYPE(notification.getPaymentChannel());
        mobTrans.setSENDERNUMBER(notification.getPhoneNumber());
        mobTrans.setSMSTEXT(Utils.getFirstPart(notification.getClientInvoiceRef()));
        if (notification.getPaymentReference() != null && !notification.getPaymentReference().isEmpty()) {
            PaymentReference ref = notification.getPaymentReference().get(0);
            mobTrans.setSMSAMOUNT(Double.parseDouble(ref.getAmount()));
            ZonedDateTime dateTime = Utils.parseFlexibleDate(notification.getPaymentDate());
            System.out.println("Saved at Date : " + dateTime.toLocalDate() +" Time " + dateTime.toLocalTime());
           // System.out.println("Time: " + dateTime.toLocalTime());
            mobTrans.setSMSCODE(ref.getPaymentReference());
            String date = dateTime.toLocalDate().toString();
            String time = dateTime.toLocalTime().toString();
            mobTrans.setSENDDATE(date);
            mobTrans.setSENDTIME(time);
        }
        return mobTrans;
    }

    public Invoices saveInvoice(String jsonString) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Invoices checkoutResponse = mapper.readValue(jsonString, Invoices.class);
        String invoiceNumber = checkoutResponse.getInvoice_number();
        String invoiceLink = checkoutResponse.getInvoice_link();
        String commission = checkoutResponse.getCommission();
        String amountNet = checkoutResponse.getAmount_net();
        String amountExpected = checkoutResponse.getAmount_expected();
        Invoices invoice = new Invoices();
        invoice.setInvoice_number(invoiceNumber);
        invoice.setInvoice_link(invoiceLink);
        invoice.setCommission(commission);
        invoice.setAmount_net(amountNet);
        invoice.setAmount_expected(amountExpected);
        return invoice;

    }
}
