package org.springframework.samples.petclinic.proxy;

import org.springframework.util.StopWatch;

public class CashPerf implements Payment {

	Payment cash = new Cash(); // 한도가 없으면 Cash로 Fallback

	@Override
	public void pay(int amount) {
		final StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		cash.pay(amount);

		stopWatch.stop();
		System.out.println(stopWatch.prettyPrint());
	}

}
