package ClassesTabelas;

public class Hospedagem {
	private int id;
	private double precoDiaria;
	private String endereco, nomeLocal;
	private Destino destino;
	
	public double getPrecoDiaria() {
		return precoDiaria;
	}

	public void setPrecoDiaria(double precoDiaria) {
		this.precoDiaria = precoDiaria;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNomeLocal() {
		return nomeLocal;
	}

	public void setNomeLocal(String nomeLocal) {
		this.nomeLocal = nomeLocal;
	}

	public Destino getDestino() {
		return destino;
	}

	public void setDestino(Destino destino) {
		this.destino = destino;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		
		return "_______________________________________________________\n" 
		+      "|Id: " + id + "\n" 
			 + "|Nome do local: " + nomeLocal+ "\n" 
		     + "|País: " + destino.getPais() + "\n" 
			 + "|Estado: " + destino.getEstado() + "\n" 
		     + "|Cidade: " +destino.getCidade() + "\n"  
		     + "|Endereço: " + endereco + "\n" 
			 + "|Preço da diária: " + precoDiaria + "\n" 
			 + "|_______________________________________________________";
	}

	
	
}
