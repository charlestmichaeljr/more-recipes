package com.charlie.morerecipes.commands;

import org.springframework.core.convert.converter.Converter;

import java.math.BigDecimal;

public class IngredientCommand {

    private Integer id;
    private String description;
    private BigDecimal amount;
    private UnitOfMeasureCommand unitOfMeasure;


    public IngredientCommand() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public UnitOfMeasureCommand getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(UnitOfMeasureCommand unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }
}
