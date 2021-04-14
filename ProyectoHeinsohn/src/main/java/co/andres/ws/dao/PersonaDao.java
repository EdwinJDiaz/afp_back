package co.andres.ws.dao;

import java.util.ArrayList;
import java.util.List;

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
			
		
		entityManager.getTransaction().begin();
		entityManager.persist(usuariosVo);
		entityManager.getTransaction().commit();

		String resp = "ok";
		return resp;
		
	}

	public String actualizarPersona(Long id, UsuariosVo usuariosVo) {
		// TODO Auto-generated method stub
		try {
			UsuariosVo miUsuariosVo = entityManager.find(UsuariosVo.class, id);
			entityManager.getTransaction().begin();
			miUsuariosVo.setCiudades(usuariosVo.getCiudades());
			miUsuariosVo.setDocumento(usuariosVo.getDocumento());
			entityManager.getTransaction().commit();
			
			String resp ="Actualizado";
			return resp;
		} catch (Exception e) {
			// TODO: handle exception
			String resp ="No existe";
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
		}catch (Exception e) {
			// TODO: handle exception
			resp = "No Encontrado";
			JOptionPane.showMessageDialog(null, "No se puede eliminar el ejercicio","ERROR",JOptionPane.ERROR_MESSAGE);
		}
		return resp;
	}
	
	public UsuariosVo consultarPersona(Long id) {
		// TODO Auto-generated method stub

		UsuariosVo miUsuariosVo= entityManager.find(UsuariosVo.class, id);

		if (miUsuariosVo != null) {
			return miUsuariosVo;
		}
		return null;
	}


	

}
