package br.com.predize.controller;

import java.sql.Connection;
import java.sql.ResultSet;

import com.mysql.jdbc.PreparedStatement;

import br.com.predize.model.Produto;

public class CarrinhoController {

	public void adicionarCarrinho(Integer idProduto, Produto produto, Integer quantidade) {
		String sql = "INSERT INTO carrinho(idProduto, quantidade, nome) VALUES(?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstm = null;
		int tempQuantidade = buscarQuantidadeProduto(idProduto);

		if(quantidade <= tempQuantidade) {
			try {
				//Criar uma conexão com o banco de dados
				conn = Conexao.createConnectionToMySQL();
				
				//Criamos uma PreparedStatement para executar uma query
				pstm = (PreparedStatement) conn.prepareStatement(sql);
				
				//Adicionar os valores que são esperados pela query
				pstm.setInt(1, idProduto);
				pstm.setInt(2, quantidade);
				pstm.setString(3, produto.getNome());
				
				//Executar a query
				pstm.execute();
				System.err.println("Produto adicionado ao carrinho com sucesso!");
				
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
		}else {
			System.out.println("Quantidade indisponivel em estoque!");
		}
	}


	
	public void editarCarrinho(Integer idProduto, Integer quantidade) {
		
		String sql = "UPDATE carrinho SET quantidade = ? where idProduto = ?"; 
		
		Connection conn = null;
		PreparedStatement pstm = null;
		int tempQuantidade = buscarQuantidadeProduto(idProduto);
		
		if(quantidade <= tempQuantidade) {
			try {
				//Criar uma conexão com o banco de dados
				conn = Conexao.createConnectionToMySQL();
				
				//Criamos uma PreparedStatement para executar uma query
				pstm = (PreparedStatement) conn.prepareStatement(sql);
				
				//Adicionar os valores que são esperados pela query
				pstm.setInt(1, quantidade);
				pstm.setInt(2, idProduto);
				
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
		}else {
			System.out.println("Quantidade indisponivel em estoque!");
		}
	}

	public void remove(Integer idProduto) {
		String sql = "DELETE from carrinho where idProduto = ?";		
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			//Criar uma conexão com o banco de dados
			conn = Conexao.createConnectionToMySQL();
			
			//Criamos uma PreparedStatement para executar uma query
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			//Adicionar os valores que são esperados pela query
			pstm.setInt(1, idProduto);
			
			//Executar a query
			pstm.execute(); 
			System.err.println("Produto removido do carrinho com suscesso!");	
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

	public void BuscarTodos() {
		String sql = "SELECT * FROM carrinho"; 
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
	
	public void finalizaCompra() {
		
		String sqlLimpaCarrinho = "DELETE FROM carrinho"; 
		
		
		
		
		
		
		
	}
	
	private int buscarQuantidadeProduto(Integer idProduto) {
		Connection conn2 = null;
		PreparedStatement pstm2 = null;
		int tempQuantidade = 0;
		
		try {
			conn2 = Conexao.createConnectionToMySQL();
			pstm2 = (PreparedStatement) conn2.prepareStatement("SELECT quantidade FROM produtos where id = ?");
			pstm2.setInt(1, idProduto);
			
			ResultSet rs = pstm2.executeQuery(); 
			if(rs.next()) {
				tempQuantidade = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstm2 != null) {
					pstm2.close();
				}
				if(conn2 != null) {
					conn2.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return tempQuantidade;
	}
}
