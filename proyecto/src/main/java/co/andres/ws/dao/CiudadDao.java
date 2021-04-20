package co.andres.ws.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import co.andres.ws.aplicacion.JPAUtil;
import co.andres.ws.vo.CiudadesVO;

public class CiudadDao {

	EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();

	public List<CiudadesVO> getAllCities() {
		// TODO Auto-generated method stub
		List<CiudadesVO> listaCiudades = new ArrayList<CiudadesVO>();

		Query query = entityManager.createQuery("SELECT p FROM CiudadesVO p");

		listaCiudades = query.getResultList();
		return listaCiudades;

	}

	public List<CiudadesVO> consultarCiudad(Long id) {
		// TODO Auto-generated method stub
		List<CiudadesVO> listaCiudades = new ArrayList<CiudadesVO>();
		Query query = entityManager.createQuery("SELECT p FROM CiudadesVO p WHERE p.departamentos.id=" + id);

		listaCiudades = query.getResultList();
		return listaCiudades;
	}

	public String Registrar(CiudadesVO ciudadesVO) {
		// TODO Auto-generated method stub
		String resp = "";
		List<CiudadesVO> listaCiudades = new ArrayList<CiudadesVO>();
		Query query = entityManager.createQuery(
				"SELECT p FROM CiudadesVO p WHERE p.nombre_ciudad = '" + ciudadesVO.getNombre_ciudad() + "' AND p.departamentos.id='"+ciudadesVO.getDepartamentos().getId()+"'");

		listaCiudades = query.getResultList();

		if (ciudadesVO.getNombre_ciudad().isEmpty() || ciudadesVO.getDepartamentos().getId() == null) {
			resp = "blanco";
			return resp;
		}

		if (listaCiudades.size() > 0) {
			resp = "existe";
			return resp;
		}
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(ciudadesVO);
			entityManager.getTransaction().commit();

			resp = "ok";
			return resp;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			resp = "MAL";
			return resp;
		}

	}

	public String actualizarCiudad(Long id, CiudadesVO miCiudadesVO) {
		// TODO Auto-generated method stub
		String resp = "";

		List<CiudadesVO> listaCiudades = new ArrayList<CiudadesVO>();
		Query query = entityManager.createQuery(
				"SELECT p FROM CiudadesVO p WHERE p.nombre_ciudad = '" + miCiudadesVO.getNombre_ciudad() + "' AND p.departamentos.id='"+miCiudadesVO.getDepartamentos().getId()+"'");

		listaCiudades = query.getResultList();

		if (listaCiudades.size() >= 1) {
			if (listaCiudades.get(0).getNombre_ciudad().equals(miCiudadesVO.getNombre_ciudad())) {
				
				if (listaCiudades.get(0).getId() != miCiudadesVO.getDepartamentos().getId()) {
					resp = "existe";
					return resp;
				}
			} 
		}

		if (miCiudadesVO.getNombre_ciudad().isEmpty() || miCiudadesVO.getDepartamentos().getId() == null) {
			resp = "blanco";
			return resp;
		}
		try {

			
			CiudadesVO miCiudadesVO2 = entityManager.find(CiudadesVO.class, id);
			
			if (miCiudadesVO2 == null) {
				resp = "No existe";
				return resp;
			}
			
			entityManager.getTransaction().begin();
			miCiudadesVO2.setNombre_ciudad(miCiudadesVO.getNombre_ciudad());
			miCiudadesVO2.setDepartamentos(miCiudadesVO.getDepartamentos());
			entityManager.getTransaction().commit();

			resp = "Actualizado";
			return resp;

		} catch (Exception e) {
			// TODO: handle exception
			resp = "existe";
			System.out.println(resp);
			return resp;
		}

	}

	public String eliminar(Long id) {
		// TODO Auto-generated method stub
		String resp = "";
		try {
			CiudadesVO miCiudadesVO = consultarCiudadId(id);
			entityManager.getTransaction().begin();
			entityManager.remove(miCiudadesVO);
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

	public CiudadesVO consultarCiudadId(Long id) {
		// TODO Auto-generated method stub
		try {
			CiudadesVO miCiudadesVO = entityManager.find(CiudadesVO.class, id);

			if (miCiudadesVO != null) {
				return miCiudadesVO;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		return null;

	}

}
