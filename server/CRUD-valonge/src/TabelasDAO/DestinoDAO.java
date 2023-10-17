package TabelasDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.mysql.cj.protocol.Resultset;

import ClassesTabelas.Destino;
import ClassesTabelas.Usuario;
import Conexao.Conexao;

public class DestinoDAO {
	private String sql;
	private Connection conexao;
	
	public void createDestino(Destino destino) {
		sql = "INSERT INTO destino (detalhes, estado, "
				+ "pais, img, cidade) VALUES "
				+ "(?, ?, ?,?, ?)";
		conexao = Conexao.conectar();
		try(PreparedStatement pstm = conexao.prepareStatement(sql)){
			pstm.setString(1, destino.getDetalhes());
			pstm.setString(2, destino.getEstado());
			pstm.setString(3, destino.getPais());
			pstm.setString(4, destino.getImg());
			pstm.setString(5, destino.getCidade());
			pstm.executeUpdate();
			
			System.out.println("Destino " + destino.getCidade() + " Cadastrado com sucesso");
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void readDestino() {
		sql = "SELECT * FROM destino";
		conexao = Conexao.conectar();
		ResultSet rset = null;
		try(PreparedStatement pstm = conexao.prepareStatement(sql)){
			
			rset = pstm.executeQuery();
			while(rset.next()) {
				
				Destino destino = new Destino();
				
				destino.setId_destino(rset.getInt("id_destino"));
				destino.setCidade(rset.getString("cidade"));
				destino.setDetalhes(rset.getString("detalhes"));
				destino.setEstado(rset.getString("estado"));
				destino.setImg(rset.getString("img"));
				destino.setPais(rset.getString("pais"));
				
				System.out.println(destino.toString());
			}		
		}catch (SQLException e) {
		System.out.println(e.getMessage());
		}
			
	}
	
	public Destino searchDestino(int id) {
		conexao = Conexao.conectar();
		sql = "SELECT * FROM destino WHERE id_destino = " + id;
		ResultSet rset = null;
		Destino destino = new Destino();
		try (PreparedStatement pstm = conexao.prepareStatement(sql)) {
			rset = pstm.executeQuery();

			while (rset.next()) {
				destino.setId_destino(rset.getInt("id_destino"));
				destino.setCidade(rset.getString("cidade"));
				destino.setDetalhes(rset.getString("detalhes"));
				destino.setEstado(rset.getString("estado"));
				destino.setImg(rset.getString("img"));
				destino.setPais(rset.getString("pais"));

				
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return destino;
	}
	
	public void updateDestino(Destino destino, int id, String campo) {
		conexao = Conexao.conectar();
		sql = "UPDATE destino SET "+ campo + " = ? WHERE id_destino = " + id;

		try (PreparedStatement pstm = conexao.prepareStatement(sql)) {

				switch (campo) {
				case "cidade": {
					pstm.setString(1, destino.getCidade());
					pstm.executeUpdate();
					break;
				}
				case "detalhes": {
					pstm.setString(1, destino.getDetalhes());
					pstm.executeUpdate();
					break;
				}
				case "estado": {
					
					pstm.setString(1, destino.getEstado());
					pstm.executeUpdate();
					break;
				}
				case "img": {
					
					pstm.setString(1, destino.getImg());
					pstm.executeUpdate();
					break;
				}
				case "pais": {
					
					pstm.setString(1, destino.getPais());
					pstm.executeUpdate();
					break;
				}
				
				default:{
					System.out.println("Opção incorreta");
					break;
				}
				}
				
				System.out.println("Destino Atualizado com sucesso");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void deleteDestino(int id) {
		conexao = Conexao.conectar();
		sql = "DELETE FROM destino WHERE id_destino = ?";

		try (PreparedStatement pstm = conexao.prepareStatement(sql)) {

			pstm.setInt(1, id);
			pstm.execute();
			
			System.out.println("Destino deletado com sucesso");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}
}
