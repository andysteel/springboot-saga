package com.gmail.andersoninfonet.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardDetails {
    
    private String name; 
    private String cardNumber; 
    private int validMonth; 
    private int validYear; 
    private int cvv;
}
