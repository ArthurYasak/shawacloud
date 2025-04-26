package com.shawa;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Document
public class ShawaOrder {

//    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private List<Shawa> shawas = new ArrayList<>();

    @NotBlank(message="Delivery name is required")
    private String deliveryName;

    @NotBlank(message="Street is required")
    private String deliveryStreet;

    @NotBlank(message="City is required")
    private String deliveryCity;

    @NotBlank(message="State is required")
    private String deliveryState;

    @NotBlank(message="Zip code is required")
    private String deliveryZip;

//    @CreditCardNumber(message="Not a valid credit card number") TODO: return validation
    private String ccNumber;

//    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$",
//            message="Must be formatted MM/YY")
    private String ccExpiration;

//    @Digits(integer=3, fraction=0, message="Invalid CVV")
    @Field(name = "cc_cvv")
    private String ccCVV;

    private Date placedAt = new Date();

}