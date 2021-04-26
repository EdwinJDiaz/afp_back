package co.andres.ws.vo;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.type.BlobType;

@Entity
@Table(name = "fotos")
public class Fotos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idFotos")
	private Long id;
	
	@Lob
	@Column(name = "Fotoscol", columnDefinition ="BLOB")
	private String Fotoscol;

	public Fotos() {
		super();
	}

	public Fotos(Long id, String fotoscol) {
		super();
		this.id = id;
		Fotoscol = fotoscol;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFotoscol() {
		return Fotoscol;
	}

	public void setFotoscol(String fotoscol2) {
		Fotoscol = fotoscol2;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
	
	
	
	

}
