package co.andres.ws.rest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
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
import co.andres.ws.vo.Fotos;
import co.andres.ws.vo.PreferenciasVO;
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
	@Path("preferencia")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<PreferenciasVO> getListaPreferencia() {
		return personaDao.GetPreferencia();

	}

	@GET
	@Path("grafica")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<UsuariosVo> getListaGrafica() {
		return personaDao.GetGrafica();

	}

	@GET
	@Path("tipoDocumentos")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<UsuariosVo> getListaTipo() {
		return personaDao.GetAllTipo();

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
			} else if (resp.equals("Correo")) {
				return Response.status(Response.Status.NOT_ACCEPTABLE).build();
			} else if (resp.equals("blanco")) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			} else if (resp.equals("existeCedula")) {
				return Response.status(Response.Status.CONFLICT).build();
			}else if (resp.equals("existeCorreo")) {
				return Response.status(Response.Status.EXPECTATION_FAILED).build();
			} else {
				return Response.status(Response.Status.PARTIAL_CONTENT).build();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}

	}

	@GET
	@Path("fotos")
	@Produces("application/json")
	public List<Fotos> getListaFotos() {
		return personaDao.GetFotos();

	}

	@POST
	@Path("imagen/{documento}")
	@Consumes("multipart/form-data")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response registroPreferencias(@PathParam("documento") String documento, String Fotoscol) throws IOException {

		String[] parts = Fotoscol.split(" ");

		String separar = parts[2];

		String[] partes = separar.split("\r\n");

		Fotoscol = partes[2];

		String resp = personaDao.registrarFotos(Fotoscol, documento);

		try {
			if (resp.equals("true")) {
				return Response.ok().build();
			} else {
				return Response.status(Response.Status.PARTIAL_CONTENT).build();
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
	public Response actualizarUsuarios(@PathParam("id") Long id, UsuariosVo usuariosVo) {
		try {
			String resp = personaDao.actualizarPersona(id, usuariosVo);
			if (resp.equals("Actualizado")) {
				return Response.ok().build();
			} else if (resp.equals("Correo")) {
				return Response.status(Response.Status.NOT_ACCEPTABLE).build();
			} else if (resp.equals("blanco")) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			} else if (resp.equals("No existe")) {
				return Response.status(Response.Status.CONFLICT).build();
			} else if (resp.equals("existeCedula")) {
				return Response.status(Response.Status.EXPECTATION_FAILED).build();
			}else if (resp.equals("existeCorreo")) {
				return Response.status(Response.Status.FORBIDDEN).build();
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
	public Response eliminarPersona(@PathParam("id") Long id) {
		try {
			String resp = personaDao.eliminar(id);
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
	
	
	
	@GET
	@Path("fechas/{fecha1}/{fecha2}")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<String> GetListaFechas(@PathParam("fecha1") String fecha1,@PathParam("fecha2") String fecha2){
		List<String> listaUsuarios = new ArrayList<String>(); 
				listaUsuarios = personaDao.getFechas(fecha1, fecha2);
				
				if (listaUsuarios != null) {
					return listaUsuarios;
				}else {
					return null;
				}
	}
	
	
	
	@POST
	@Path("login")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response login(UsuariosVo usuariosVo) {
		
		String resp = personaDao.consultarLogin(usuariosVo);
		
		if (resp.equals("No existe")) {
			return Response.status(Response.Status.NO_CONTENT).build();
		}else if (resp.equals("ok")) {
			return Response.ok().build();
		}else if(resp.equals("MAL")) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).build();
		}else if(resp.equals("Correo")) {
			return Response.status(Response.Status.CONFLICT).build();
		}
		else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	
		
	}
	
}
