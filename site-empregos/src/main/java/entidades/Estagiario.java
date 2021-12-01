package entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ESTAGIARIO")
public class Estagiario extends Usuario
{
	private String nomeCompleto;
	
	private String cpf;
	
	private Date dataNascimento;
	
	@OneToOne(fetch = FetchType.LAZY)
    @MapsId
	private Curriculo curriculo;
	
	@OneToMany(mappedBy = "estagiario",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Aplicacao> aplicacoes;
	
	public String getNomeCompleto()
	{
		return nomeCompleto;
	}
	
	public void setNomeCompleto(String nomeCompleto)
	{
		this.nomeCompleto = nomeCompleto;
	}
	
	public String getCpf()
	{
		return cpf;
	}
	
	public void setCpf(String cpf)
	{
		this.cpf = cpf;
	}
	
	public Date getDataNascimento()
	{
		return dataNascimento;
	}
	
	public void setDataNascimento(Date dataNascimento)
	{
		this.dataNascimento = dataNascimento;
	}
}
