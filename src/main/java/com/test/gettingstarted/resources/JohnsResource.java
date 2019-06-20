package com.test.gettingstarted.resources;

import com.test.gettingstarted.core.JohnsModel;
import com.test.gettingstarted.core.JohnsService;
import com.test.gettingstarted.views.JohnsViewModel;
import com.google.inject.Inject;
import ma.glasnost.orika.MapperFacade;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/john")
@Produces(MediaType.APPLICATION_JSON)
public class JohnsResource {

	private final JohnsService johnsService;
	private final MapperFacade mapper;

	@Inject
	public JohnsResource(JohnsService service, MapperFacade mapper) {
		this.johnsService = service;
		this.mapper = mapper;
	}

	@GET
	public Response sayHello() {
		JohnsModel model = johnsService.getInfo();
		JohnsViewModel vm = mapper.map(model, JohnsViewModel.class);
		return Response.ok(vm).build();
	}
}