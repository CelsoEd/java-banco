package la.foton.treinamento.ws;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import la.foton.treinamento.entity.Conta;
import la.foton.treinamento.service.ContaService;
import la.foton.treinamento.util.NegocioException;

@Path("/conta")
@Stateless
@Produces(MediaType.APPLICATION_JSON)
public class ContaWS {

	@Inject
	private ContaService service;

	@GET
	@Path("/hello")
	public Response hello() {
		return Response.ok().entity("API REST Conta").build();
	}

	@GET
	@Path("/{numero}")
	public Response consultaPorNumero(@PathParam("numero") int numero) {
		Conta conta = null;
		try {
			conta = service.consultaPorNumero(numero);
		} catch (NegocioException e) {
			throw new WebApplicationException(
					Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build());
		}
		return Response.ok().entity(conta).build();
	}

	@PUT
	@Path("/{numero}/{valor}")
	public Response sacaEmConta(@PathParam("numero") int numeroConta, @PathParam("valor") double valor)
			throws NegocioException {
		Conta conta = null;
		try {
			conta = service.sacaEmConta(numeroConta, valor);
		} catch (NegocioException e) {
			throw new WebApplicationException(
					Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build());
		}
		return Response.ok().entity(conta).build();
	}

	@PUT
	@Path("/{numero}:{valor}")
	public Response creditaEmConta(@PathParam("numero") int numeroConta, @PathParam("valor") double valor) {
		Conta conta = null;
		conta = service.depositaEmConta(numeroConta, valor);
		return Response.ok().entity(conta).build();
	}

	@PUT
	@Path("/{numeroOrigem}:{numeroDestino}/{valor}")
	public Response tranferenciaEntreContas(@PathParam("numeroOrigem") int numeroOrigem,
			@PathParam("numeroDestino") int numeroDestino, @PathParam("valor") double valor) throws NegocioException {
		Conta conta = null;
		try {
			service.transfereEntreContas(numeroOrigem, numeroDestino, valor);
			conta = service.consultaPorNumero(numeroOrigem);
		} catch (NegocioException e) {
			throw new WebApplicationException(
					Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build());
		}
		return Response.ok().entity(conta).build();
	}

}
