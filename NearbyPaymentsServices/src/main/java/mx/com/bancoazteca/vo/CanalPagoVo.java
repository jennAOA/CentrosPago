package mx.com.bancoazteca.vo;

import java.io.Serializable;

public class CanalPagoVo implements Serializable {
	private String nombre;
	private String direccion;
	private Boolean abierto;
	private String horario;
	private String comision;
	private String distancia;
	private String urlDireccion;
	private Double dblDistancia;
	private Integer intComision;
	
	
	public CanalPagoVo() {
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public Boolean getAbierto() {
		return abierto;
	}


	public void setAbierto(Boolean abierto) {
		this.abierto = abierto;
	}


	public String getHorario() {
		return horario;
	}


	public void setHorario(String horario) {
		this.horario = horario;
	}


	public String getComision() {
		return comision;
	}


	public void setComision(String comision) {
		this.comision = comision;
	}


	public String getDistancia() {
		return distancia;
	}


	public void setDistancia(String distancia) {
		this.distancia = distancia;
	}


	public String getUrlDireccion() {
		return urlDireccion;
	}


	public void setUrlDireccion(String urlDireccion) {
		this.urlDireccion = urlDireccion;
	}


	public Double getDblDistancia() {
		return dblDistancia;
	}


	public void setDblDistancia(Double dblDistancia) {
		this.dblDistancia = dblDistancia;
	}


	public Integer getIntComision() {
		return intComision;
	}


	public void setIntComision(Integer intComision) {
		this.intComision = intComision;
	}
}
