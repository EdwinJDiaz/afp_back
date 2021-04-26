package co.andres.ws.dao;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import org.apache.commons.codec.digest.DigestUtils;

import co.andres.ws.aplicacion.JPAUtil;
import co.andres.ws.vo.Fotos;
import co.andres.ws.vo.PreferenciasUsuarioVO;
import co.andres.ws.vo.PreferenciasVO;
import co.andres.ws.vo.UsuariosVo;

import com.cloudinary.utils.ObjectUtils;

public class PersonaDao {

	EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();

	public List<UsuariosVo> GetAllDocumentos() {
		// TODO Auto-generated method stub
		List<UsuariosVo> listaUsuarios = new ArrayList<UsuariosVo>();

		Query query = entityManager.createQuery("SELECT p FROM UsuariosVo p");

		listaUsuarios = query.getResultList();

		for (UsuariosVo usuariosVo : listaUsuarios) {
			System.out.println(usuariosVo);
		}
		return listaUsuarios;
	}

	public List<UsuariosVo> GetAllTipo() {
		// TODO Auto-generated method stub
		List<UsuariosVo> listaUsuarios = new ArrayList<UsuariosVo>();

		Query query = entityManager.createQuery("SELECT p FROM TipoDocumentoVO p");

		listaUsuarios = query.getResultList();
		return listaUsuarios;
	}

	public List<UsuariosVo> ConsultarPersona(String documento) {
		// TODO Auto-generated method stub
		List<UsuariosVo> listaUsuarios = new ArrayList<UsuariosVo>();
		Query query = entityManager.createQuery("SELECT p FROM UsuariosVo p WHERE p.documento=" + documento);

		// query.setParameter("documento", documento);
		listaUsuarios = query.getResultList();

		if (listaUsuarios.size() > 0) {
			return listaUsuarios;
		}

		return null;
	}

	public String Registrar(UsuariosVo usuariosVo) {
		// TODO Auto-generated method stub
		String resp = "";

		Pattern pattern = Pattern.compile(
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

		Matcher mather = pattern.matcher(usuariosVo.getCorreo());

		if (mather.find() == false) {
			resp = "Correo";
			return resp;
		}

		if (usuariosVo.getDocumento().isEmpty()) {
			resp = "blanco";
			return resp;
		}

		List<UsuariosVo> listaUsuarios = new ArrayList<UsuariosVo>();

		Query query = entityManager
				.createQuery("SELECT p FROM UsuariosVo p WHERE p.documento='" + usuariosVo.getDocumento() + "'");

		listaUsuarios = query.getResultList();

		if (listaUsuarios.size() > 0) {

			resp = "existeCedula";
			return resp;

		}

		List<UsuariosVo> listaUsuariosCorreo = new ArrayList<UsuariosVo>();

		query = entityManager.createQuery("SELECT p FROM UsuariosVo p WHERE p.correo='" + usuariosVo.getCorreo() + "'");

		listaUsuariosCorreo = query.getResultList();

		if (listaUsuarios.size() > 0) {
			resp = "existeCorreo";
			return resp;
		}

		try {

			UsuariosVo miUsuariosVo = new UsuariosVo();

			miUsuariosVo.setApellidos(usuariosVo.getApellidos());
			miUsuariosVo.setAvatar(usuariosVo.getAvatar());
			miUsuariosVo.setCiudades(usuariosVo.getCiudades());
			miUsuariosVo.setCorreo(usuariosVo.getCorreo());
			miUsuariosVo.setDocumento(usuariosVo.getDocumento());
			miUsuariosVo.setFecha_nacimiento(usuariosVo.getFecha_nacimiento());

			miUsuariosVo.setFecha_registro(LocalDate.now());

			miUsuariosVo.setSexo(usuariosVo.getSexo());
			miUsuariosVo.setTelefono(usuariosVo.getTelefono());
			miUsuariosVo.setTipoDocumento(usuariosVo.getTipoDocumento());
			miUsuariosVo.setNombre(usuariosVo.getNombre());

			List<PreferenciasUsuarioVO> listaPreferencias = new ArrayList<PreferenciasUsuarioVO>();

			for (PreferenciasUsuarioVO usuariosVo2 : usuariosVo.getPreferenciasUsuarioVO()) {

				PreferenciasUsuarioVO preferenciasUsuarioVO = new PreferenciasUsuarioVO();

				System.out.println("PREFERENCIA");
				preferenciasUsuarioVO.setPreferenciasVO(usuariosVo2.getPreferenciasVO());
				System.out.println("USUARIO");
				preferenciasUsuarioVO.setUsuariosVo(miUsuariosVo);

				// listaPreferencias.add(preferenciasUsuarioVO);

				listaPreferencias.add(preferenciasUsuarioVO);

			}

			for (PreferenciasUsuarioVO preferenciasUsuarioVO : listaPreferencias) {
				System.out.println("BIEN");
				miUsuariosVo.getPreferenciasUsuarioVO().add(preferenciasUsuarioVO);
			}

			String hashpass = DigestUtils.md5Hex(usuariosVo.getPassword()).toUpperCase();

			miUsuariosVo.setPassword(hashpass);

			// miUsuariosVo.setPreferenciasUsuarioVO(listaPreferencias);

			entityManager.getTransaction().begin();
			entityManager.persist(miUsuariosVo);
			entityManager.getTransaction().commit();

			resp = "ok";

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			resp = "MAL";
		}

		return resp;
	}

	public String actualizarPersona(Long id, UsuariosVo usuariosVo) {
		// TODO Auto-generated method stub
		String resp = "";

		Pattern pattern = Pattern.compile(
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

		Matcher mather = pattern.matcher(usuariosVo.getCorreo());

		if (mather.find() == false) {
			resp = "Correo";
			return resp;
		}

		if (usuariosVo.getDocumento().isEmpty()) {
			resp = "blanco";
			return resp;
		}
		List<UsuariosVo> listaUsuarios = new ArrayList<UsuariosVo>();

		Query query = entityManager
				.createQuery("SELECT p FROM UsuariosVo p WHERE p.documento='" + usuariosVo.getDocumento() + "'");

		listaUsuarios = query.getResultList();

		if (listaUsuarios.size() >= 1) {

			if (listaUsuarios.get(0).getDocumento().equals(usuariosVo.getDocumento())) {
				System.out.println("son iguales");
				listaUsuarios.clear();
			} else {
				resp = "existeCedula";
				return resp;
			}
		}

		listaUsuarios = new ArrayList<UsuariosVo>();

		query = entityManager.createQuery("SELECT p FROM UsuariosVo p WHERE p.correo='" + usuariosVo.getCorreo() + "'");

		listaUsuarios = query.getResultList();

		if (listaUsuarios.size() >= 1) {

			if (listaUsuarios.get(0).getCorreo().equals(usuariosVo.getCorreo())) {
				System.out.println("son iguales");
			} else {
				resp = "existeCorreo";
				return resp;
			}
		}

		try {
			UsuariosVo miUsuariosVo = entityManager.find(UsuariosVo.class, id);

			if (miUsuariosVo == null) {
				resp = "No existe";
				return resp;
			}
			entityManager.getTransaction().begin();

			miUsuariosVo.setCiudades(usuariosVo.getCiudades());
			miUsuariosVo.setDocumento(usuariosVo.getDocumento());
			miUsuariosVo.setNombre(usuariosVo.getNombre());
			miUsuariosVo.setApellidos(usuariosVo.getApellidos());
			miUsuariosVo.setCorreo(usuariosVo.getCorreo());
			miUsuariosVo.setTelefono(usuariosVo.getTelefono());
			miUsuariosVo.setTipoDocumento(usuariosVo.getTipoDocumento());

			String hashpass = DigestUtils.md5Hex(usuariosVo.getPassword()).toUpperCase();

			miUsuariosVo.setPassword(hashpass);

			if (usuariosVo.getAvatar() == null) {

			} else {
				miUsuariosVo.setAvatar(usuariosVo.getAvatar());
			}

			miUsuariosVo.setFecha_nacimiento(usuariosVo.getFecha_nacimiento());

			miUsuariosVo.setSexo(usuariosVo.getSexo());

			List<PreferenciasUsuarioVO> listaPreferencias = new ArrayList<PreferenciasUsuarioVO>();

			for (PreferenciasUsuarioVO usuariosVo2 : usuariosVo.getPreferenciasUsuarioVO()) {

				PreferenciasUsuarioVO preferenciasUsuarioVO = new PreferenciasUsuarioVO();

				preferenciasUsuarioVO.setPreferenciasVO(usuariosVo2.getPreferenciasVO());

				preferenciasUsuarioVO.setUsuariosVo(miUsuariosVo);

				listaPreferencias.add(preferenciasUsuarioVO);

			}

			Query query2 = entityManager.createQuery(
					"DELETE FROM PreferenciasUsuarioVO p WHERE p.usuariosVo=" + miUsuariosVo.getDocumento());
			query2.executeUpdate();

			miUsuariosVo.setPreferenciasUsuarioVO(listaPreferencias);
			/*
			 * for (PreferenciasUsuarioVO preferenciasUsuarioVO : listaPreferencias) {
			 * System.out.println("BIEN"); miUsuariosVo.getPreferenciasUsuarioVO().clear();
			 * miUsuariosVo.getPreferenciasUsuarioVO().add(preferenciasUsuarioVO); }
			 */

			entityManager.getTransaction().commit();

			resp = "Actualizado";
			return resp;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			resp = "vacio";
			return resp;
		}

	}

	public String eliminar(Long id) {
		// TODO Auto-generated method stub
		String resp = "";
		try {
			UsuariosVo miUsuariosVo = consultarPersona(id);

			entityManager.getTransaction().begin();
			entityManager.remove(miUsuariosVo);
			entityManager.getTransaction().commit();

			resp = "Eliminado";
		} catch (Exception e) {
			// TODO: handle exception
			resp = "No Encontrado";
			JOptionPane.showMessageDialog(null, "No se puede eliminar el ejercicio", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		}
		return resp;
	}

	public UsuariosVo consultarPersona(Long id) {
		// TODO Auto-generated method stub

		UsuariosVo miUsuariosVo = entityManager.find(UsuariosVo.class, id);

		if (miUsuariosVo != null) {
			return miUsuariosVo;
		}
		return null;
	}

	public String registrarFotos(String fotoscol, String documento) throws IOException {
		// TODO Auto-generated method stub

		try {
			/*
			 * Map params = ObjectUtils.asMap( "public_id", "primeraFoto", "overwrite",
			 * true, "notification_url", " http://res.cloudinary.com/hdjsownnk",
			 * "resource_type", "auto");
			 * 
			 * 
			 * 
			 * 
			 * Map uploadResult = cloudinary.uploader().upload(Fotoscol, params);
			 */

			Query query = entityManager.createQuery("SELECT p FROM UsuariosVo p WHERE p.documento='" + documento + "'");

			List<UsuariosVo> listaUsuarios = new ArrayList<UsuariosVo>();

			listaUsuarios = query.getResultList();

			UsuariosVo miUsuariosVo = entityManager.find(UsuariosVo.class, listaUsuarios.get(0).getId());

			entityManager.getTransaction().begin();
			miUsuariosVo.setAvatar(fotoscol);
			entityManager.getTransaction().commit();

			return "true";

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "MAL";
		}

	}

	public List<PreferenciasVO> GetPreferencia() {
		// TODO Auto-generated method stub
		List<PreferenciasVO> listaPreferencias = new ArrayList<PreferenciasVO>();

		Query query = entityManager.createQuery("SELECT p FROM PreferenciasVO p");

		listaPreferencias = query.getResultList();
		return listaPreferencias;
	}

	public List<Fotos> GetFotos() {
		// TODO Auto-generated method stub
		List<Fotos> listaFotos = new ArrayList<Fotos>();

		Query query = entityManager.createQuery("SELECT p FROM Fotos p");

		listaFotos = query.getResultList();
		return listaFotos;

	}

	public List<UsuariosVo> GetGrafica() {
		// TODO Auto-generated method stub

		List<UsuariosVo> listaGraficaList = new ArrayList<UsuariosVo>();

		Query query = entityManager.createQuery(
				"SELECT count(p.ciudades.id), p.ciudades.nombre_ciudad FROM UsuariosVo p group by p.ciudades.id");
		listaGraficaList = query.getResultList();
		return listaGraficaList;
	}

	public List<String> getFechas(String fecha1, String fecha2) {
		// TODO Auto-generated method stub

		try {
			List<String> listaFechas = new ArrayList<String>();

			Query query = entityManager.createQuery(
					"SELECT p FROM UsuariosVo p WHERE p.fecha_registro BETWEEN '" + fecha1 + "' AND '" + fecha2 + "'");

			listaFechas = query.getResultList();

			if (listaFechas.size() > 0) {
				return listaFechas;
			}

			return null;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

	}

	public String consultarLogin(UsuariosVo usuariosVo) {
		// TODO Auto-generated method stub

		String resp = "";
		
		Pattern pattern = Pattern.compile(
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

		Matcher mather = pattern.matcher(usuariosVo.getCorreo());

		if (mather.find() == false) {
			resp = "Correo";
			return resp;
		}

		List<UsuariosVo> listaFechas = new ArrayList<UsuariosVo>();

		Query query = entityManager
				.createQuery("SELECT p FROM UsuariosVo p WHERE p.correo ='" + usuariosVo.getCorreo() + "'");

		listaFechas = query.getResultList();

		if (listaFechas.size() < 1) {
			resp = "No existe";
			return resp;
		}

		String pass = usuariosVo.getPassword();

		String hashpass = DigestUtils.md5Hex(pass).toUpperCase();

		if (listaFechas.get(0).getPassword().equals(hashpass)) {
			return "ok";
		}

		return "MAL";

	}
}
