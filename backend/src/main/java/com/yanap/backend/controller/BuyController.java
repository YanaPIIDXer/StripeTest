package com.yanap.backend.controller;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Refund;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Getter;

@RestController
public class BuyController {
    @AllArgsConstructor
    public class Response {
        @Getter
        private boolean result;
    }
    
    @PostMapping("/buy")
    public Response buy(@RequestParam("token") String token) {
        String key = System.getenv("STRIPE_SECRET_KEY");
        Stripe.apiKey = key;
        
        Map<String, Object> chargeMap = new HashMap<String, Object>();
        chargeMap.put("amount", 1000000);
        chargeMap.put("description", "慰謝料");
        chargeMap.put("currency", "jpy");
        chargeMap.put("source", token);
        
        try {
            Charge charge = Charge.create(chargeMap);
            System.out.println(charge);
            
            System.out.println("======================");
            
            Map<String, Object> refundMap = new HashMap<String, Object>();
            refundMap.put("charge", charge.getId());    // IDを投げると返金できるので、システム上から返金したい場合は控えておく必要がある
            Refund refund = Refund.create(refundMap);
            System.out.println(refund);
            
        } catch (StripeException e) {
            e.printStackTrace();
            return new Response(false);
        }
        
        return new Response(true);
    }
}
