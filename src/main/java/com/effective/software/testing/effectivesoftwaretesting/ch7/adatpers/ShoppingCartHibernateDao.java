package com.effective.software.testing.effectivesoftwaretesting.ch7.adatpers;

import com.effective.software.testing.effectivesoftwaretesting.ch7.domain.ShoppingCart;
import com.effective.software.testing.effectivesoftwaretesting.ch7.ports.ShoppingCartRepository;

import java.util.List;

public class ShoppingCartHibernateDao implements ShoppingCartRepository {
    @Override
    public List<ShoppingCart> cartsPaidToday() {
        //TODO(오늘 결제가 완료된 송장 목록을 얻는 하이버네이트 쿼리)
        return null;
    }

    @Override
    public void persist(ShoppingCart cart) {
        //TODO(데이터베이스에 카트를 영속화하는 하이버네이트 코드)
    }
}
