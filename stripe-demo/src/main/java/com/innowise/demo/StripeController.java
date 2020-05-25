package com.innowise.demo;

import com.stripe.exception.StripeException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StripeController {

    private final StripeService stripeService;

    @PostMapping("/create-charge")
    public ResponseEntity<String> createCharge(@RequestParam String email, @RequestParam String token) throws StripeException {
        String chargeId = stripeService.createCharge(email, token, 999);
        return ResponseEntity.ok(chargeId);
    }
}
