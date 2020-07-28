package org.springframework.samples.petclinic.proxy;

public class Cash implements Payment {

	@Override
	public void pay(int amount) {
		System.out.print("현금 결제: " + amount);
	}

}
