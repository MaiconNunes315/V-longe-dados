package TabelasDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import ClassesTabelas.Contato;
import ClassesTabelas.Destino;
import Conexao.Conexao;

public class ContatoDAO {
	private String sql;
	private Connection conexao;

	public void createContato(Contato contato) {
		sql = "INSERT INTO contato (email, data, " + "mensagem, telefone, nome) VALUES " + "(?, ?, ?,?, ?)";
		conexao = Conexao.conectar();
		try (PreparedStatement pstm = conexao.prepareStatement(sql)) {
			pstm.setString(1, contato.getEmail());
			pstm.setTimestamp(2, Timestamp.valueOf(contato.getData()));
			pstm.setString(3, contato.getMensagem());
			pstm.setString(4, contato.getTelefone());
			pstm.setString(5, contato.getNome());
			pstm.executeUpdate();

			System.out.println("Mensagem de " + contato.getNome() + ", Cadastrado com sucesso");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void readContato() {
		sql = "SELECT * FROM contato";
		conexao = Conexao.conectar();
		ResultSet rset = null;
		try (PreparedStatement pstm = conexao.prepareStatement(sql)) {

			rset = pstm.executeQuery();
			while (rset.next()) {

				Contato contato = new Contato();

				contato.setId(rset.getInt("id"));
				contato.setEmail(rset.getString("email"));
				contato.setMensagem(rset.getString("mensagem"));
				contato.setNome(rset.getString("nome"));
				contato.setTelefone(rset.getString("telefone"));
				contato.setData(rset.getTimestamp("data").toLocalDateTime());
				System.out.println(contato.toString());
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public void searchContato(int id) {
		conexao = Conexao.conectar();
		sql = "SELECT * FROM contato WHERE id = " + id;
		ResultSet rset = null;
		try (PreparedStatement pstm = conexao.prepareStatement(sql)) {
			rset = pstm.executeQuery();

			while (rset.next()) {
				Contato contato = new Contato();

				contato.setId(rset.getInt("id"));
				contato.setEmail(rset.getString("email"));
				contato.setMensagem(rset.getString("mensagem"));
				contato.setNome(rset.getString("nome"));
				contato.setTelefone(rset.getString("telefone"));
				contato.setData(rset.getTimestamp("data").toLocalDateTime());
				System.out.println(contato.toString());
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void updateContato(Contato contato, int id, String campo) {
		conexao = Conexao.conectar();
		sql = "UPDATE contato SET " + campo + " = ? WHERE id = " + id;

		try (PreparedStatement pstm = conexao.prepareStatement(sql)) {

			switch (campo) {
			case "email": {
				pstm.setString(1, contato.getEmail());
				pstm.executeUpdate();
				break;
			}
			case "mensagem": {

				pstm.setString(1, contato.getMensagem());
				pstm.executeUpdate();
				break;
			}
			case "data": {
				pstm.setTimestamp(1, Timestamp.valueOf(contato.getData()));
				pstm.executeUpdate();
				break;
			}
			case "telefone": {

				pstm.setString(1, contato.getTelefone());
				pstm.executeUpdate();
				break;
			}
			case "nome": {

				pstm.setString(1, contato.getNome());
				pstm.executeUpdate();
				break;
			}
		
			default: {
				System.out.println("Opção incorreta");
				break;
			}
			}

			System.out.println("Mensagem de  Atualizado com sucesso");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void deleteContato(int id) {
		conexao = Conexao.conectar();
		sql = "DELETE FROM contato WHERE id = ?";

		try (PreparedStatement pstm = conexao.prepareStatement(sql)) {

			pstm.setInt(1, id);
			pstm.execute();

			System.out.println("Mensagem deletado com sucesso");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}
}
