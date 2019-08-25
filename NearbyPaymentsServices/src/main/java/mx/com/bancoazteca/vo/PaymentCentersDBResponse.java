package mx.com.bancoazteca.vo;

import java.io.Serializable;
import java.util.List;

import mx.com.bancoazteca.entity.PaymentCenter;

public class PaymentCentersDBResponse implements Serializable {
	private List<PaymentCenter> paymentCenters;
	
	public PaymentCentersDBResponse() {
	}

	public List<PaymentCenter> getPaymentCenters() {
		return paymentCenters;
	}

	public void setPaymentCenters(List<PaymentCenter> paymentCenters) {
		this.paymentCenters = paymentCenters;
	}
}
