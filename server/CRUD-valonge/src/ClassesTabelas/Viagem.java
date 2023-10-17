package ClassesTabelas;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import TabelasDAO.HospedagemDAO;

public class Viagem {
	private int id_viagem, desconto, possuiHospedagem;
	private String observacoes;
	private LocalDateTime dataEntrada, dataSaida;
	private double preco, precoDiaria,precoTotal;
	private Destino destino;
	private Usuario usuario;
	private Hospedagem hospedagem;
	
	public int getPossuiHospedagem() {
		return possuiHospedagem;
	}
	public void setPossuiHospedagem(int possuiHospedagem) {
		this.possuiHospedagem = possuiHospedagem;
	}
	
	public Hospedagem getHospedagem() {
		return hospedagem;
	}
	public void setHospedagem(Hospedagem hospedagem) {
		this.hospedagem = hospedagem;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public double getPrecoDiaria() {
		return precoDiaria;
	}
	public void setPrecoDiaria(double precoDiaria) {
		this.precoDiaria = precoDiaria;
	}
	public int getId_viagem() {
		return id_viagem;
	}
	public void setId_viagem(int id_viagem) {
		this.id_viagem = id_viagem;
	}
	public int getDesconto() {
		return desconto;
	}
	public void setDesconto(int desconto) {
		this.desconto = desconto;
	}
	public String getObservacoes() {
		return observacoes;
	}
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	public LocalDateTime getDataEntrada() {
		return dataEntrada;
	}
	public void setDataEntrada(LocalDateTime dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	public LocalDateTime getDataSaida() {
		return dataSaida;
	}
	public void setDataSaida(LocalDateTime dataSaida) {
		this.dataSaida = dataSaida;
	}
	public Destino getDestino() {
		return destino;
	}
	public void setDestino(Destino destino) {
		this.destino = destino;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
public double getPrecoTotal() {
		return precoTotal;
	}
	public void setPrecoTotal(double precoTotal) {
		this.precoTotal = precoTotal;
	}
	
public double calculaPrecoTotal() {
		
		double novoDesconto =(double) this.desconto/100;
		
		if(possuiHospedagem == 1) {		
			int qtDias = (int) this.dataEntrada.until(this.dataSaida, ChronoUnit.DAYS);	
			double precoHospedagem = qtDias * this.precoDiaria;	
		if(this.desconto == 0) {
			return precoHospedagem + this.preco;
		}else {
			double valorDesconto = (precoHospedagem + this.preco) * novoDesconto;	
			return  precoHospedagem + this.preco - valorDesconto ;
		}
		
		}else {
			if(this.desconto == 0) {
				return this.preco;
			}else {
				double valorDesconto = this.preco * novoDesconto;
				return  this.preco - valorDesconto ;
			}		
		}
	}

public double subTotal() {
	double novoDesconto =(double) this.desconto/100;
	return preco - (novoDesconto * preco);
}

	
	@Override
	public String toString() {
		
		HospedagemDAO hosp = new HospedagemDAO();
		String local = hosp.searchHospedagem(hospedagem.getId()).getNomeLocal();
		
		
		return "_______________________________________________________\n" 
		+      "|Id: " + id_viagem + "\n" 
			 + "|Nome do cliente: " + usuario.getNome()+ "\n" 
		     + "|Saindo de: " + usuario.getEstado() + "\n" 
			 + "|Para: " + destino.getCidade() + "\n" 
		     + "|Data de embarque: " + usuario.formatarData(dataEntrada) + "\n" 
			 + "|Data de desembarque: " + usuario.formatarData(dataSaida)+ "\n"
			 + "|Quantidade de dias: " + this.dataEntrada.until(this.dataSaida, ChronoUnit.DAYS) + "\n"
		     + "|Desconto: " + (desconto == 0 ?"Não possui desconto" : desconto +"%")+"\n" 
		     + "|Possui hospedagem: " + (possuiHospedagem == 1? " Sim \n"
		     + "|Hospedagem: " + local : " Não") +"\n"
			 + "|Sub total: " + NumberFormat.getCurrencyInstance().format(subTotal()) + "\n" 
		     + "|Total: " + NumberFormat.getCurrencyInstance().format(precoTotal) + "\n"
			 + "|Observações: " + observacoes + "\n" 
			 + "|_______________________________________________________";
	}
	
	
	
}
