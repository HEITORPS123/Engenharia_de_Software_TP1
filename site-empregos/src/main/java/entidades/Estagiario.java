package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ESTAGIARIO")
public class Estagiario implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	private String nomeCompleto;
	
	private String cpf;
	
	private Date dataNascimento;
	
	private String local;
	
	@OneToOne(mappedBy = "estagiario", cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="estagiario_id")
	private Usuario usuario;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "curriculo_id")
	private Curriculo curriculo;
	
	@OneToMany(mappedBy = "estagiario")
	private List<Aplicacao> aplicacoes = new ArrayList<Aplicacao>();
	
	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}
	
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

	public Usuario getUsuario()
	{
		return usuario;
	}

	public void setUsuario(Usuario usuario)
	{
		this.usuario = usuario;
	}

	public Curriculo getCurriculo()
	{
		return curriculo;
	}

	public void setCurriculo(Curriculo curriculo)
	{
		this.curriculo = curriculo;
	}

	public List<Aplicacao> getAplicacoes()
	{
		return aplicacoes;
	}

	public void setAplicacoes(List<Aplicacao> aplicacoes)
	{
		this.aplicacoes = aplicacoes;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}
}
