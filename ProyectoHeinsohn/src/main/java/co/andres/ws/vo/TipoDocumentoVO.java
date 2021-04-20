package co.andres.ws.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tiposdocumentos")
public class TipoDocumentoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "tipo_documento")
	private String tipo_documento;

	public TipoDocumentoVO() {
		super();
	}

	public TipoDocumentoVO(Long id, String tipo_documento) {
		super();
		this.id = id;
		this.tipo_documento = tipo_documento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo_documento() {
		return tipo_documento;
	}

	public void setTipo_documento(String tipo_documento) {
		this.tipo_documento = tipo_documento;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	

}
