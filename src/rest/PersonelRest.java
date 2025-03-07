package rest;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;


import dto.Personel;
import service.ServiceFacade;


@Path("/personel")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PersonelRest {

	@GET
	@PermitAll
	public Personel getUserDetails(@Context HttpServletRequest request) throws Exception {
//		String userId = request.getUserPrincipal().getName();
		Personel authenticatedPersonelDetails = (Personel) request.getSession().getAttribute("LOGIN_USER");
		authenticatedPersonelDetails.getEmail();
		return authenticatedPersonelDetails;
	}

	@Path("/addPersonel")
	@POST
	@RolesAllowed("admin")
	public void addPersonel(Personel personel) throws Exception {
		ServiceFacade.getInstance().addPersonel(personel);
	}
	
	@Path("/getPersonel")
	@POST
	@RolesAllowed({"admin","HR"})
	public Personel getPersonel(long sicilNo) throws Exception {
		return ServiceFacade.getInstance().getPersonel(sicilNo);
	}
	
	@Path("/getAllPersonel")
	@GET
	@RolesAllowed({"admin","HR"})
	public List<Personel> getPersonel() throws Exception {
		return ServiceFacade.getInstance().getAllPersonel();
	}


	@Path("/deletePersonel")
	@POST
	@RolesAllowed("admin")
	public void deletePersonel(long sicilNo) throws Exception {
		ServiceFacade.getInstance().deletePersonel(sicilNo);
	}

	@Path("/updatePersonel")
	@POST
	@RolesAllowed("admin")
	public void updatePersonel(Personel personel) throws Exception {
		ServiceFacade.getInstance().updatePersonel(personel);
	}
}