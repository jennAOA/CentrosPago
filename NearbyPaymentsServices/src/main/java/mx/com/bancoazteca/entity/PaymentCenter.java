package mx.com.bancoazteca.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TACANALPAGO")
public class PaymentCenter {
	
	@Id
	@Column(name="FIIDCANALPAGO")
	private Integer id;
	
	@Column(name = "FCCANALPAGO")
	private String nombre;
	
	@Column(name = "FCCALLE")
	private String calle;
	
	@Column(name = "FCNUMERO")
	private String numero;
	
	@Column(name = "FCCOLONIA")
	private String colonia;
	
	@Column(name = "FCESTADO")
	private String estado;
	
	@Column(name = "FICODIGOPOSTAL")
	private Integer cp;
	
	@Column(name = "FNLATITUD")
	private Double latitud;
	
	@Column(name = "FNLONGITUD")
	private Double longitud;
	
	@Column(name = "FCPLACEID")
	private String placeId;
	
	@Column(name = "FITIPOCANAL")
	private Integer tipoCanal;
	
	@Column(name = "FNCOMISION")
	private Integer comision;
	
	public PaymentCenter() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getColonia() {
		return colonia;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getCp() {
		return cp;
	}

	public void setCp(Integer cp) {
		this.cp = cp;
	}

	public Double getLatitud() {
		return latitud;
	}

	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

	public Double getLongitud() {
		return longitud;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}

	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	public Integer getTipoCanal() {
		return tipoCanal;
	}

	public void setTipoCanal(Integer tipoCanal) {
		this.tipoCanal = tipoCanal;
	}

	public Integer getComision() {
		return comision;
	}

	public void setComision(Integer comision) {
		this.comision = comision;
	}
}
