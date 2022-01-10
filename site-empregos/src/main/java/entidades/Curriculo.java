package entidades;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="CURRICULO")
public class Curriculo implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	private byte [] arquivo;
	
	@OneToOne(fetch = FetchType.EAGER)
	private Estagiario estagiario;
	
	public Long getId()
	{
		return id;
	}
	
	public void setId(Long id)
	{
		this.id = id;
	}
	
	public byte[] getArquivo()
	{
		return arquivo;
	}
	
	public void setArquivo(byte[] arquivo)
	{
		this.arquivo = arquivo;
	}

	public Estagiario getEstagiario()
	{
		return estagiario;
	}

	public void setEstagiario(Estagiario estagiario)
	{
		this.estagiario = estagiario;
	}
}
