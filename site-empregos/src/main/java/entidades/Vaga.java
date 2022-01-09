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
	
	private String nome;
	
	@OneToOne(fetch = FetchType.EAGER)
	private Empresa empresa;
	
	private String descricao;
	
	private String local;
	
	private Integer cargaHoraria;
	
	private String posicao;
	
	private double salario;
	
	private Integer numeroRestante;
	
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

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
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

	public String getLocal()
	{
		return local;
	}

	public void setLocal(String local)
	{
		this.local = local;
	}

	public Integer getCargaHoraria()
	{
		return cargaHoraria;
	}

	public void setCargaHoraria(Integer cargaHoraria)
	{
		this.cargaHoraria = cargaHoraria;
	}

	public String getPosicao()
	{
		return posicao;
	}

	public void setPosicao(String posicao)
	{
		this.posicao = posicao;
	}

	public double getSalario()
	{
		return salario;
	}

	public void setSalario(double salario)
	{
		this.salario = salario;
	}

	public Integer getNumeroRestante()
	{
		return numeroRestante;
	}

	public void setNumeroRestante(Integer numeroRestante)
	{
		this.numeroRestante = numeroRestante;
	}

	public List<Aplicacao> getAplicacoes()
	{
		return aplicacoes;
	}

	public void setAplicacoes(List<Aplicacao> aplicacoes)
	{
		this.aplicacoes = aplicacoes;
	}
	
}
