package mx.com.bancoazteca.service;

import java.util.List;

import mx.com.bancoazteca.vo.CanalPagoVo;

public interface PaymentCentersService {
	List<CanalPagoVo> findPaymentCentersNear(double lat, double len, double radius);
}
