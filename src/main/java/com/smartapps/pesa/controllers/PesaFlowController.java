package com.smartapps.pesa.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartapps.pesa.Repository.Repo;
import com.smartapps.pesa.models.PaymentStatus;
import com.smartapps.pesa.models.PesaFlowViewModel;
import com.smartapps.pesa.models.PesaflowPaymentNotification;
import com.smartapps.pesa.utils.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/api/pesaflow")
public class PesaFlowController {

    @Autowired
    Repo repository;

    @Value("${pesaflow.secret}")
    private String secret;

    @Value("${pesaflow.query-key}")
    private String key;
    private final String pesaflowSandboxUrl = "https://test.pesaflow.com/PaymentAPI/iframev2.1.php";
    private final String paymentStatusUrl = "https://test.pesaflow.com/api/invoice/payment/status";
    RestTemplate restTemplate = new RestTemplate();
    @PostMapping("/checkout")
    public ResponseEntity<?> processJsonCheckoutjson(@RequestBody PesaFlowViewModel model) {
        model.setFormat("json");
        String dataString =
                model.getApiClientID()
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

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String requestBodyJson = objectMapper.writeValueAsString(model);
            System.out.println("Request Body: " + requestBodyJson);
        } catch (JsonProcessingException e) {
            System.err.println("Error printing request body: " + e.getMessage());
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PesaFlowViewModel> request = new HttpEntity<>(model, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(pesaflowSandboxUrl, request, String.class);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @PostMapping("/notification-url")
    public ResponseEntity<?> handlePesaflowNotification(@RequestBody PesaflowPaymentNotification notification) {
        try {
           repository.save(notification);
            return ResponseEntity.ok("Notification received.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid notification payload.");
        }
    }

    @PostMapping("/callback-url")
    public ResponseEntity<?> callBackUrl(@RequestBody PesaflowPaymentNotification notification) {
        try {
            repository.save(notification);
            return ResponseEntity.ok("Notification received.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid notification payload.");
        }
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
