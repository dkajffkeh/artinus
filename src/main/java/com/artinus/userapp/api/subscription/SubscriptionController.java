package com.artinus.userapp.api.subscription;

import com.artinus.userapp.payload.ApiResult;
import com.artinus.userapp.payload.request.SubscribeRequest;
import com.artinus.userapp.service.subscription.SubscriptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subscription")
public class SubscriptionController {

    private final SubscriptionHandler subscribeHandler;

    public SubscriptionController(
            SubscriptionHandler subscribeHandler) {
        this.subscribeHandler = subscribeHandler;
    }

    @PostMapping
    public ApiResult<?> subscribe(SubscribeRequest request) {
        return null;
    }
}
