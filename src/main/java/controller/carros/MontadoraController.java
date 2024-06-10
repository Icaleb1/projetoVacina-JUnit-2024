package controller.carros;

import exception.CarrosException;
import exception.VacinacaoException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.entity.Pessoa;
import model.entity.carros.Montadora;
import service.carros.MontadoraService;

@Path("/montadora")
public class MontadoraController {
	
	MontadoraService montadoraService = new MontadoraService();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Montadora salvar(Montadora novaMontadora) throws CarrosException {
		return montadoraService.salvar(novaMontadora);
	}

}
