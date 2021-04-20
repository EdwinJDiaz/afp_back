package co.andres.ws.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import co.andres.ws.aplicacion.JPAUtil;
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
		String resp = "";

		List<DepartamentosVO> listaDepartamentos = new ArrayList<DepartamentosVO>();
		Query query = entityManager.createQuery("SELECT p FROM DepartamentosVO p WHERE p.nombre_departamento = '" + miDepartamentosVO.getNombre_departamento()+"'");

		listaDepartamentos = query.getResultList();

		if (listaDepartamentos.size() > 0) {
			resp = "existe";
			return resp;
		}
		
		if (miDepartamentosVO.getNombre_departamento().isEmpty()) {
			resp = "blanco";
			return resp;
		}
		
		entityManager.getTransaction().begin();
		entityManager.persist(miDepartamentosVO);
		entityManager.getTransaction().commit();

		resp = "ok";
		return resp;
	}

	public String actualizarDepartamento(Long id, DepartamentosVO miDepartamentosVO) {
		// TODO Auto-generated method stub
		String resp = "";
		
		List<DepartamentosVO> listaDepartamentos = new ArrayList<DepartamentosVO>();
		Query query = entityManager.createQuery("SELECT p FROM DepartamentosVO p WHERE p.nombre_departamento = '" + miDepartamentosVO.getNombre_departamento()+"'");

		listaDepartamentos = query.getResultList();
		
		if (listaDepartamentos.size() >= 1 ) {
			if (listaDepartamentos.get(0).getNombre_departamento().equals(miDepartamentosVO.getNombre_departamento()) ) {
				System.out.println("Son iguales");
			}else {
				resp = "existe";
				return resp;
			}
			
		}
		
		if (miDepartamentosVO.getNombre_departamento().isEmpty()) {
			resp = "blanco";
			return resp;
		}

		try {
			DepartamentosVO miDepartamentosVO2 = entityManager.find(DepartamentosVO.class, id);
			
			if (miDepartamentosVO2 == null) {
				resp = "NO existe";
				return resp;
			}
			
			entityManager.getTransaction().begin();
			miDepartamentosVO2.setNombre_departamento(miDepartamentosVO.getNombre_departamento());
			entityManager.getTransaction().commit();

			resp = "Actualizado";
		} catch (Exception e) {
			// TODO: handle exception
			resp = "existe";
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
