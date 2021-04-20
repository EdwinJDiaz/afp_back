package co.andres.ws.vo;

import java.io.Serializable;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "preferencias_persona")
public class PreferenciasUsuarioVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	
	@ManyToOne
	@JoinColumn(name = "id_preferencia", referencedColumnName = "id")
	private PreferenciasVO preferenciasVO;
	
	@ManyToOne
	@JoinColumn(name = "id_persona", referencedColumnName = "id")
	private UsuariosVo usuariosVo;
	
	

	public PreferenciasUsuarioVO() {
		super();
	}

	public PreferenciasUsuarioVO(Long id, PreferenciasVO preferenciasVO, UsuariosVo usuariosVo) {
		super();
		this.id = id;
		this.preferenciasVO = preferenciasVO;
		this.usuariosVo = usuariosVo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public PreferenciasVO getPreferenciasVO() {
		return preferenciasVO;
	}

	public void setPreferenciasVO(PreferenciasVO preferenciasVO) {
		this.preferenciasVO = preferenciasVO;
	}
	
	@JsonbTransient
	public UsuariosVo getUsuariosVo() {
		return usuariosVo;
	}

	public void setUsuariosVo(UsuariosVo usuariosVo) {
		this.usuariosVo = usuariosVo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

	
}
