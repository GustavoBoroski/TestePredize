package br.com.predize.controller;

import java.sql.Connection;
import java.sql.ResultSet;

import com.mysql.jdbc.PreparedStatement;
import br.com.predize.model.Produto;

public class ProdutoController {
	
	public void post(Produto produto) {
		String sql = "INSERT INTO produtos(nome, preco, quantidade) VALUES(?, ?, ?)";		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			//Criar uma conexão com o banco de dados
			conn = Conexao.createConnectionToMySQL();
			
			//Criamos uma PreparedStatement para executar uma query
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			//Adicionar os valores que são esperados pela query
			pstm.setString(1, produto.getNome());
			pstm.setDouble(2, produto.getPreco());
			pstm.setInt(3, produto.getQuantidade());
			
			//Executar a query
			pstm.execute();
			System.err.println("Produto salvo com sucesso!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//Fechar as conexões
			try {
				if(pstm != null) {
					pstm.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void put(Integer id, Produto produto) {
		
		String sql = "UPDATE produtos SET nome = ?, preco = ?, quantidade = ? where id = ?"; 
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			//Criar uma conexão com o banco de dados
			conn = Conexao.createConnectionToMySQL();
			
			//Criamos uma PreparedStatement para executar uma query
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			//Adicionar os valores que são esperados pela query
			pstm.setString(1, produto.getNome());
			pstm.setDouble(2, produto.getPreco());
			pstm.setInt(3, produto.getQuantidade());
			pstm.setInt(4, id);
			
			//Executar a query
			pstm.execute();
			System.err.println("Produto atualizado com sucesso!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//Fechar as conexões
			try {
				if(pstm != null) {
					pstm.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public void delete(Integer id, Produto produto) {
	
		String sql = "DELETE from produtos where id = ? and nome = ?";		
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			//Criar uma conexão com o banco de dados
			conn = Conexao.createConnectionToMySQL();
			
			//Criamos uma PreparedStatement para executar uma query
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			//Adicionar os valores que são esperados pela query
			pstm.setInt(1, id);
			pstm.setString(2, produto.getNome());
			
			//Executar a query
			pstm.execute(); 
			System.err.println("Produto removido com suscesso!");	
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//Fechar as conexões
			try {
				if(pstm != null) {
					pstm.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public void get(Integer id, Produto produto) {
		String sql = "SELECT * FROM produtos where id = ? and nome = ?"; 
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			//Criar uma conexão com o banco de dados
			conn = Conexao.createConnectionToMySQL();
			
			//Criamos uma PreparedStatement para executar uma query
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			//Adicionar os valores que são esperados pela query
			pstm.setInt(1, id);
			pstm.setString(2, produto.getNome());
			
			//Executar a query
			ResultSet rs = pstm.executeQuery(); 
			if(!rs.next()) {
				System.err.println("ID ou nome do produto incorretos, por favor tente novamente!");
			}
			rs.beforeFirst();
			
			while(rs.next()) {
				System.err.println(rs.getString(1) + "|" + rs.getString(2) + "|" + rs.getString(3) + "|" + rs.getString(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//Fechar as conexões
			try {
				if(pstm != null) {
					pstm.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void getAll() {
		String sql = "SELECT * FROM produtos"; 
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			//Criar uma conexão com o banco de dados
			conn = Conexao.createConnectionToMySQL();
			
			//Criamos uma PreparedStatement para executar uma query
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			//Executar a query
			ResultSet rs = pstm.executeQuery(); 
			if(!rs.next()) {
				System.err.println("Não possui produtos adicionados!");
			}
			rs.beforeFirst();
			
			while(rs.next()) {
				System.err.println(rs.getString(1) + "|" + rs.getString(2) + "|" + rs.getString(3) + "|" + rs.getString(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//Fechar as conexões
			try {
				if(pstm != null) {
					pstm.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
