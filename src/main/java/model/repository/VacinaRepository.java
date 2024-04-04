package model.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entity.Pais;
import model.entity.Pessoa;
import model.entity.Vacina;
import model.repository.Banco;
import model.repository.PaisRepository;
import model.repository.PessoaRepository;

public class VacinaRepository {


	public Vacina salvar(Vacina novaVacina) {
		String sql = " INSERT INTO vacina(id_pesquisador, id_pais_origem, nome, estagio_pesquisa, data_inicio) "
				   + " VALUES(?, ?, ?, ?, ?) ";
		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, sql);
		
		try {
			stmt.setInt(1, novaVacina.getPesquisadorResponsavel().getId());
			stmt.setInt(2, novaVacina.getPais().getId());
			stmt.setString(3, novaVacina.getNome());
			stmt.setInt(4, novaVacina.getEstagioPesquisa());
			stmt.setDate(5, Date.valueOf(novaVacina.getDataInicio()));
			
			stmt.execute();
			ResultSet resultado = stmt.getGeneratedKeys();
			if(resultado.next()) {
				novaVacina.setId(resultado.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao salvar nova vacina");
			System.out.println("Erro: " + e.getMessage());
		}
		
		return novaVacina;
	}

	public boolean excluir(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean excluiu = false;
		String query = "DELETE FROM vacina WHERE id = " + id;
		try {
			if(stmt.executeUpdate(query) == 1) {
				excluiu = true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao excluir vacina");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return excluiu;
	}

	public boolean alterar(Vacina vacinaEditada) {
		boolean alterou = false;
		String query = " UPDATE vacina "
				     + " SET id_pesquisador=?, id_pais_origem=?, nome=?, estagio_pesquisa=?, data_inicio=? "
				     + " WHERE id=? ";
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			stmt.setInt(1, vacinaEditada.getPesquisadorResponsavel().getId());
			stmt.setInt(2, vacinaEditada.getPais().getId());
			stmt.setString(3, vacinaEditada.getNome());
			stmt.setInt(4, vacinaEditada.getEstagioPesquisa());
			stmt.setDate(5, Date.valueOf(vacinaEditada.getDataInicio()));
			
			
			stmt.setInt(6, vacinaEditada.getId());
			alterou = stmt.executeUpdate() > 0;
		} catch (SQLException erro) {
			System.out.println("Erro ao atualizar vacina");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return alterou;
	}

	public Vacina consultarPorId(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		
		Vacina vacina = null;
		ResultSet resultado = null;
		String query = " SELECT * FROM vacina WHERE id = " + id;
		
		try{
			resultado = stmt.executeQuery(query);
			PessoaRepository pessoaRepository = new PessoaRepository();
			PaisRepository paisRepository = new PaisRepository();
			
			if(resultado.next()){
				vacina = new Vacina();
				vacina.setId(Integer.parseInt(resultado.getString("ID")));
				vacina.setNome(resultado.getString("NOME"));
				vacina.setEstagioPesquisa(resultado.getInt("ESTAGIO_PESQUISA"));
				vacina.setDataInicio(resultado.getDate("DATA_INICIO").toLocalDate()); 
				
				Pessoa pesquisador = pessoaRepository.consultarPorId(resultado.getInt("ID_PESQUISADOR"));
				vacina.setPesquisadorResponsavel(pesquisador);
				
				Pais pais = paisRepository.consultarPorId(resultado.getInt("ID_PAIS_ORIGEM"));
				vacina.setPais(pais);
			}
		} catch (SQLException erro){
			System.out.println("Erro ao consultar vacina com o id: " + id);
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return vacina;
	}

	public ArrayList<Vacina> consultarTodos() {
		ArrayList<Vacina> vacinas = new ArrayList<>();
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		
		ResultSet resultado = null;
		String query = " SELECT * FROM vacina";
		
		try{
			resultado = stmt.executeQuery(query);
			PessoaRepository pessoaRepository = new PessoaRepository();
			PaisRepository paisRepository = new PaisRepository();
			
			while(resultado.next()){
				Vacina vacina = new Vacina();
				vacina.setId(Integer.parseInt(resultado.getString("ID")));
				vacina.setNome(resultado.getString("NOME"));
				vacina.setEstagioPesquisa(resultado.getInt("ESTAGIO_PESQUISA"));
				vacina.setDataInicio(resultado.getDate("DATA_INICIO").toLocalDate()); 
				
				Pessoa pesquisador = pessoaRepository.consultarPorId(resultado.getInt("ID_PESQUISADOR"));
				vacina.setPesquisadorResponsavel(pesquisador);
				
				Pais pais = paisRepository.consultarPorId(resultado.getInt("ID_PAIS_ORIGEM"));
				vacina.setPais(pais);
				
				vacinas.add(vacina);
				
				
				
			}
		} catch (SQLException erro){
			System.out.println("Erro ao consultar todas as vacinas");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return vacinas;
	}
}
