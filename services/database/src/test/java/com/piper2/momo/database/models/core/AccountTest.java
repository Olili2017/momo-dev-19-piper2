package com.piper2.momo.database.models.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void createAccountNumber() {

    }

    @Test
    void getAccountNumberLength() {
        Account ac = new Account();
        assertEquals(1,ac.getAccountNumberLength(8));
        assertEquals(2,ac.getAccountNumberLength(85));
        assertEquals(3,ac.getAccountNumberLength(856));
        assertEquals(4,ac.getAccountNumberLength(8674));
        assertEquals(5,ac.getAccountNumberLength(86744));
        assertEquals(6,ac.getAccountNumberLength(867445));
        assertEquals(7,ac.getAccountNumberLength(8674445));
        assertEquals(8,ac.getAccountNumberLength(86744455));
        assertEquals(9,ac.getAccountNumberLength(867444559L));
        assertEquals(10,ac.getAccountNumberLength(8674445595L));
    }
}