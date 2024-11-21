package CodedBTA.AccountService.Controller;

import CodedBTA.AccountService.bo.BalanceResponse;
import CodedBTA.AccountService.bo.TransferRequest;
import CodedBTA.AccountService.bo.TransferResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class AccountController {

    private final RestTemplate restTemplate;

    private static final String STOCK_API = "http://localhost:8080/api";

    public AccountController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @GetMapping("/account/balance")
    @CircuitBreaker(name = "accountMS", fallbackMethod = "fallbackMethod")
    public ResponseEntity<BalanceResponse> getAll() {
        ResponseEntity<BalanceResponse> response = restTemplate.getForEntity(STOCK_API, BalanceResponse.class);
        return ResponseEntity.ok().body(response.getBody());
    }

    @PostMapping("/transfer")
    @CircuitBreaker(name = "accountMS", fallbackMethod = "fallbackMethod")
    public TransferResponse transfer(
            @RequestBody TransferRequest transferRequest,
            @RequestHeader("Authorization") String authorizationHeader) {

        // Log the received token for debugging (optional, but avoid printing sensitive information in production logs)
        System.out.println("Received Token: " + authorizationHeader);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", authorizationHeader); // Forward the received token

        HttpEntity<TransferRequest> request = new HttpEntity<>(transferRequest, headers);

        try {
            ResponseEntity<TransferResponse> responseEntity = restTemplate.postForEntity(
                    STOCK_API,
                    request,
                    TransferResponse.class
            );

            TransferResponse response = responseEntity.getBody();
            System.out.println("Response from 8080: " + response);

            return response;
        } catch (Exception e) {
            throw new RuntimeException("Error forwarding transfer request: " + e.getMessage(), e);
        }
    }

    public ResponseEntity<BalanceResponse> fallbackMethod(Throwable throwable) {
        BalanceResponse accountResponse = new BalanceResponse();
        accountResponse.setStatus("Called Fallback Method");
        System.out.println("Fallback triggered: " + throwable.getMessage());
        return ResponseEntity.ok(accountResponse);
    }
    
}
