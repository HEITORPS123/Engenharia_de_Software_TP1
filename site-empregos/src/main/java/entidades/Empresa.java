package entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="EMPRESA")
public class Empresa implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	private String nomeComercial;
	
	private String descricao;
	
	private String setor;
	
	private String cnpj;
	
	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}
	
	public String getNomeComercial()
	{
		return nomeComercial;
	}
	
	public void setNomeComercial(String nomeComercial)
	{
		this.nomeComercial = nomeComercial;
	}
	
	public String getDescricao()
	{
		return descricao;
	}
	
	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}
	
	public String getSetor()
	{
		return setor;
	}
	
	public void setSetor(String setor)
	{
		this.setor = setor;
	}
	
	public String getCnpj()
	{
		return cnpj;
	}
	
	public void setCnpj(String cnpj)
	{
		this.cnpj = cnpj;
	}

}
