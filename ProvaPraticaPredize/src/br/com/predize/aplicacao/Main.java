package br.com.predize.aplicacao;

import br.com.predize.model.Produto;

public class Main {

	public static void main(String[] args) {

		Produto pt1 = new Produto("Cerveja", 48.94, 5);
		Produto pt2 = new Produto("Cerveja", 48.94, 5);
		Produto pt3 = new Produto("Cerveja", 48.94, 5);
		Produto pt4 = new Produto("Cerveja", 48.94, 5);
		
		
		System.out.println(pt1.getId());
		System.out.println(pt2.getId());
		System.out.println(pt3.getId());
		System.out.println(pt4.getId());
		
		
		
	}

}
