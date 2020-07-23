package com.example.rest.servicios;

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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.example.rest.dao.PedidoModel;
import com.example.rest.dao.UbigeoModel;
import com.example.rest.entidades.Pedido;
import com.example.rest.entidades.Ubigeo;




@Path("/servicios")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class ServicioRest {
	private static final Log log = LogFactory.getLog(ServicioRest.class);
	
	private PedidoModel daoPe = new PedidoModel();
	private UbigeoModel daoUbi= new UbigeoModel();

	
	
	@GET
	@Path("/pedido")
	public Response listarProveedorTodos() {
		log.info("listarTodos pedido rest ");
		return Response.ok(daoPe.listaPedido()).build();
	}
	@GET
	@Path("/pedido/{idPedido}")
	public Response listarPedidoporcliente(@PathParam("idPedido") int id) {
		log.info("listarporCliente rest ");
		return Response.ok(daoPe.listaPedidoporCliente(id)).build();
	}
	@POST
	@Path("/pedido")
	public Response registroPedido(Pedido obj) {
		log.info("rest registroPedido ");
		return Response.ok(daoPe.inserta(obj)).build();
	}
	
	@GET
	@Path("/departamentos")
	public Response listarDepartamentos() {
		log.info("listar departamentos");
		return Response.ok(daoUbi.listarDepartamentos()).build();
	}

	@GET
	@Path("/provincias/{depa}")
	public Response listarProvincias(@PathParam("depa") String idDepa) {
		log.info("listar provincias");
		Ubigeo obj = new Ubigeo();
		obj.setDepartamento(idDepa);
		return Response.ok(daoUbi.listarProvincia(obj)).build();
	}

	@GET
	@Path("/distritos/{depa}/{pro}")
	public Response listarDistritos(@PathParam("depa") String idDepa, @PathParam("pro") String idPro) {
		log.info("listar Distritos");
		Ubigeo obj = new Ubigeo();
		obj.setDepartamento(idDepa);
		obj.setProvincia(idPro);
		return Response.ok(daoUbi.listarDistrito(obj)).build();
	}

	

	

}