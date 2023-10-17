package ClassesTabelas;

import java.time.LocalDateTime;
import java.util.Date;

public class Contato {
	private int id;
	private String email,mensagem, telefone,nome;
	private LocalDateTime data;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	@Override
	public String toString() {
		
		Usuario usuario = new Usuario();

		return "_______________________________________________________\n" 
		+      "|Id: " + id + "\n" 
			 + "|Nome: " + nome+ "\n" 
		     + "|Telefone: " + telefone + "\n" 
			 + "|Email: " + email + "\n" 
		     + "|Mensagem: " + mensagem + "\n"
		     + "|Data de modificação: "+ usuario.formatarData(data) + "\n"
			 + "|_______________________________________________________";
		
	}
	
	
}
