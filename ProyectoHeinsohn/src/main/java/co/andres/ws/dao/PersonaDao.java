package co.andres.ws.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import org.hibernate.Criteria;

import co.andres.ws.vo.UsuariosVo;

import co.andres.ws.aplicacion.JPAUtil;

public class PersonaDao {

	EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();

	public List<UsuariosVo> GetAllDocumentos() {
		// TODO Auto-generated method stub
		List<UsuariosVo> listaUsuarios = new ArrayList<UsuariosVo>();

		Query query = entityManager.createQuery("SELECT p FROM UsuariosVo p");

		listaUsuarios = query.getResultList();
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

		Query query = entityManager.createQuery("SELECT p FROM UsuariosVo p WHERE p.documento="
				+ usuariosVo.getDocumento() + " OR p.correo='" + usuariosVo.getCorreo() + "'");

		listaUsuarios = query.getResultList();

		if (listaUsuarios.size() > 0) {
			resp = "existe";
			return resp;
		}

		try {
			entityManager.getTransaction().begin();
			entityManager.persist(usuariosVo);
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
				resp = "existe";
				return resp;
			}
		}

		listaUsuarios = new ArrayList<UsuariosVo>();

		query = entityManager
				.createQuery("SELECT p FROM UsuariosVo p WHERE p.documento='" + usuariosVo.getCorreo() + "'");

		listaUsuarios = query.getResultList();

		if (listaUsuarios.size() >= 1) {

			if (listaUsuarios.get(0).getCorreo().equals(usuariosVo.getCorreo())) {
				System.out.println("son iguales");
			} else {
				resp = "existe";
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
			entityManager.getTransaction().commit();

			resp = "Actualizado";
			return resp;
		} catch (Exception e) {
			// TODO: handle exception
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

}
