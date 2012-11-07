package br.com.zapeat.mobile.model;

import java.io.Serializable;

import br.com.topsys.util.TSUtil;

@SuppressWarnings("serial")
public class UsuarioModel implements Serializable {

	private Long id;

	private String nome;

	private String login;

	private String senha;

	private String email;

	private String confirmaSenha;

	private Boolean flagAtivo;

	private String imagem;

	private String token;

	private Boolean flagFacebook;

	private Boolean flagAceitouTermo;
	
	public UsuarioModel() {

	}

	public UsuarioModel(String token) {
		this.token = token;
	}

	public UsuarioModel(String email, String senha) {
		this.email = email;
		this.senha = senha;
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
	
	public String getNomeExibicao() {
		
		if(TSUtil.isEmpty(nome)) {
			return null;
		}
		
		if(nome.indexOf(" ")<=0) {
			return nome;
		}
		
		return nome.substring(0,nome.indexOf(" "));
		
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Boolean getFlagAtivo() {
		return flagAtivo;
	}

	public void setFlagAtivo(Boolean flagAtivo) {
		this.flagAtivo = flagAtivo;
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Boolean getFlagFacebook() {
		return flagFacebook;
	}

	public void setFlagFacebook(Boolean flagFacebook) {
		this.flagFacebook = flagFacebook;
	}

	public Boolean getFlagAceitouTermo() {
		return flagAceitouTermo;
	}

	public void setFlagAceitouTermo(Boolean flagAceitouTermo) {
		this.flagAceitouTermo = flagAceitouTermo;
	}

}
