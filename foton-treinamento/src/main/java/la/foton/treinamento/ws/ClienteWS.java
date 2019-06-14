package la.foton.treinamento.ws;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import la.foton.treinamento.entity.Cliente;
import la.foton.treinamento.service.ClienteService;
import la.foton.treinamento.util.NegocioException;

@Path("/cliente")
@Stateless
@Produces(MediaType.APPLICATION_JSON)
public class ClienteWS {
	
	@Inject
	private ClienteService service;
	
	@POST
	@Path("/{cpf}/cadastra")
	public Response cadastraCliente(@PathParam("cpf") String cpf,@QueryParam("nome")String nome) {
		Cliente cliente = null;
		try {
			cliente = service.cadastraCliente(cpf, nome);
		} catch (NegocioException e) {
			throw new WebApplicationException(
					Response.status(Response.Status.BAD_REQUEST).entity(e.getMensagem().getDescricao()).build());			
		}
		
		// URI uri= UriBuilder.fromPath("cliente/{cpf}").build(cpf);
		
		//return Response.created(uri).type(MediaType.APPLICATION_JSON_TYPE).build();
		return Response.ok().entity(cliente).build();
				
	}
	
	@GET
	@Path("/{cpf}/consulta")
	public Response consultaPorCpf(@PathParam("cpf") String cpf) {
		
		try {
			return Response.ok(service.consultaPorCPF(cpf)).build();
		} catch (NegocioException e) {
			throw new WebApplicationException(
					Response.status(Response.Status.BAD_REQUEST).entity(e.getMensagem().getDescricao()).build());
		}	
		
	}
	
	

}
