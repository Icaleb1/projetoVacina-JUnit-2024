package pessoaService;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;


import exception.VacinacaoException;
import jakarta.annotation.security.RunAs;
import model.entity.Pais;
import model.entity.Pessoa;
import service.PaisService;
import service.PessoaService;


class PessoaServiceTest {

	
	@Test
    public void salvarPessoaComNomeNuloDeveLancarExcecao() throws VacinacaoException {
	   Pais pais = new Pais(1, "Brasil", "BR");
	   LocalDate data = LocalDate.of(2024, 12, 8);
       Pessoa pessoa = new Pessoa(1, "Caleb", data, 'M', "99999999999", 1, pais);
       PessoaService pessoaService = new PessoaService();

        try {
            pessoaService.salvar(pessoa);
            fail("Exceção esperada não foi lançada");
        } catch (VacinacaoException e) {
            assertEquals("Preencha os seguintes campos: Nome obrigatório!", e.getMessage());
        }
    }
	



	
	
}
