package com.example.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author by Dulanja Wijethunga.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Integer orderId;
    private String description;
}
