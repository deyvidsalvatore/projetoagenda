package projetoagenda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContatoJdbc {
	
	private static Connection CONNECTION;
	
	public ContatoJdbc() throws Exception {
		CONNECTION = ConexaoBanco.criarConexaoMySQL();
	}
	
	/* Listar todos os contatos do banco */
	public void getAllContatos() throws SQLException {
		List<Contato> contatos = new ArrayList<Contato>();
		String sql = "SELECT * FROM contato";
		
		try(
			PreparedStatement pstm = CONNECTION.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
		) {
			while(rs.next()) {
				Contato contato = new Contato();
				contato.setId(rs.getInt("id"));
				contato.setNome(rs.getString("nome"));
				contato.setNumero(rs.getInt("numero"));
				contatos.add(contato);
			}
			System.out.println("-=- Lista de Contato -=-");
			contatos.forEach(contato -> System.out.printf("ID: %d | Nome: %s | Numero: %d\n",
					contato.getId(), contato.getNome(), contato.getNumero()
			));
		}
	}
	
	/* Salvar novo contato */
	public void saveContato(Contato contato) throws SQLException {
		String sql = "INSERT INTO contato(nome, numero) VALUES (?, ?)";
		
		try (PreparedStatement pstm = CONNECTION.prepareStatement(sql)) {
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getNumero());
			pstm.executeUpdate();
			System.out.println("Novo contato adicionado com sucesso");
		}
	}
	
	/* Atualizar contato existente */
	public void updateContato(Contato contato) throws SQLException {
        String sql = "UPDATE contato SET nome = ?, numero = ? WHERE id = ?";

        try (PreparedStatement pstm = CONNECTION.prepareStatement(sql)) {
            pstm.setString(1, contato.getNome());
            pstm.setInt(2, contato.getNumero());
            pstm.setInt(3, contato.getId());
            pstm.executeUpdate();
            System.out.println("O contato com o id: " + contato.getId() 
            						+ " foi atualizado com sucesso!");
        }
    }
	
	/* Apagar contato existente pelo ID */
	public void deleteContatoById(int id) throws SQLException {
        String sql = "DELETE FROM contato WHERE id = ?";

        try (PreparedStatement pstm = CONNECTION.prepareStatement(sql)) {
            pstm.setInt(1, id);
            pstm.executeUpdate();
            System.out.println("Contato apagado com sucesso!");
        }
    }
}
