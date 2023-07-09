package br.com.predize.aplicacao;

import br.com.predize.controller.ProdutoController;
import br.com.predize.model.Carrinho;
import br.com.predize.model.Produto;

public class Main {

	public static void main(String[] args) {

		ProdutoController prodController = new ProdutoController();
		Produto prod = new Produto("Sabonte", 6.00, 2);
		Carrinho carrinhoDeCompras = new Carrinho();
		
		//Salvar produto no banco
		//prodController.post(prod);
		
		//Editar produto no banco
		//prodController.put(5, prod);
		
		//Buscar produto no banco
		//prodController.get(4, prod);

		//Buscar todos os produtos no banco
		//prodController.getAll();
		
		//Excluir produto no banco
		//prodController.delete(4, prod);
		
		//Adicionar produto ao carrinho
		
		//Atualizar a quantidade do produto no carrinho
		
		//Remover um item do carrinho
		
		//Buscar todos os itens do carrinho
		
		//Finalizar a compra
		
		
	}
}
