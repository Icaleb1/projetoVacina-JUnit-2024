package service;

import java.util.List;

import exception.VacinacaoException;
import model.entity.Pessoa;
import model.repository.PessoaRepository;


public class PessoaService {

private PessoaRepository repository = new PessoaRepository();
	
	public Pessoa salvar(Pessoa novaPessoa) throws VacinacaoException {
		validarCamposObrigatorios(novaPessoa);
		
		validarCpf(novaPessoa);
		
		return repository.salvar(novaPessoa);
	}
	
	public boolean alterar(Pessoa pessoaEditada) throws VacinacaoException{
		validarCamposObrigatorios(pessoaEditada);
		
		return repository.alterar(pessoaEditada);
	}
	
	private void validarCamposObrigatorios(Pessoa pessoa) throws VacinacaoException {
		String mensagemValidacao = "";
	
		
		
		if (pessoa.getNome() == null || pessoa.getNome().isEmpty()) {
			mensagemValidacao += "Nome Obrigatório!";
		}
		
		if (pessoa.getDataNascimento() == null) {
			mensagemValidacao += "- informe a data de nascimento \n";
		}
		
		if (pessoa.getCpf() == null || pessoa.getCpf().isEmpty() || pessoa.getCpf().length() != 11) {
			mensagemValidacao += "- informe o CPF";
		}
		
		if (pessoa.getSexo() == ' ' ) {
			mensagemValidacao += "- informe o sexo";
		}
		
		if (pessoa.getTipoPessoa() < 1 || pessoa.getTipoPessoa() > 3) {
			mensagemValidacao += "- informe o tipo entre (1 e 3)";
		}
		
		if (pessoa.getPais() == null) {
			mensagemValidacao += "- informe o pais: ";
		}
		
		if (!mensagemValidacao.isEmpty()) {
			throw new VacinacaoException("Preencha os seguintes campos: " + mensagemValidacao);
			
		}
		
	}
	
	private void validarCpf(Pessoa novaPessoa) throws VacinacaoException{
		if (repository.cpfJaCadastrado(novaPessoa.getCpf())) {
			throw new VacinacaoException("CPF" + novaPessoa.getCpf() + " já cadastrado");
		}
	}
	
	public boolean excluir(int id) {
		return repository.excluir(id);
	}
	
	public List<Pessoa> consultarTodas(){
		return repository.consultarTodos();
	}
	
	
	public Pessoa consultarPorId(int id) {
		return repository.consultarPorId(id);
	}
	
	
}
