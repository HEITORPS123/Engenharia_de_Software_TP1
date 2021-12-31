package entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="VAGA")
public class Vaga implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
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
