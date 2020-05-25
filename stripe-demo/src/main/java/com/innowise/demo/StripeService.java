package com.innowise.demo;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class StripeService {

    @Value("${stripe.keys.secret}")
    private String apiSecretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = apiSecretKey;
    }

    public String createCharge(String email, String token, int amount) throws StripeException {

        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", amount);
        chargeParams.put("currency", "usd");
        chargeParams.put("description", "Charge for " + email);
        chargeParams.put("source", token); // ^ obtained with Stripe.js

        //create a charge
        Charge charge = Charge.create(chargeParams);

        return charge.getId();
    }

}
