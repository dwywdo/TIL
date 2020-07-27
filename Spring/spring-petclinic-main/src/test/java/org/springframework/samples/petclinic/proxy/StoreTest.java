package org.springframework.samples.petclinic.proxy;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StoreTest {

	@Test
	public void testPay() {
		final Payment cashPerf = new CashPerf();
		Store store = new Store(cashPerf);
		store.buySomething(100);

	}

}
