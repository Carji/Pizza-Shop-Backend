package com.example.demo.domain.pizzaDomain;

import java.math.BigDecimal;
import java.util.UUID;

import com.example.demo.domain.imageDomain.Image;

public interface PizzaProjection {
    public UUID getId();

    public String getName();

    public BigDecimal getPrice();

    public Image getImage();
}
