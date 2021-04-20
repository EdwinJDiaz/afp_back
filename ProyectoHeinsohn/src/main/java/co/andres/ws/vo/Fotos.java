
package co.andres.ws.vo;

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
	@Column(name = "Fotoscol")
	private byte[] Fotoscol;

	public Fotos() {
		super();
	}

	public Fotos(Long id, byte[] fotoscol) {
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

	public byte[] getFotoscol() {
		return Fotoscol;
	}

	public void setFotoscol(byte[] fotoscol) {
		Fotoscol = fotoscol;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Fotos [id=" + id + ", Fotoscol=" + Arrays.toString(Fotoscol) + "]";
	}
	
	
	
	

}
