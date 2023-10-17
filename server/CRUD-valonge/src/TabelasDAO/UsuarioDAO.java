package TabelasDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import ClassesTabelas.Usuario;
import Conexao.Conexao;

public class UsuarioDAO {

	private String sql;
	private Connection conexao;

	// Criando Usuário para o banco de dados
	public void creatUsuario(Usuario usuario) {
		conexao = Conexao.conectar();
		sql = "INSERT INTO usuario (nome, rg, endereco, cpf, estado, "
				+ "dataNascimento, telefone, criado_em, modificado_em, senha, "
				+ "email, tipoUsuario) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

		try (PreparedStatement pstm = conexao.prepareStatement(sql)) {

			pstm.setString(1, usuario.getNome());
			pstm.setString(2, usuario.getRg());
			pstm.setString(3, usuario.getEndereco());
			pstm.setString(4, usuario.getCpf());
			pstm.setString(5, usuario.getEstado());
			pstm.setTimestamp(6, Timestamp.valueOf(usuario.getDataNascimento().atStartOfDay()));
			pstm.setString(7, usuario.getTelefone());
			pstm.setTimestamp(8, Timestamp.valueOf(usuario.getCriado_em()));
			pstm.setTimestamp(9, Timestamp.valueOf(usuario.getModificado_em()));
			pstm.setString(10, usuario.getSenha());
			pstm.setString(11, usuario.getEmail());
			pstm.setString(12, usuario.getTipoUsuario());

			pstm.executeUpdate();
			System.out.println("usuário " + usuario.getNome() + " cadastrado com  sucesso");
		} catch (SQLException e) {
			System.out.println("Error " + e.getMessage());
		}
	}

	// Listando usuarios do banco de dados
	public void readUsuarios() {
		conexao = Conexao.conectar();
		sql = "SELECT * FROM usuario";
		ResultSet rset = null;
		try (PreparedStatement pstm = conexao.prepareStatement(sql)) {
			rset = pstm.executeQuery();

			while (rset.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rset.getInt("id"));
				usuario.setNome(rset.getString("nome"));
				usuario.setRg(rset.getString("rg"));
				usuario.setEndereco(rset.getString("endereco"));
				usuario.setCpf(rset.getString("cpf"));
				usuario.setEstado(rset.getString("estado"));
				usuario.setDataNascimento(rset.getTimestamp("dataNascimento").toLocalDateTime().toLocalDate());
				usuario.setTelefone(rset.getString("telefone"));
				usuario.setCriado_em(rset.getTimestamp("criado_em").toLocalDateTime());
				usuario.setModificado_em(rset.getTimestamp("modificado_em").toLocalDateTime());
				usuario.setSenha(rset.getString("senha"));
				usuario.setEmail(rset.getString("email"));
				usuario.setTipoUsuario(rset.getString("tipoUsuario"));

				System.out.println(usuario.toString());
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	// buscar usuario através do id
	public Usuario searchUsuarios(int id) {
		conexao = Conexao.conectar();
		sql = "SELECT * FROM usuario WHERE id = " + id;
		ResultSet rset = null;
		Usuario usuario = new Usuario();
		try (PreparedStatement pstm = conexao.prepareStatement(sql)) {
			rset = pstm.executeQuery();

			while (rset.next()) {
				usuario.setId(rset.getInt("id"));
				usuario.setNome(rset.getString("nome"));
				usuario.setRg(rset.getString("rg"));
				usuario.setEndereco(rset.getString("endereco"));
				usuario.setCpf(rset.getString("cpf"));
				usuario.setEstado(rset.getString("estado"));
				usuario.setDataNascimento(rset.getTimestamp("dataNascimento").toLocalDateTime().toLocalDate());
				usuario.setTelefone(rset.getString("telefone"));
				usuario.setCriado_em(rset.getTimestamp("criado_em").toLocalDateTime());
				usuario.setModificado_em(rset.getTimestamp("modificado_em").toLocalDateTime());
				usuario.setSenha(rset.getString("senha"));
				usuario.setEmail(rset.getString("email"));
				usuario.setTipoUsuario(rset.getString("tipoUsuario"));

			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return usuario;
	}

	// atualizar um campo do usuario
	public void updateUsuario(Usuario usuario, int id, String campo) {
		conexao = Conexao.conectar();
		sql = "UPDATE usuario SET " + campo + " = ? , modificado_em = now() WHERE id = " + id;

		try (PreparedStatement pstm = conexao.prepareStatement(sql)) {

			switch (campo) {
			case "nome": {
				pstm.setString(1, usuario.getNome());
				pstm.executeUpdate();
				break;
			}
			case "rg": {

				pstm.setString(1, usuario.getRg());
				pstm.executeUpdate();
				break;
			}
			case "endereco": {
				pstm.setString(1, usuario.getEndereco());
				pstm.executeUpdate();
				break;
			}
			case "cpf": {

				pstm.setString(1, usuario.getCpf());
				pstm.executeUpdate();
				break;
			}
			case "estado": {

				pstm.setString(1, usuario.getEstado());
				pstm.executeUpdate();
				break;
			}
			case "dataNascimento": {

				pstm.setTimestamp(1, Timestamp.valueOf(usuario.getDataNascimento().atStartOfDay()));
				pstm.executeUpdate();
				break;
			}
			case "telefone": {

				pstm.setString(1, usuario.getTelefone());
				pstm.executeUpdate();
				break;
			}
			case "email": {

				pstm.setString(1, usuario.getEmail());
				pstm.executeUpdate();
				break;
			}
			case "senha": {

				pstm.setString(1, usuario.getSenha());
				pstm.executeUpdate();
				break;
			}
			case "tipoUsuario": {

				pstm.setString(1, usuario.getTipoUsuario());
				pstm.executeUpdate();
				break;
			}
			default: {
				System.out.println("Opção incorreta");
				break;
			}
			}
			System.out.println("Usuário atualizado com sucesso");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	// deletar um usuario
	public void deleteUsuario(int id) {
		conexao = Conexao.conectar();
		sql = "DELETE FROM usuario WHERE id = ?";

		try (PreparedStatement pstm = conexao.prepareStatement(sql)) {

			pstm.setInt(1, id);
			pstm.execute();

			System.out.println("Usuário deletado com sucesso");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

}
