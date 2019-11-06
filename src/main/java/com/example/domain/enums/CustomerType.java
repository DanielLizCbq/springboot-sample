/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.domain.enums;

/**
 *
 * @author daniel
 */
public enum CustomerType {
    INDIVIDUALENTITY(0, "Individual Entity"),
    LEGALENTITY(10, "Legal Entity");

    private int code;
    private String description;

    private CustomerType(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static CustomerType toEnum(Integer code) {

        if (code == null) {
            return null;
        }

        for (CustomerType x : CustomerType.values()) {
            if (code.equals(x.getCode())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Invalid ID: " + code);
    }

}
