package co.andres.ws.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import co.andres.ws.aplicacion.JPAUtil;
import co.andres.ws.vo.CiudadesVO;
import co.andres.ws.vo.DepartamentosVO;

public class DepartamentoDao {

	EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();

	public List<DepartamentosVO> getAllDepartamentos() {
		// TODO Auto-generated method stub
		List<DepartamentosVO> listaDepartamentos = new ArrayList<DepartamentosVO>();

		Query query = entityManager.createQuery("SELECT p FROM DepartamentosVO p");

		listaDepartamentos = query.getResultList();
		return listaDepartamentos;
	}

	public String registrar(DepartamentosVO miDepartamentosVO) {
		// TODO Auto-generated method stub
		entityManager.getTransaction().begin();
		entityManager.persist(miDepartamentosVO);
		entityManager.getTransaction().commit();

		String resp = "ok";
		return resp;
	}

	public String actualizarDepartamento(Long id, DepartamentosVO miDepartamentosVO) {
		// TODO Auto-generated method stub
		String resp = "";

		try {
			DepartamentosVO miDepartamentosVO2 = entityManager.find(DepartamentosVO.class, id);
			entityManager.getTransaction().begin();
			miDepartamentosVO2.setNombre_departamento(miDepartamentosVO.getNombre_departamento());
			entityManager.getTransaction().commit();

			resp = "Actualizado";
		} catch (Exception e) {
			// TODO: handle exception
			resp = "No existe";
		}
		return resp;
	}

	public String eliminar(Long id) {
		// TODO Auto-generated method stub
		String resp = "";
		try {
			DepartamentosVO miDepartamentosVO = entityManager.find(DepartamentosVO.class, id);
			entityManager.getTransaction().begin();
			entityManager.remove(miDepartamentosVO);
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

	

}
