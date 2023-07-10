package br.com.predize.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Set;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;

public class CarrinhoController {

	public void adicionarCarrinho(Integer idProduto, String nome, Integer quantidade) {
		String sql = "INSERT INTO carrinho(idProduto, quantidade, nome) VALUES(?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstm = null;
		int tempQuantidade = buscarQuantidadeProduto(idProduto);

		if(quantidade <= tempQuantidade) {
			try {
				conn = Conexao.createConnectionToMySQL();
				pstm = (PreparedStatement) conn.prepareStatement(sql);
				pstm.setInt(1, idProduto);
				pstm.setInt(2, quantidade);
				pstm.setString(3, nome);
				pstm.execute();
				JOptionPane.showMessageDialog(null, "Produto adicionado ao carrinho com sucesso!", "Aviso", JOptionPane.YES_NO_CANCEL_OPTION);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
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
			JOptionPane.showMessageDialog(null, "Quantidade indisponivel em estoque!", "Aviso", JOptionPane.WARNING_MESSAGE);
		}
	}


	
	public void editarCarrinho(Integer idProduto, Integer quantidade) {
		
		String sql = "UPDATE carrinho SET quantidade = ? where idProduto = ?"; 
		
		Connection conn = null;
		PreparedStatement pstm = null;
		int tempQuantidade = buscarQuantidadeProduto(idProduto);
		
		if(quantidade <= tempQuantidade) {
			try {
				conn = Conexao.createConnectionToMySQL();
				pstm = (PreparedStatement) conn.prepareStatement(sql);
				pstm.setInt(1, quantidade);
				pstm.setInt(2, idProduto);
				pstm.execute();
				JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!", "Aviso", JOptionPane.YES_NO_CANCEL_OPTION);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
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
			JOptionPane.showMessageDialog(null, "Quantidade indisponivel em estoque!", "Aviso", JOptionPane.WARNING_MESSAGE);
		}
	}

	public void remove(Integer idProduto) {
		String sql = "DELETE from carrinho where idProduto = ?";		
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = Conexao.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setInt(1, idProduto);
			pstm.execute(); 
			JOptionPane.showMessageDialog(null, "Produto removido do carrinho com suscesso!", "Aviso", JOptionPane.YES_NO_CANCEL_OPTION);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
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
			conn = Conexao.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery(); 
			if(!rs.next()) {
				JOptionPane.showMessageDialog(null, "Não possui produtos adicionados!", "Aviso", JOptionPane.YES_NO_CANCEL_OPTION);
			}
			rs.beforeFirst();
			
			while(rs.next()) {
				System.err.println(rs.getString(1) + "|" + rs.getString(2) + "|" + rs.getString(3) + "|" + rs.getString(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
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
		//Dar baixa no sistema
		realizarBaixaQuantidade();
		//Limpar carrinho
		limparCarrinho();
	}

	private void realizarBaixaQuantidade() {
		HashMap<Integer, Integer> listaCarrinho = new HashMap<>();
		HashMap<Integer, Integer> listaProduto = new HashMap<>();
		
		String sqlCarrinho = "SELECT idProduto, quantidade FROM carrinho"; 
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = Conexao.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(sqlCarrinho);
			ResultSet rs = pstm.executeQuery(); 
			while(rs.next()) {
				listaCarrinho.put(rs.getInt(1), rs.getInt(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
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
		
		String sqlProduto = "SELECT id, quantidade FROM produtos"; 
		Connection conn2 = null;
		PreparedStatement pstm2 = null;
		try {
			conn2 = Conexao.createConnectionToMySQL();
			pstm2 = (PreparedStatement) conn2.prepareStatement(sqlProduto);
			ResultSet rs = pstm2.executeQuery(); 
			while(rs.next()) {
				listaProduto.put(rs.getInt(1), rs.getInt(2));
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
		
		Set<Integer> cart = listaCarrinho.keySet();
		Set<Integer> produtoList = listaProduto.keySet();
		Integer novaQuantidadeEstoque;
		for(Integer prodId : cart) {
			if(prodId != null) {
				for(Integer produto : produtoList) {
					if(prodId == produto) {
						realizaBaixa(listaCarrinho, listaProduto, prodId, produto);
					}
				}
			}
		}
	}

	private void realizaBaixa(HashMap<Integer, Integer> listaCarrinho, HashMap<Integer, Integer> listaProduto,
			Integer prodId, Integer produto) {
		Integer novaQuantidadeEstoque;
		novaQuantidadeEstoque = listaProduto.get(produto) - listaCarrinho.get(prodId);
		
		String sqlModificacaoQuantidade = "UPDATE produtos SET quantidade = ? where id = ?"; 
		Connection conn3 = null;
		PreparedStatement pstm3 = null;
		try {
			conn3 = Conexao.createConnectionToMySQL();
			pstm3 = (PreparedStatement) conn3.prepareStatement(sqlModificacaoQuantidade);
			pstm3.setInt(1, novaQuantidadeEstoque);
			pstm3.setInt(2, prodId);
			pstm3.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstm3 != null) {
					pstm3.close();
				}
				if(conn3 != null) {
					conn3.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	private void limparCarrinho() {
		String sqlLimpaCarrinho = "DELETE FROM carrinho"; 
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = Conexao.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(sqlLimpaCarrinho);
			pstm.execute(); 
			JOptionPane.showMessageDialog(null, "Compra finalizada!", "Aviso", JOptionPane.YES_NO_CANCEL_OPTION);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
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
