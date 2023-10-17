package TabelasDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.temporal.ChronoUnit;

import ClassesTabelas.Destino;
import ClassesTabelas.Hospedagem;
import ClassesTabelas.Usuario;
import ClassesTabelas.Viagem;
import Conexao.Conexao;

public class ViagemDAO {
	private String sql;
	private Connection conexao;

	public void createViagem(Viagem viagem) {
		sql = "INSERT INTO viagem (observacoes, desconto, "
				+ "dataEntrada, dataSaida,preco, id_destino, id_usuario, precoTotal, possuiHospedagem,id_hospedagem) VALUES "

				+ "(?, ?, ?,?, ?, ?,?,?,?,?)";
		conexao = Conexao.conectar();
		try (PreparedStatement pstm = conexao.prepareStatement(sql)) {
			pstm.setString(1, viagem.getObservacoes());
			pstm.setInt(2, viagem.getDesconto());
			pstm.setTimestamp(3, Timestamp.valueOf(viagem.getDataEntrada()));
			pstm.setTimestamp(4, Timestamp.valueOf(viagem.getDataSaida()));
			pstm.setDouble(5, viagem.getPreco());
			pstm.setInt(6, viagem.getDestino().getId_destino());
			pstm.setInt(7, viagem.getUsuario().getId());
			pstm.setDouble(8, viagem.calculaPrecoTotal());
			pstm.setInt(9, viagem.getPossuiHospedagem());
			pstm.setInt(10, viagem.getHospedagem().getId());
			
			pstm.executeUpdate();

			System.out.println("Viagem " + viagem.getDataEntrada() + " Cadastrado com sucesso");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void readViagem() {
		sql = "SELECT * FROM viagem A INNER JOIN usuario B ON A.id_usuario = B.id INNER JOIN destino C ON A.id_destino = C.id_destino";
		conexao = Conexao.conectar();
		ResultSet rset = null;
		try (PreparedStatement pstm = conexao.prepareStatement(sql)) {

			rset = pstm.executeQuery();
			while (rset.next()) {

				Viagem viagem = new Viagem();
				Destino destino = new Destino();
				Usuario usuario = new Usuario();
				Hospedagem hospedagem = new Hospedagem();

				viagem.setId_viagem(rset.getInt("id_viagem"));
				viagem.setObservacoes(rset.getString("observacoes"));
				viagem.setPreco(rset.getDouble("preco"));
				viagem.setDesconto(rset.getInt("desconto"));
				viagem.setDataEntrada(rset.getTimestamp("dataEntrada").toLocalDateTime());
				viagem.setDataSaida(rset.getTimestamp("dataSaida").toLocalDateTime());
				viagem.setPrecoTotal(rset.getDouble("precoTotal"));
				viagem.setPossuiHospedagem(rset.getInt("possuiHospedagem"));
				destino.setId_destino(rset.getInt("id_destino"));
				destino.setCidade(rset.getString("cidade"));
				destino.setDetalhes(rset.getString("detalhes"));
				destino.setEstado(rset.getString("estado"));
				destino.setImg(rset.getString("img"));
				destino.setPais(rset.getString("pais"));

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

				hospedagem.setId(rset.getInt("id_hospedagem"));
//				hospedagem.setEndereco(rset.getString("endereco"));
//				hospedagem.setNomeLocal(rset.getString("nomeLocal"));
//				hospedagem.setPrecoDiaria(rset.getDouble("precoDiaria"));
//				hospedagem.setDestino(destino);

				viagem.setDestino(destino);
				viagem.setUsuario(usuario);
				viagem.setHospedagem(hospedagem);

				System.out.println(viagem.toString());
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public Viagem searchViagem(int id) {
		sql = "SELECT * FROM viagem A INNER JOIN usuario B ON A.id_usuario = B.id INNER JOIN destino C ON A.id_destino = C.id_destino"
				+ " WHERE id_viagem = " + id;
		conexao = Conexao.conectar();
		ResultSet rset = null;
		Viagem viagem = new Viagem();
		try (PreparedStatement pstm = conexao.prepareStatement(sql)) {

			rset = pstm.executeQuery();
			while (rset.next()) {

				Destino destino = new Destino();
				Usuario usuario = new Usuario();
				Hospedagem hospedagem =  new Hospedagem();
				
				HospedagemDAO hospedagemDao = new HospedagemDAO();
				viagem.setId_viagem(rset.getInt("id_viagem"));
				viagem.setObservacoes(rset.getString("observacoes"));
				viagem.setPreco(rset.getDouble("preco"));
				viagem.setDesconto(rset.getInt("desconto"));
				viagem.setDataEntrada(rset.getTimestamp("dataEntrada").toLocalDateTime());
				viagem.setDataSaida(rset.getTimestamp("dataSaida").toLocalDateTime());
				viagem.setPrecoTotal(rset.getDouble("precoTotal"));
				viagem.setPossuiHospedagem(rset.getInt("possuiHospedagem"));
				int idHospedagem = (rset.getInt("id_hospedagem"));
				destino.setId_destino(rset.getInt("id_destino"));
				destino.setCidade(rset.getString("cidade"));
				destino.setDetalhes(rset.getString("detalhes"));
				destino.setEstado(rset.getString("estado"));
				destino.setImg(rset.getString("img"));
				destino.setPais(rset.getString("pais"));
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
				Hospedagem findHospedagem = hospedagemDao.searchHospedagem(idHospedagem);
				
				viagem.setDestino(destino);
				viagem.setUsuario(usuario);
				viagem.setHospedagem(findHospedagem);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return viagem;
	}
	
	
	public void updateViagem(Viagem viagem, int id, String campo) {
		conexao = Conexao.conectar();
		
		Viagem findViagem = this.searchViagem(id);
		Viagem novoCalculo = new Viagem();
		HospedagemDAO hospedagemDao = new HospedagemDAO();
		
		
		sql = "UPDATE viagem SET " + campo + " = ? , precoTotal = ? WHERE id_viagem = " + id;
		try (PreparedStatement pstm = conexao.prepareStatement(sql)) {

			switch (campo) {
			case "desconto": {
				novoCalculo.setDataEntrada(findViagem.getDataEntrada());
				novoCalculo.setDataSaida(findViagem.getDataSaida());
				novoCalculo.setPrecoDiaria(findViagem.getHospedagem().getPrecoDiaria());
				novoCalculo.setPreco(findViagem.getPreco());
				novoCalculo.setDesconto(viagem.getDesconto());
				novoCalculo.setPossuiHospedagem(findViagem.getPossuiHospedagem());
				System.out.println("só preco diaria " + novoCalculo.calculaPrecoTotal());
				
				pstm.setInt(1, viagem.getDesconto());
				pstm.setDouble(2, novoCalculo.calculaPrecoTotal());
				pstm.executeUpdate();
				break;
			}
			case "observacoes": {

				pstm.setString(1, viagem.getObservacoes());
				pstm.setDouble(2, findViagem.getPrecoTotal());
				pstm.executeUpdate();
				break;
			}
			case "preco": {
				novoCalculo.setDataEntrada(findViagem.getDataEntrada());
				novoCalculo.setDataSaida(findViagem.getDataSaida());
				novoCalculo.setPrecoDiaria(findViagem.getHospedagem().getPrecoDiaria());
				novoCalculo.setPreco(viagem.getPreco());
				novoCalculo.setDesconto(findViagem.getDesconto());
				novoCalculo.setPossuiHospedagem(findViagem.getPossuiHospedagem());
				
				pstm.setDouble(1, viagem.getPreco());
				pstm.setDouble(2, novoCalculo.calculaPrecoTotal());
				pstm.executeUpdate();
				break;
			}
			case "dataEntrada": {
				novoCalculo.setDataEntrada(viagem.getDataEntrada());
				novoCalculo.setDataSaida(findViagem.getDataSaida());
				novoCalculo.setPrecoDiaria(findViagem.getHospedagem().getPrecoDiaria());
				novoCalculo.setPreco(findViagem.getPreco());
				novoCalculo.setDesconto(findViagem.getDesconto());
				novoCalculo.setPossuiHospedagem(findViagem.getPossuiHospedagem());
				
				pstm.setTimestamp(1, Timestamp.valueOf(viagem.getDataEntrada()));
				pstm.setDouble(2, novoCalculo.calculaPrecoTotal());
				pstm.executeUpdate();
				break;
			}
			case "dataSaida": {
				novoCalculo.setDataEntrada(findViagem.getDataEntrada());
				novoCalculo.setDataSaida(viagem.getDataSaida());
				novoCalculo.setPrecoDiaria(findViagem.getHospedagem().getPrecoDiaria());
				novoCalculo.setPreco(findViagem.getPreco());
				novoCalculo.setDesconto(findViagem.getDesconto());
				novoCalculo.setPossuiHospedagem(findViagem.getPossuiHospedagem());
				
				pstm.setTimestamp(1, Timestamp.valueOf(viagem.getDataSaida()));
				pstm.setDouble(2, novoCalculo.calculaPrecoTotal());
				pstm.executeUpdate();
				break;
			}
			case "possuiHospedagem":{
				pstm.setInt(1, viagem.getPossuiHospedagem());
				pstm.setDouble(2, findViagem.getPrecoTotal());
				break;
			}

			default: {
				System.out.println("Opção incorreta");
				break;
			}
			}

			System.out.println("Viagem Atualizada com sucesso");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

//	public void updatePrecoTotal(Viagem id) {
//
//		Viagem viagemOB = this.searchViagemHospedagem(id.getId_viagem());
//		
//		if(id.getId_viagem() != 0) {
//			double precoDiaria = 0;
//			int qtDias = (int) viagemOB.getDataEntrada().until(viagemOB.getDataSaida(), ChronoUnit.DAYS);
//			double preco = viagemOB.getPreco();
//			if(viagemOB.getPossuiHospedagem() == 1) {
//				precoDiaria = viagemOB.getPrecoDiaria() * qtDias;
//			}
//			double desconto =(double) viagemOB.getDesconto()/100;
//			double precoTotal = (preco + precoDiaria) - (desconto * preco);
//			
//		conexao = Conexao.conectar();
//		sql = "UPDATE viagem SET precoTotal = ? WHERE id_viagem = " + id;
//		try (PreparedStatement pstm = conexao.prepareStatement(sql)) {		
//			pstm.setDouble(1, precoTotal);
//			pstm.executeUpdate();
//			System.out.println("Preço total atualizado");
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//		}
//
//	}

	public void deleteViagem(int id) {
		conexao = Conexao.conectar();
		sql = "DELETE FROM viagem WHERE id_viagem = ?";

		try (PreparedStatement pstm = conexao.prepareStatement(sql)) {

			pstm.setInt(1, id);
			pstm.execute();

			System.out.println("Viagem deletada com sucesso");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
