package br.com.predize.aplicacao;

import br.com.predize.controller.CarrinhoController;
import br.com.predize.controller.ProdutoController;
import br.com.predize.model.Carrinho;
import br.com.predize.model.Produto;

public class Main {

	public static void main(String[] args) {

		ProdutoController prodController = new ProdutoController();
		CarrinhoController cartController = new CarrinhoController();
		Produto prod = new Produto("Café", 20.99, 2);
		Carrinho carrinhoDeCompras = new Carrinho();
		
		//Salvar produto no banco
		//prodController.post(prod);
		
		//Editar produto no banco
		//prodController.put(8, prod);
		
		//Buscar produto no banco
		//prodController.get(8, prod);

		//Buscar todos os produtos no banco
		//prodController.getAll();
		
		//Excluir produto no banco
		//prodController.delete(1, "Feijão");
		
		//----------------------------------------------//
		
		//Adicionar produto ao carrinho
		//cartController.adicionarCarrinho(8, "Café", 2);
		
		//Atualizar a quantidade do produto no carrinho
		//cartController.editarCarrinho(8, 1);
		
		//Remover um item do carrinho
		//cartController.remove(5);
		
		//Buscar todos os itens do carrinho
		//cartController.BuscarTodos();
		
		//Finalizar a compra
		//cartController.finalizaCompra();
		
	}
}
