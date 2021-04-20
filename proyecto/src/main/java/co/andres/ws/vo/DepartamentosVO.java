package co.andres.ws.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "departamento")
public class DepartamentosVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "nombre_departamento")
	private String nombre_departamento;
	
	
	public DepartamentosVO() {
		super();
	}

	public DepartamentosVO(Long id, String nombre_departamento) {
		super();
		this.id = id;
		this.nombre_departamento = nombre_departamento;
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre_departamento() {
		return nombre_departamento;
	}

	public void setNombre_departamento(String nombre_departamento) {
		this.nombre_departamento = nombre_departamento;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

	
}
