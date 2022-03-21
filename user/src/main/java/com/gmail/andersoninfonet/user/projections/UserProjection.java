package com.gmail.andersoninfonet.user.projections;

import com.gmail.andersoninfonet.common.model.CardDetails;
import com.gmail.andersoninfonet.common.model.User;
import com.gmail.andersoninfonet.common.queries.GetUserPaymentDetailsQuery;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
public class UserProjection {
    
    @QueryHandler
    public User getUserPaymentDetails(GetUserPaymentDetailsQuery query) {
        //Ideally get the details from DB
        var cardDetails = new CardDetails("Anderson", "123456789",2023, 03, 456);

        return new User(query.getUserId(), "Anderson", "Dias", cardDetails);
    }
}
