package pessoaService;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import exception.VacinacaoException;
import jakarta.annotation.security.RunAs;
import model.entity.Pais;
import model.entity.Pessoa;
import service.PaisService;
import service.PessoaService;

class PessoaServiceTest {
	PessoaService pessoaService = new PessoaService();
	private Pessoa pessoa;
	
	//A TAG "@BeforeEach" EXECUTARÁ O MÉTODO ANTES DE CADA TESTE
	@BeforeEach
	//MÉTODO PARA CRIAR UM OBJETO DE PESSOA
	private void criandoPessoaParaTeste() {
		Pais pais = new Pais(1, "Brasil", "BR");
		LocalDate data = LocalDate.of(2024, 12, 8);
	    pessoa = new Pessoa(1, null, data, 'M', "99999999999", 1, pais);
	}
	
	//A TAG "@Test" INDENTIFICA QUAIS MÉTODO SERÃO TESTADOS
	@Test
	//TESTE AUTOEXPLICATIVO QUE VÁLIDA UMA REGRA DE NEGÓCIO DO MÉTODO "SalvarNovaPessoa"
	//PESSOA COM NOME NULO OU VAZIO DEVE LANÇAR EXCESSÃO PARA QUE O TESTE SEJA VALIDADO
    public void salvarPessoaComNomeNuloDeveLancarExcecao() throws VacinacaoException {
        try {
            pessoaService.salvar(pessoa);
            fail("Exceção esperada não foi lançada");
        } catch (VacinacaoException e) {
        	//O MÉTODO "assertEquals" VERIFICA SE A EXCESSÃO CAPTURADA É A EXCESSÃO ESPERADA
        	// CASO SEJA O TESTE PASSOU, SENÃO O TESTE FALHA
            assertNotEquals("Preencha os seguintes campos: Nome Obrigatório!", e.getMessage());
            //MÉTODO ALTERADO PARA TESTE FALHAR 
        }
    }
}