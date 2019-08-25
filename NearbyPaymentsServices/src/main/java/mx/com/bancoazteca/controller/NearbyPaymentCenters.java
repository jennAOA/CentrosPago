package mx.com.bancoazteca.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mx.com.bancoazteca.service.PaymentCentersService;
import mx.com.bancoazteca.vo.CanalPagoVo;

@RestController
@RequestMapping(value = "centers")
public class NearbyPaymentCenters {
	private static final Logger log = LoggerFactory.getLogger(NearbyPaymentCenters.class);
	
	@Autowired
	private PaymentCentersService paymentCentersService;

	@GetMapping(value = "/version", produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String getVersion(HttpServletResponse response) {
		return "1.0";
	}
	
	@GetMapping(path = "{latitude}/{longitude}/{radius}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CanalPagoVo> getPaymentCenters(@PathVariable("latitude") String latitude, @PathVariable("longitude") String longitude, @PathVariable("radius") String radius) {
		return paymentCentersService.findPaymentCentersNear(Double.parseDouble(latitude), Double.parseDouble(longitude), Double.parseDouble(radius));
	}
}
