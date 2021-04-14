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

import co.andres.ws.dao.PersonaDao;
import co.andres.ws.vo.UsuariosVo;

@Path("documento")
public class ServicesPersona {

	PersonaDao personaDao = new PersonaDao();

	@GET
	@Path("documentos")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<UsuariosVo> getLista() {
		return personaDao.GetAllDocumentos();

	}

	@GET
	@Path("documento/{documento}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getPersona(@PathParam("documento") String documento) {
		List<UsuariosVo> listaUsuarios = new ArrayList<UsuariosVo>();
		listaUsuarios = personaDao.ConsultarPersona(documento);

		UsuariosVo usuariosVo = listaUsuarios.get(0);

		if (usuariosVo != null) {
			return Response.ok(usuariosVo).build();
		}

		return Response.status(Response.Status.NOT_FOUND).build();

	}

	@POST
	@Path("add")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response registroDocumento(UsuariosVo usuariosVo) {
		try {

			String resp = personaDao.Registrar(usuariosVo);
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
	public Response actualizarUsuarios(@PathParam("id") Long id, UsuariosVo usuariosVo) {
		try {
			String resp = personaDao.actualizarPersona(id, usuariosVo);
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
	public Response eliminarPersona(@PathParam("id") Long id) {
		try {
			String resp = personaDao.eliminar(id);
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
