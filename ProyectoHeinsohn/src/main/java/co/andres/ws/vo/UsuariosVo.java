package co.andres.ws.vo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.type.BlobType;

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

	@Column(name = "fecha_nacimiento")
	@JsonbDateFormat(value = "yyyy-MM-dd")
	private LocalDate fecha_nacimiento;

	@Column(name = "fecha_registro")
	@JsonbDateFormat(value = "yyyy-MM-dd")
	private LocalDate fecha_registro;

	@Column(name = "sexo")
	private String sexo;

	@Column(name = "avatar")
	private String avatar;
	
	@OneToMany(mappedBy = "usuariosVo", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<PreferenciasUsuarioVO> preferenciasUsuarioVO;

	public UsuariosVo() {
		this.preferenciasUsuarioVO = new ArrayList<PreferenciasUsuarioVO>();
	}

	public UsuariosVo(Long id, String documento, CiudadesVO ciudades, TipoDocumentoVO tipoDocumento, String nombre,
			String apellidos, String correo, String telefono, LocalDate fecha_nacimiento, LocalDate fecha_registro,
			String sexo, String avatar, List<PreferenciasUsuarioVO> preferenciasUsuarioVO) {
		super();
		this.id = id;
		this.documento = documento;
		this.ciudades = ciudades;
		this.tipoDocumento = tipoDocumento;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.correo = correo;
		this.telefono = telefono;
		this.fecha_nacimiento = fecha_nacimiento;
		this.fecha_registro = fecha_registro;
		this.sexo = sexo;
		this.avatar = avatar;
		this.preferenciasUsuarioVO = preferenciasUsuarioVO;
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

	public LocalDate getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public LocalDate getFecha_registro() {
		return fecha_registro;
	}

	public void setFecha_registro(LocalDate fecha_registro) {
		this.fecha_registro = fecha_registro;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	
	public List<PreferenciasUsuarioVO> getPreferenciasUsuarioVO() {
		return preferenciasUsuarioVO;
	}

	public void setPreferenciasUsuarioVO(List<PreferenciasUsuarioVO> preferenciasUsuarioVO) {
		this.preferenciasUsuarioVO = preferenciasUsuarioVO;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
	

}
