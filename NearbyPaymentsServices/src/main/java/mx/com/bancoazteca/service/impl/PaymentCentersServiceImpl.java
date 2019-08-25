package mx.com.bancoazteca.service.impl;

import java.util.ArrayList;
import java.util.Collections;import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlaceDetails;

import mx.com.bancoazteca.entity.PaymentCenter;
import mx.com.bancoazteca.repository.PaymentCenterRepository;
import mx.com.bancoazteca.service.PaymentCentersService;
import mx.com.bancoazteca.vo.CanalPagoVo;
import mx.com.bancoazteca.vo.PaymentCentersDBResponse;
import net.sf.geographiclib.Geodesic;
import net.sf.geographiclib.GeodesicLine;
import net.sf.geographiclib.GeodesicMask;

@Service
public final class PaymentCentersServiceImpl implements PaymentCentersService {
	
	/*@Autowired
	private PaymentCenterRepository canalPagoRepository;*/
	
	@Autowired
	private GeoApiContext geoApiContext;
	
	@Autowired
	private Gson gson;
	
	private String jsonPaymentCenters = "{\"paymentCenters\":[{\"id\":100,\"nombre\":\"ELEKTRA MEGA DF LA LUNA\",\"calle\":\"CALZADA DE TLALPAN\",\"numero\":\"4355\",\"colonia\":\"BOSQUES DE TETLAMEYA\",\"estado\":\"CIUDAD DE MÉXICO\",\"cp\":4730,\"latitud\":19.2990001,\"longitud\":-99.2048811,\"placeId\":\"ChIJqcB5Q1EAzoUR-HvI2-wV4tk\",\"tipoCanal\":1,\"comision\":0},{\"id\":2544,\"nombre\":\"CHEDRAUI AJUSCO\",\"calle\":\"CARRETERA PICACHO AJUSCO\",\"numero\":\"175\",\"colonia\":\"HEROES DE PADIERNA\",\"estado\":\"CIUDAD DE MÉXICO\",\"cp\":14200,\"latitud\":19.2992294,\"longitud\":-99.2143511,\"placeId\":\"ChIJ12lTsEz-zYUR5xT4G3pOIGw\",\"tipoCanal\":2,\"comision\":9},{\"id\":1221,\"nombre\":\"ELEKTRA MEGA VILLA OLIMPICA\",\"calle\":\"AV. INSURGENTES SUR\",\"numero\":\"3691\",\"colonia\":\"VILLA OLIMPICA\",\"estado\":\"CIUDAD DE MÉXICO\",\"cp\":14020,\"latitud\":19.2939883,\"longitud\":-99.1842281,\"placeId\":\"ChIJV9yKo4kAzoURw2IDtMnId30\",\"tipoCanal\":1,\"comision\":0},{\"id\":6754,\"nombre\":\"OXXO DEGOLLADO\",\"calle\":\"AV. INSURGENTES SUR\",\"numero\":\"3724\",\"colonia\":\"TLALPAN CENTRO\",\"estado\":\"CIUDAD DE MÉXICO\",\"cp\":14000,\"latitud\":19.2937381,\"longitud\":-99.1819356,\"placeId\":\"ChIJCW0ti3wAzoURQfjOMpJ7isM\",\"tipoCanal\":2,\"comision\":9},{\"id\":7654,\"nombre\":\"FARMACIAS DEL AHORRO\",\"calle\":\"AV. INSURGENTES SUR\",\"numero\":\"S/N\",\"colonia\":\"TLALCOLIGIA\",\"estado\":\"CIUDAD DE MÉXICO\",\"cp\":14420,\"latitud\":19.2816313,\"longitud\":-99.174291,\"placeId\":\"ChIJXV0PE40AzoURCVRp8FlvZhw\",\"tipoCanal\":2,\"comision\":6}]}";
	private static final String DIRECTIONS_URL = "https://www.google.com/maps/dir/?api=1&origin=%s,%s&destination=%s,%s";

	@Override
	public List<CanalPagoVo> findPaymentCentersNear(double lat, double len, double radius) {
		List<CanalPagoVo> result = new ArrayList<CanalPagoVo>();
		
		try {
			Geodesic geod = Geodesic.WGS84;
			
			PaymentCentersDBResponse simulatedResult = gson.fromJson(jsonPaymentCenters, PaymentCentersDBResponse.class);
			if (simulatedResult.getPaymentCenters() != null && !simulatedResult.getPaymentCenters().isEmpty()) {
				for (PaymentCenter canalPago: simulatedResult.getPaymentCenters()) {
					GeodesicLine line = geod.InverseLine(lat, len, canalPago.getLatitud(), canalPago.getLongitud(), GeodesicMask.DISTANCE_IN | GeodesicMask.LATITUDE | GeodesicMask.LONGITUDE);
					double distance = line.Distance() / 1000d; 
					
					if (distance <= radius) {
						PlaceDetails placeDetails = PlacesApi.placeDetails(geoApiContext, canalPago.getPlaceId()).await();
						
						CanalPagoVo vo = new CanalPagoVo();
						vo.setNombre(canalPago.getNombre());
						vo.setAbierto(placeDetails.openingHours.openNow);
						if (vo.getAbierto())
							vo.setHorario(String.format("Cierra a las %s hrs", placeDetails.openingHours.periods[GregorianCalendar.getInstance(new Locale("es", "MX")).get(GregorianCalendar.DAY_OF_WEEK)].close.time.toString()));
						else
							vo.setHorario(String.format("Abre a las %s hrs", placeDetails.openingHours.periods[GregorianCalendar.getInstance(new Locale("es", "MX")).get(GregorianCalendar.DAY_OF_WEEK)].open.time.toString()));
						vo.setComision(String.format("$ %d", canalPago.getComision()));
						vo.setIntComision(canalPago.getComision());
						vo.setDireccion(placeDetails.formattedAddress);
						vo.setDistancia(String.format("%s km", String.valueOf(distance)));
						vo.setDblDistancia(distance);
						vo.setUrlDireccion(String.format(DIRECTIONS_URL, String.valueOf(lat), String.valueOf(len), String.valueOf(canalPago.getLatitud()), String.valueOf(canalPago.getLongitud())));
						
						result.add(vo);
					}
				}
				
				if (!result.isEmpty()) {
					result.sort(new Comparator<CanalPagoVo>() {
						@Override
						public int compare(CanalPagoVo o1, CanalPagoVo o2) {
							return o1.getDblDistancia().compareTo(o2.getDblDistancia());
						}
					});
				}
			}
			
			/*List<PaymentCenter> canalesPago = canalPagoRepository.findAll();
			if (canalesPago != null && !canalesPago.isEmpty()) {
				for (PaymentCenter canalPago: canalesPago) {
					GeodesicLine line = geod.InverseLine(lat, len, canalPago.getLatitud(), canalPago.getLongitud(), GeodesicMask.DISTANCE_IN | GeodesicMask.LATITUDE | GeodesicMask.LONGITUDE);
					if (line.Distance() <= radius) {
						PlaceDetails placeDetails = PlacesApi.placeDetails(geoApiContext, canalPago.getPlaceId()).await();
						
						CanalPagoVo vo = new CanalPagoVo();
						vo.setNombre(canalPago.getNombre());
						vo.setAbierto(placeDetails.openingHours.openNow);
						if (vo.getAbierto())
							vo.setHorario(String.format("Cierra a las %s hrs", placeDetails.openingHours.periods[GregorianCalendar.getInstance(new Locale("es", "MX")).get(GregorianCalendar.DAY_OF_WEEK)].close.time.toString()));
						else
							vo.setHorario(String.format("Abre a las %s hrs", placeDetails.openingHours.periods[GregorianCalendar.getInstance(new Locale("es", "MX")).get(GregorianCalendar.DAY_OF_WEEK)].open.time.toString()));
						
						result.add(vo);
					}
				}
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
