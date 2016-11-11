package br.com.gerenciador.enumeration;


public enum Fechamento {
	
	F10(10, "10%"),
	F30(30,"30%"),
	F60(60,"60%"),
	F90(90,"90%"),
	F100(100,"100%");

	private String label;
	private int percentual;

	private Fechamento(int percentual, String label) {
		this.label = label;
		this.percentual = percentual;
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getPercentual() {
		return percentual;
	}
	
	public void setPercentual(int percentual) {
		this.percentual = percentual;
	}
}
