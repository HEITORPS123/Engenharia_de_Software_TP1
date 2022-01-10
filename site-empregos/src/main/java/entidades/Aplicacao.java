package entidades;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="APLICACAO")
public class Aplicacao implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Estagiario estagiario;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Vaga vaga;
	
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
	
	public String getStatus()
	{
		return status;
	}
	
	public void setStatus(String status)
	{
		this.status = status;
	}
}
