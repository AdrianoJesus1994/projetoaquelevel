package com.aqualevel.model;

public class Reservatorio{

	private Long id;
	
	private String nome;
	
	private TipoReservatorio tipo;

	private Usuario usuario;

	private Float altura;

	private Float largura;

	private Float raio;

	private Float raioMenor;
	
	private Float profundidade;
	
	public Reservatorio() {
		super();
		this.altura = 0F;
		this.largura = 0F;
		this.raio = 0F;
		this.raioMenor = 0F;
		this.profundidade = 0F;
		this.usuario = new Usuario();
		this.tipo = new TipoReservatorio();
	}



	public Reservatorio(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoReservatorio getTipo() {
		return tipo;
	}

	public void setTipo(TipoReservatorio tipo) {
		this.tipo = tipo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Float getAltura() {
		return altura;
	}

	public void setAltura(Float altura) {
		this.altura = altura;
	}

	public Float getLargura() {
		return largura;
	}

	public void setLargura(Float largura) {
		this.largura = largura;
	}

	public Float getRaio() {
		return raio;
	}

	public void setRaio(Float raio) {
		this.raio = raio;
	}

	public Float getRaioMenor() {
		return raioMenor;
	}

	public void setRaioMenor(Float raioMenor) {
		this.raioMenor = raioMenor;
	}

	public Float getProfundidade() {
		return profundidade;
	}

	public void setProfundidade(Float profundidade) {
		this.profundidade = profundidade;
	}

	@Override
	public String toString() {
		return "Reservatorio [id=" + id + ", nome=" + nome + ", tipo=" + tipo + ", usuario=" + usuario + ", altura="
				+ altura + ", largura=" + largura + ", raio=" + raio + ", raioMenor=" + raioMenor + ", profundidade="
				+ profundidade + "]";
	}
	
	
}
