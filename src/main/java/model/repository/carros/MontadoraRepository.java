 package model.repository.carros;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.entity.Pais;
import model.entity.carros.Montadora;
import model.repository.Banco;

public class MontadoraRepository {
	
	public Montadora salvar(Montadora novaMontadora) {
		String query = "INSERT INTO concesionaria.montadora (nome,paisFundacao,nomePresidente,dataFundacao) VALUES (?,?,?,?)";
		Connection conexao = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conexao, query);
		
		try {
			pstmt.setString(1, novaMontadora.getNome());
			pstmt.setString(2, novaMontadora.getPaisFundacao());
			pstmt.setString(3, novaMontadora.getNomePresidente());
			pstmt.setDate(4, Date.valueOf(novaMontadora.getDataFundacao()));
			
			pstmt.execute();
			ResultSet resultado = pstmt.getGeneratedKeys();
			
			if (resultado.next()) {
				novaMontadora.setId(resultado.getInt(1));
				
			}
			
		} catch (SQLException erro) {
			System.out.println("Erro ao salvar a nova Montadora");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closePreparedStatement(pstmt);
			Banco.closeConnection(conexao);
		}
		
		
		return novaMontadora;
		
	}

	
	public Montadora consultarPorId(int id) {
		Connection conexao = Banco.getConnection();
		Statement stmt = Banco.getStatement(conexao);
		
		Montadora montadora = null;
		ResultSet resultado = null;
		String query = " SELECT * FROM concesionaria.montadora WHERE id = " + id;
		
		try{
			resultado = stmt.executeQuery(query);
			if(resultado.next()){
				montadora = new Montadora();
				montadora.setId(resultado.getInt("ID"));
				montadora.setNome(resultado.getString("NOME"));
				montadora.setNomePresidente(resultado.getString("NOMEPRESIDENTE"));
				montadora.setPaisFundacao(resultado.getString("PAISFUNDACAO"));
				montadora.setDataFundacao(resultado.getDate("DATAFUNDACAO").toLocalDate());
			}
		} catch (SQLException erro){
			System.out.println("Erro ao consultar montadora com o id: " + id);
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conexao);
		}
		return montadora;
	}

}
