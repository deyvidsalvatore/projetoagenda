package projetoagenda;

public class Programa {
	public static void main(String[] args) throws Exception {
		ContatoJdbc contatoJdbc = new ContatoJdbc();
		
		System.out.println("Antes");
		contatoJdbc.getAllContatos();
		System.out.println();
		
		/* Apagando um contato existente */
		contatoJdbc.deleteContatoById(7);
		
		System.out.println("Depois");
		contatoJdbc.getAllContatos();
	}

}
