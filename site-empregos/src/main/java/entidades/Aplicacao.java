package entidades;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="APLICACAO")
public class Aplicacao
{
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Estagiario estagiario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Vaga vaga;
	
	@OneToOne(mappedBy = "aplicacao", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Curriculo curriculo;
	
	private String status;
	
	public Long getId()
	{
		return id;
	}
	
	public void setId(Long id)
	{
		this.id = id;
	}
	
	public Estagiario getEstagiario()
	{
		return estagiario;
	}
	
	public void setEstagiario(Estagiario estagiario)
	{
		this.estagiario = estagiario;
	}
	
	public Vaga getVaga()
	{
		return vaga;
	}
	
	public void setVaga(Vaga vaga)
	{
		this.vaga = vaga;
	}
	
	public Curriculo getCurriculo()
	{
		return curriculo;
	}
	
	public void setCurriculo(Curriculo curriculo)
	{
		this.curriculo = curriculo;
	}
	
	public String getStatus()
	{
		return status;
	}
	
	public void setStatus(String status)
	{
		this.status = status;
	}
}
