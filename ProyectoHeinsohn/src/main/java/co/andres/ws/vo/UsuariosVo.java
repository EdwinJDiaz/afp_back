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
@Table(name = "usuarios")
public class UsuariosVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "documento")
	private String documento;

	@ManyToOne
	@JoinColumn(name = "id_ciudad", referencedColumnName = "id")
	private CiudadesVO ciudades;

	@ManyToOne
	@JoinColumn(name = "id_tipo_documento", referencedColumnName = "id")
	private TipoDocumentoVO tipoDocumento;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "apellidos")
	private String apellidos;

	@Column(name = "correo")
	private String correo;

	@Column(name = "telefono")
	private String telefono;

	public UsuariosVo() {
		super();
	}

	public UsuariosVo(Long id, String documento, CiudadesVO ciudades, TipoDocumentoVO tipoDocumento, String nombre,
			String apellidos, String correo, String telefono) {
		super();
		this.id = id;
		this.documento = documento;
		this.ciudades = ciudades;
		this.tipoDocumento = tipoDocumento;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.correo = correo;
		this.telefono = telefono;
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

	public TipoDocumentoVO getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumentoVO tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "UsuariosVo [id=" + id + ", documento=" + documento + ", ciudades=" + ciudades + ", tipoDocumento="
				+ tipoDocumento + ", nombre=" + nombre + ", apellidos=" + apellidos + ", correo=" + correo
				+ ", telefono=" + telefono + "]";
	}
	
	

}
