package entidades;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="VAGA")
public class Vaga
{
	private Long id;
	
	private Empresa empresa;
	
	private String descricao;
	
	private int numeroRestante;
	
	@OneToMany(mappedBy = "vaga",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Aplicacao> aplicacoes;
	
	public Long getId()
	{
		return id;
	}
	
	public void setId(Long id)
	{
		this.id = id;
	}
	
	public Empresa getEmpresa()
	{
		return empresa;
	}
	
	public void setEmpresa(Empresa empresa)
	{
		this.empresa = empresa;
	}
	
	public String getDescricao()
	{
		return descricao;
	}
	
	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}
	
	public int getNumeroRestante()
	{
		return numeroRestante;
	}
	
	public void setNumeroRestante(int numeroRestante)
	{
		this.numeroRestante = numeroRestante;
	}
}
