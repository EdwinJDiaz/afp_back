package co.andres.ws.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "preferencias")
public class PreferenciasVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	
	@Column(name = "nombre_preferencia")
	private String nombre_preferencia;
	



	public PreferenciasVO() {
		super();
	}


	public PreferenciasVO(Long id, String nombre_preferencia) {
		super();
		this.id = id;
		this.nombre_preferencia = nombre_preferencia;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNombre_preferencia() {
		return nombre_preferencia;
	}


	public void setNombre_preferencia(String nombre_preferencia) {
		this.nombre_preferencia = nombre_preferencia;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
	
	
	

}
