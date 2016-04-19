package br.com.constran.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.constran.dao.LogAuditoriaDAO;
import br.com.constran.model.LogCollectionWrapper;
import br.com.constran.util.AbstractController;

import com.google.gson.Gson;


@Path("/logs")
public class GravadorLog extends AbstractController{
	
	@POST
	@Path("/gravar")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response gravarLog(String logJson){
		
		Response response = null;
		LogCollectionWrapper logCollection = null;
		
		Gson gJson = new Gson();
		
		try {
		
			logCollection =  gJson.fromJson(logJson, LogCollectionWrapper.class);
			
			abreConexaoBD();
			
			LogAuditoriaDAO logDAO = new LogAuditoriaDAO(conn);
			
			logDAO.cadastrarLog(logCollection);
	
			response = Response.status(Response.Status.OK).build();
		}
		catch (Exception e) {
			
			e.printStackTrace();
			response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
			return response;
		}
		finally{
			
			fechaConexaoBD();
		}
	
		return response;
	}
}
