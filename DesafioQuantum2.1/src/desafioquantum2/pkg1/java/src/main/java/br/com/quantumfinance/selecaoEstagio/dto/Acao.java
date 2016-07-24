package br.com.quantumfinance.selecaoEstagio.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Quantum Finance
 */
public class Acao 
{
	private String nome;
	private List<Cotacao> cotacoes;

	public Acao(String nome) {
		this.nome = nome;
		cotacoes = new ArrayList();
	}

	public String getNome()
	{
		return nome;
	}
	public void setNome(String nome) 
	{
		this.nome = nome;
	}
	
	public List<Cotacao> getCotacoes()
	{
		return cotacoes;
	}

	public void adicionaCotacao(Cotacao cotacao){
		cotacoes.add(cotacao);
	}
}
