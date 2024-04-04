package paisService;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exception.VacinacaoException;
import model.entity.Pais;
import service.PaisService;

class PaisServiceTest {

	/*
	 * @Test void testSalvarCamposNulos() { Pais novoPais = new Pais(); Pais
	 * novoPais1 = new Pais(0, "Brasil", "BR"); Pais novoPais2 = new Pais(1, "",
	 * "BR"); Pais novoPais3 = new Pais(1, "Brasil", ""); PaisService paisService =
	 * new PaisService();
	 * 
	 * assertFalse(novoPais1.getId() == 0); assertFalse(novoPais2.getNome() == "" ||
	 * novoPais.getNome().isEmpty()); assertFalse(novoPais3.getSigla() == "" ||
	 * novoPais.getSigla().isEmpty()); assertNotNull(novoPais); }
	 */
	
	@Test
    public void salvarPaisComNomeNuloDeveLancarExcecao() throws VacinacaoException {
        Pais pais = new Pais(1, "", "BR");
        PaisService paisService = new PaisService();

        try {
            paisService.salvar(pais);
            fail("Exceção esperada não foi lançada");
        } catch (VacinacaoException e) {
            assertEquals("Preencha os seguintes campos: Nome obrigatório!", e.getMessage());
        }
    }

	
	
	@Test
    public void salvarPaisComSiglaNulaDeveLancarExcecao() throws VacinacaoException {
        Pais pais = new Pais(1, "Brasil", "");
        PaisService paisService = new PaisService();

        try {
            paisService.salvar(pais);
            fail("Exceção esperada não foi lançada");
        } catch (VacinacaoException e) {
            assertEquals("Preencha os seguintes campos: Sigla obrigatória!", e.getMessage());
        }
    }




	
	








}
