package com.artinus.userapp.api.subscription;

import com.artinus.userapp.payload.ApiResult;
import com.artinus.userapp.payload.response.SubscriptionHistResponse;
import com.artinus.userapp.service.subscription.SubscriptionHistHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionHistController {

    private final SubscriptionHistHandler subscriptionHistHandler;

    public SubscriptionHistController(
            SubscriptionHistHandler subscriptionHistHandler) {
        this.subscriptionHistHandler = subscriptionHistHandler;
    }

    @GetMapping
    public ApiResult<SubscriptionHistResponse> getSubscriptionHists(
            @RequestParam(value = "phoneNumber", required = true) String phoneNumber) {
        return ApiResult.succeed(subscriptionHistHandler.getSubscriptionHists(phoneNumber));
    }

}
