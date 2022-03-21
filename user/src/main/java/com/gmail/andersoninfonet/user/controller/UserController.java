package com.gmail.andersoninfonet.user.controller;

import java.util.concurrent.CompletableFuture;

import com.gmail.andersoninfonet.common.model.User;
import com.gmail.andersoninfonet.common.queries.GetUserPaymentDetailsQuery;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final QueryGateway queryGateway;

    public UserController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping("/{userId}")
    public CompletableFuture<User> getUserPaymentDetails(@PathVariable String userId){
        
        return queryGateway.query(new GetUserPaymentDetailsQuery(userId),ResponseTypes.instanceOf(User.class));

    }
}
