package br.com.quantumfinance.selecaoEstagio.dto;

import java.util.Date;

/**
 * @author Quantum Finance
 */
public class Cotacao 
{
	private String nome;
	private Date data;
	private float fechamento;
	private Long volume;
        private float retorno;
        
        public Cotacao(String nome,Date data,Float fech,Long vol){
            this.nome = nome;
            this.data = data;
            this.fechamento = fech;
            this.volume= vol;
        }

	public String getNome() 
	{
		return nome;
	}
	public void setNome(String nome) 
	{
		this.nome = nome;
	}
	
	public Date getData() 
	{
		return data;
	}
	public void setData(Date data) 
	{
		this.data = data;
	}
	
	public Float getFechamento() 
	{
		return fechamento;
	}
	public void setFechamento(Float fechamento) 
	{
		this.fechamento = fechamento;
	}
	
	public Long getVolume() 
	{
		return volume;
	}
	public void setVolume(Long volume) 
	{
		this.volume = volume;
	}
        
        public void setRetorno(float ret){
            this.retorno = ret;
        }
        
        public float getRetorno(){
            return this.retorno;
        }

}
