package co.andres.ws.vo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="usuarios")
public class UsuariosVo  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name = "documento")
	private String documento;
	
	@ManyToOne
	@JoinColumn(name="id_ciudad", referencedColumnName = "id")
	private CiudadesVO ciudades;

	public UsuariosVo(Long id, String documento, CiudadesVO ciudades) {
		super();
		this.id = id;
		this.documento = documento;
		this.ciudades = ciudades;
	}
	
	

	public UsuariosVo() {
		super();
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public CiudadesVO getCiudades() {
		return ciudades;
	}

	public void setCiudades(CiudadesVO ciudades) {
		this.ciudades = ciudades;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@Override
	public String toString() {
		return "UsuariosVo [id=" + id + ", documento=" + documento + ", ciudades=" + ciudades + "]";
	}
	
	

	
}
