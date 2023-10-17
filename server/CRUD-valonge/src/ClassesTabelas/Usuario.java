package ClassesTabelas;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Usuario {
	private int id;
	private String telefone, email, nome, rg, cpf, endereco, estado, senha, tipoUsuario;
	private LocalDateTime criado_em, modificado_em;
	private LocalDate dataNascimento;

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public LocalDateTime getCriado_em() {
		return criado_em;
	}

	public void setCriado_em(LocalDateTime criado_em) {
		this.criado_em = criado_em;
	}

	public LocalDateTime getModificado_em() {
		return modificado_em;
	}

	public void setModificado_em(LocalDateTime modificado_em) {
		this.modificado_em = modificado_em;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String formatarData(LocalDate data) {
		DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return data.format(formatoData);
	}

	public String formatarData(LocalDateTime data) {
		DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		return data.format(formatoData);
	}

	@Override
	public String toString() {

		return "_______________________________________________________\n" 
		+      "|Id: " + id + "\n" 
			 + "|Nome: " + nome+ "\n" 
		     + "|Telefone: " + telefone + "\n" 
			 + "|Email: " + email + "\n" 
		     + "|RG: " + rg + "\n" 
			 + "|CPF: " + cpf+ "\n" 
		     + "|Endereço: " + endereco + "\n" 
			 + "|Estado: " + estado + "\n" 
		     + "|Senha: " + senha + "\n"
			 + "|Tipo de Usuário: " + tipoUsuario + "\n" 
		     + "|Data de nascimento: " + formatarData(dataNascimento)+ "\n" 
			 + "|Data de criação: " + formatarData(criado_em) + "\n" 
		     + "|Data de modificação: "+ formatarData(modificado_em) + "\n"
			 + "|_______________________________________________________";

	}

}
