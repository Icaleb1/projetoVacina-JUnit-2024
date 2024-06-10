package service.carros;

import exception.CarrosException;
import exception.VacinacaoException;
import model.entity.Pais;
import model.entity.carros.Montadora;
import model.repository.PaisRepository;
import model.repository.carros.MontadoraRepository;

public class MontadoraService {
	
private MontadoraRepository repository = new MontadoraRepository();
	
	
	public Montadora salvar(Montadora novaMontadora) throws CarrosException {
		validarCamposObrigatorios(novaMontadora);
		
		return repository.salvar(novaMontadora);	
		
	}


	private void validarCamposObrigatorios(Montadora montadoraValidada)  throws CarrosException {
		String mensagemValidacao = "";
		
		if(montadoraValidada.getNome() == null || montadoraValidada.getNome().isEmpty()) {
			mensagemValidacao = "Nome obrigatório!";
		}
		if(montadoraValidada.getPaisFundacao() == null || montadoraValidada.getPaisFundacao().isEmpty()) {
			mensagemValidacao = "pais obrigatório!";
		}
		if(montadoraValidada.getNomePresidente() == null || montadoraValidada.getNomePresidente().isEmpty()) {
			mensagemValidacao = "presidente obrigatório!";
		}
		if(montadoraValidada.getDataFundacao() == null) {
			mensagemValidacao = "data de fundação obrigatória!";
		}
		
		if (!mensagemValidacao.isEmpty()) {
			throw new CarrosException("Preencha os seguintes campos: " + mensagemValidacao);
			
		}
		
		
	}

}
