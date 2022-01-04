package entidades;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="USUARIO")
public class Usuario implements Serializable
{	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	private String login;
	
	private String email;
	
	private String senha;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estagiario_id")
	private Estagiario estagiario;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id")
	private Empresa empresa;
	
	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}
	
	public String getLogin()
	{
		return login;
	}
	
	public void setLogin(String login)
	{
		this.login = login;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public String getSenha()
	{
		return senha;
	}
	
	public void setSenha(String senha)
	{
		this.senha = senha;
	}

	public Estagiario getEstagiario()
	{
		return estagiario;
	}

	public void setEstagiario(Estagiario estagiario)
	{
		this.estagiario = estagiario;
	}

	public Empresa getEmpresa()
	{
		return empresa;
	}

	public void setEmpresa(Empresa empresa)
	{
		this.empresa = empresa;
	}
}
