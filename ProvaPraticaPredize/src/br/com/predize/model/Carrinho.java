package br.com.predize.model;

public class Carrinho {

	private Integer Quantidade;
	
//	public Carrinho(String nome, Double preco, Integer quantidade) {
//		if(camposObrigatoriosPreenchidos(nome, preco, quantidade)) {
//			this.nome = nome;
//			this.preco = preco;
//			Quantidade = quantidade;
//		}else {
//			System.out.println("Campos obrigatorios não estão preenchidos. \nPor favor verificar(Nome, Preço e/ou quantidade).");
//		}
//	}
	
	public Integer getQuantidade() {
		return Quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		Quantidade = quantidade;
	}

	//Nome, Preco, Quantidade - Obrigatórios
	public boolean camposObrigatoriosPreenchidos(String nome, Double preco, Integer quantidade){
		try {
			if(!nome.isEmpty() && preco != null && quantidade != null){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
