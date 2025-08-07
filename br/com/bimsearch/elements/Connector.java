package br.com.bimsearch.elements;

public class Connector {

	private String imagePath;
	private int numeroConexoes;
	private boolean argola;
	private boolean cabo;
	private boolean conduite;
	private boolean caboConduite;
	private boolean equipamento;
	private boolean aereo;
	private int tamanho;
	private String nameId;

	public Connector(String imagePath, int numeroConexoes, boolean argola, boolean cabo, boolean conduite, boolean caboConduite, boolean equipamento, boolean aereo, int tamanho, String nameId) {
		this.imagePath = imagePath;
		this.numeroConexoes = numeroConexoes;
		this.argola = argola;
		this.cabo = cabo;
		this.conduite = conduite;
		this.caboConduite = caboConduite;
		this.equipamento = equipamento;
		this.aereo = aereo;
		this.tamanho = tamanho;
		this.nameId = nameId;
	}

	public String getImagePath() {
		return imagePath;
	}

	public int getNumeroConexoes() {
		return numeroConexoes;
	}

	public boolean isArgola() {
		return argola;
	}

	public boolean isCabo() {
		return cabo;
	}

	public boolean isConduite() {
		return conduite;
	}

	public boolean isCaboConduite() {
		return caboConduite;
	}

	public boolean isEquipamento() {
		return equipamento;
	}

	public boolean isAereo() {
		return aereo;
	}

	public int getTamanho() {
		return tamanho;
	}

	public String getNameId(){
		return nameId;
	}

}

