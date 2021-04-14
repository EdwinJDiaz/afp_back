package co.andres.ws.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ciudades")
public class CiudadesVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name = "nombre_ciudad")
	private String nombre_ciudad;
	
	@ManyToOne
	@JoinColumn(name = "id_departamento", referencedColumnName = "id" )
	private DepartamentosVO departamentos;

	public CiudadesVO(Long id, String nombre_ciudad, DepartamentosVO departamentos) {
		super();
		this.id = id;
		this.nombre_ciudad = nombre_ciudad;
		this.departamentos = departamentos;
	}
	
	

	public CiudadesVO() {
		super();
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre_ciudad() {
		return nombre_ciudad;
	}

	public void setNombre_ciudad(String nombre_ciudad) {
		this.nombre_ciudad = nombre_ciudad;
	}

	public DepartamentosVO getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(DepartamentosVO departamentos) {
		this.departamentos = departamentos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@Override
	public String toString() {
		return "CiudadesVO [id=" + id + ", nombre_ciudad=" + nombre_ciudad + ", departamentos=" + departamentos + "]";
	}

	
	

}
