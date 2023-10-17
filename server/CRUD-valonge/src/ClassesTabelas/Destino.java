package ClassesTabelas;

public class Destino {
	private int id_destino;
	private String detalhes, estado, img, pais, cidade;

	public int getId_destino() {
		return id_destino;
	}
	public void setId_destino(int id_destino) {
		this.id_destino = id_destino;
	}
	public String getDetalhes() {
		return detalhes;
	}
	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	@Override
	public String toString() {
		return "_______________________________________________________\n" 
        +      "|Id: " + id_destino + "\n" 
			 + "|Link da imagem: " + img+ "\n" 
		     + "|Estado: " + estado + "\n" 
		     + "|Cidade: " + cidade + "\n"
			 + "|País: " + pais + "\n" 
		     + "|Detalhes: " + detalhes+ "\n" 
			 + "|_______________________________________________________";
		
	}
	
	
}
