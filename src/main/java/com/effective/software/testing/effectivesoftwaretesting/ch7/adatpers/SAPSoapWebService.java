package com.effective.software.testing.effectivesoftwaretesting.ch7.adatpers;

import com.effective.software.testing.effectivesoftwaretesting.ch7.domain.ShoppingCart;
import com.effective.software.testing.effectivesoftwaretesting.ch7.ports.SAP;

public class SAPSoapWebService implements SAP {

    @Override
    public void cartReadyForDelivery(ShoppingCart cart) {
        // SAP의 SOAP 웹 서비스에 카트를 전송하는 코드
    }
}
