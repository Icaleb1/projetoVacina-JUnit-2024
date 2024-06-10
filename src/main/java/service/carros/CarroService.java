package service.carros;

import java.util.List;

import exception.CarrosException;
import model.entity.Pessoa;
import model.entity.carros.Carro;
import model.entity.seletores.CarroSeletor;
import model.entity.seletores.PessoaSeletor;
import model.repository.carros.CarroRepository;

public class CarroService {
	
	CarroRepository carroRepository = new CarroRepository();
	
	public List<Carro> consultarComFiltros(CarroSeletor seletor) throws CarrosException {
		  if (!seletor.temFiltro()) {
	            throw new CarrosException("Pelo menos um filtro deve ser preenchido.");
	        }
		  verificarAnosPreenchidosEmPares(seletor);
		  verificarValoresPreenchidosEmPares(seletor);
		  verificarFiltros(seletor);
		return carroRepository.consultarComFiltro(seletor);
	}
	
	public void verificarAnosPreenchidosEmPares(CarroSeletor seletor) throws CarrosException {
	    boolean anoInicialPreenchido = seletor.getAnoInicial() != null && seletor.getAnoInicial() != 0;
	    boolean anoFinalPreenchido = seletor.getAnoFinal() != null && seletor.getAnoFinal() != 0;

	    if (anoInicialPreenchido && !anoFinalPreenchido) {
	        throw new CarrosException("AVISO: O ano inicial está preenchido, mas o ano final está vazio.");
	    } else if (!anoInicialPreenchido && anoFinalPreenchido) {
	        throw new CarrosException("AVISO: O ano final está preenchido, mas o ano inicial está vazio.");
	    }
	}
	
	public void verificarValoresPreenchidosEmPares(CarroSeletor seletor) throws CarrosException {
	    boolean valorInicialPreenchido = seletor.getValorInicial() != null && seletor.getValorInicial() != 0;
	    boolean valorFinalPreenchido = seletor.getValorFinal() != null && seletor.getValorFinal() != 0;

	    if (valorInicialPreenchido && !valorFinalPreenchido) {
	        throw new CarrosException("AVISO: O valor inicial está preenchido, mas o valor final está vazio.");
	    } else if (!valorInicialPreenchido && valorFinalPreenchido) {
	        throw new CarrosException("AVISO: O valor final está preenchido, mas o valor inicial está vazio.");
	    }
	}

	
	public boolean verificarFiltros(CarroSeletor seletor) {
	    return (seletor.getNomeMontadora() != null && !seletor.getNomeMontadora().trim().isEmpty()) ||
	           (seletor.getModelo() != null && !seletor.getModelo().trim().isEmpty()) ||
	           seletor.getAnoInicial() != null && seletor.getAnoInicial() != 0 ||
	           seletor.getAnoFinal() != null && seletor.getAnoFinal() != 0 ||
	           seletor.getValorInicial() != null && seletor.getValorInicial() != 0 ||
	           seletor.getValorFinal() != null && seletor.getValorInicial() != 0;
	}

	
	

}
