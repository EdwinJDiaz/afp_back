package co.andres.ws.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import co.andres.ws.dao.CiudadDao;
import co.andres.ws.vo.CiudadesVO;

@Path("ciudad")
public class ServicesCiudad {
	
	CiudadDao miCiudadDao = new CiudadDao();
	
	@GET
	@Path("get")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<CiudadesVO> getLista(){
		return miCiudadDao.getAllCities();
	}
	
	@GET
	@Path("get/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public List<CiudadesVO> getCiudad(@PathParam("id") Long id) {
		return miCiudadDao.consultarCiudad(id);
				
		
	}
	
	
	@POST
	@Path("add")
	@Consumes({MediaType.APPLICATION_JSON})
	public Response registroCiudad(CiudadesVO ciudadesVO) {
		try {
			String resp = miCiudadDao.Registrar(ciudadesVO);
			if (resp.equals("ok")) {
				return Response.ok().build();
			} else {
				return Response.status(Response.Status.NOT_FOUND).build();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PUT
	@Path("update/{id}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response actualizarCiudad(@PathParam("id") Long id, CiudadesVO miCiudadesVO) {
		try {
			String resp = miCiudadDao.actualizarCiudad(id, miCiudadesVO);
			if (resp.equals("Actualizado")) {
				return Response.ok().build();
			}else{
				return Response.status(Response.Status.NOT_FOUND).build();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	@DELETE
	@Path("delete/{id}")
	public Response eliminarCiudad(@PathParam("id") Long id) {
		try {
			String resp = miCiudadDao.eliminar(id);
			if (resp.equals("Eliminado")) {
				return Response.ok().build();
			}else {
				if (!resp.equals("No Encontrado")) {
					return Response.status(204).build();
				}else {
					return Response.status(Response.Status.NOT_FOUND).build();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		
	}

}
