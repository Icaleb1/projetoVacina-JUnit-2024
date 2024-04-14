package service;

import java.time.LocalDate;
import java.util.ArrayList;

import exception.VacinacaoException;
import model.entity.Pessoa;
import model.entity.Vacina;
import model.entity.Vacinacao;
import model.repository.VacinaRepository;
import model.repository.VacinacaoRepository;

public class VacinacaoService {
	private static final int NOTA_MAXIMA=5;
	private VacinacaoRepository vacinacaoRepository = new VacinacaoRepository();
	private VacinaRepository vacinaRepository = new VacinaRepository();
	
	public Vacinacao salvar(Vacinacao novaVacinacao) throws VacinacaoException{
		
		if(novaVacinacao.getIdPessoa() == 0 
				|| novaVacinacao.getVacina() == null
				|| novaVacinacao.getVacina().getId() == 0) {
			throw new VacinacaoException("Informe a o id da pessoa e a vacina da aplicação");
		}
		
		novaVacinacao.setDataAplicacao(LocalDate.now());
		
		if(novaVacinacao.getAvaliacao() == 0) {
			novaVacinacao.setAvaliacao(NOTA_MAXIMA);
		}
		
		recalcularMediaVacina(novaVacinacao);
		
		return  vacinacaoRepository.salvar(novaVacinacao);
		
	}
	
	public boolean alterar(Vacinacao vacinacao) {
		
		recalcularMediaVacina(vacinacao);
		
		return vacinacaoRepository.alterar(vacinacao);
	}
	
	public boolean excluir(int id) {
		return vacinacaoRepository.excluir(id);
	}
	
	public Vacinacao consultarPorId(int id) {
		return vacinacaoRepository.consultarPorId(id);
	}

	public ArrayList<Vacinacao> consultarTodos(){
		return vacinacaoRepository.consultarTodos();
	}
	
	private void recalcularMediaVacina(Vacinacao vacinacao) {
		ArrayList<Vacinacao> aplicacoesDaVacina = vacinacaoRepository.consultarPorIdVacina(vacinacao.getVacina().getId());

		int somatorio = 0;
		double novaMedia = 0;
		
		for (Vacinacao dose: aplicacoesDaVacina) {
			somatorio += dose.getAvaliacao();
		}

		novaMedia = (somatorio + vacinacao.getAvaliacao()) / (aplicacoesDaVacina.size() + 1);
		
		vacinacao.getVacina().setMedia(novaMedia);
		vacinaRepository.alterar(vacinacao.getVacina());
	}
	
	
	public void receberVacina(Vacinacao vacinacao) {
		Pessoa pessoa = new Pessoa();
		
		
		Vacina vacina = new Vacina();
		String mensagemValidacao = "";

		switch(vacina.getEstagioPesquisa()) {
		case 1: 
			if(pessoa.getTipoPessoa() != 1) {
				mensagemValidacao = " Estágio inicial somente para pesquisadores";
			};
		break;
		case 2:
			if(pessoa.getTipoPessoa() != 1 || pessoa.getTipoPessoa() != 2) {
				mensagemValidacao = "Estágio de teste somente para pesquisadores ou voluntários";
			}
		break;
		}
	}
	
}
