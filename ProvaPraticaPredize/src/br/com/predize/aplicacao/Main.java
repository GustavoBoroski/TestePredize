package br.com.predize.aplicacao;

import br.com.predize.controller.ProdutoController;
import br.com.predize.model.Produto;

public class Main {

	public static void main(String[] args) {

		ProdutoController prodController = new ProdutoController();
		Produto prod = new Produto("Sabonte", 6.00, 2);
		
		//Salvar produto
		//prodController.post(prod);
		
		//Editar produto
		//prodController.put(5, prod);
		
		//Buscar produto
		//prodController.get(4, prod);

		//Buscar todos os produtos
		//prodController.getAll();
		
		//Excluir produto
		//prodController.delete(4, prod);
	}
}
