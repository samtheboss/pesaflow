package com.smartapps.pesa.controllers;

import com.smartapps.pesa.Repository.MpesaRepo;
import com.smartapps.pesa.Repository.PesaFlowInvoiceRepo;
import com.smartapps.pesa.Repository.NotificationRepo;
import com.smartapps.pesa.models.*;
import com.smartapps.pesa.services.PaymentServices;
import com.smartapps.pesa.utils.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/pesaflow")
public class PesaFlowController {

    @Autowired
    NotificationRepo notificationRepo;
    @Autowired
    MpesaRepo mpesaRepo;
    @Autowired
    PesaFlowInvoiceRepo invoiceRepository;
    @Autowired
    PaymentServices services;
    @Value("${pesaflow.secret}")
    private String secret;
    @Value("${pesaflow.query-key}")
    private String key;
    @Value("${pesaflow.pesaflowSUrl}")
    private String pesaflowSandboxUrl;
    @Value("${pesaflow.paymentStatusUrl}")
    private String paymentStatusUrl;
    RestTemplate restTemplate = new RestTemplate();

    @PostMapping("/checkout")
    public ResponseEntity<?> processJsonCheckout(@RequestBody PesaFlowViewModel model) {
        try {
            model.setFormat("json");
            String dataString = model.getApiClientID()
                    + model.getAmountExpected()
                    + model.getServiceID()
                    + model.getClientIDNumber()
                    + model.getCurrency()
                    + model.getBillRefNumber()
                    + model.getBillDesc()
                    + model.getClientName()
                    + secret;
            String secureHash = HashUtil.generateBase64EncodedHmac(dataString, key);
            model.setSecureHash(secureHash);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<PesaFlowViewModel> request = new HttpEntity<>(model, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(pesaflowSandboxUrl, request, String.class);
            Invoices invoices = services.saveInvoice(response.getBody());
            invoiceRepository.save(invoices);
            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
        } catch (HttpClientErrorException.UnprocessableEntity e) {
            System.err.println("❌ Invoice already paid: " + e.getResponseBodyAsString());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(e.getResponseBodyAsString());
        } catch (HttpClientErrorException e) {
            System.err.println("❌ Pesaflow client error: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        } catch (Exception e) {
            System.err.println("❌ Internal server error: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal error occurred: " + e.getMessage());
        }
    }

    @PostMapping("/notification-url")
    public ResponseEntity<?> handlePesaflowNotification(@RequestBody PesaflowPaymentNotification notification) {
        PesaflowTransaction reference = services.mapToEntity(notification);
        MpesaTable mpesaTable = services.saveMobileMoney(notification);
        mpesaRepo.save(mpesaTable);
        notificationRepo.save(reference);
        return ResponseEntity.ok("Notification received.");
    }

    @PostMapping("/callback-url")
    public ResponseEntity<?> callBackUrl(@RequestBody PesaflowPaymentNotification notification) {
        PesaflowTransaction reference = services.mapToEntity(notification);
        MpesaTable mpesaTable = services.saveMobileMoney(notification);
        mpesaRepo.save(mpesaTable);
        notificationRepo.save(reference);
        return ResponseEntity.ok("Notification received.");
    }

    @PostMapping("/payment-status")
    public ResponseEntity<?> checkPaymentStatus(@RequestBody PaymentStatus request) {
        String dataString = request.getApi_client_id() + request.getRef_no();
        String secureHash = HashUtil.generateBase64EncodedHmac(dataString, key);
        String url = paymentStatusUrl +
                "?api_client_id=" + request.getApi_client_id() +
                "&ref_no=" + request.getRef_no() +
                "&secure_hash=" + secureHash;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }
}
