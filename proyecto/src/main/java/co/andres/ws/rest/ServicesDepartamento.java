package co.andres.ws.rest;

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

import co.andres.ws.dao.DepartamentoDao;
import co.andres.ws.vo.DepartamentosVO;

@Path("departamento")
public class ServicesDepartamento {
	
	DepartamentoDao miDepartamentosDao = new DepartamentoDao();

	@GET
	@Path("get")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<DepartamentosVO> getLista() {
		return miDepartamentosDao.getAllDepartamentos();
	}

	@POST
	@Path("add")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response registroDepartamento(DepartamentosVO miDepartamentosVO) {
		try {
			String resp = miDepartamentosDao.registrar(miDepartamentosVO);
			if (resp.equals("ok")) {
				return Response.ok().build();
			} else if (resp.equals("existe")) {
				return Response.status(Response.Status.CONFLICT).build();
			} else if (resp.equals("blanco")) {
				return Response.status(Response.Status.PARTIAL_CONTENT).build();
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
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response actualizarDepartamento(@PathParam("id") Long id, DepartamentosVO miDepartamentosVO) {
		try {
			String resp = miDepartamentosDao.actualizarDepartamento(id, miDepartamentosVO);
			if (resp.equals("Actualizado")) {
				return Response.ok().build();
			} else if (resp.equals("existe")) {
				return Response.status(Response.Status.CONFLICT).build();
			} else if (resp.equals("blanco")) {
				return Response.status(Response.Status.PARTIAL_CONTENT).build();
			} else if (resp.equals("No existe")) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			} else {
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
	public Response eliminarDepartamento(@PathParam("id") Long id) {
		try {
			String resp = miDepartamentosDao.eliminar(id);
			if (resp.equals("Eliminado")) {
				return Response.ok().build();
			} else {
				if (!resp.equals("No Encontrado")) {
					return Response.status(204).build();
				} else {
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
