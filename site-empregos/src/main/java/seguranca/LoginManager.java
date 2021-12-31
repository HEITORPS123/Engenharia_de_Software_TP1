package seguranca;

import java.io.Serializable;

import entidades.Usuario;

public class LoginManager implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String login;
	private String password;
	
	private Usuario usuario;
	
	public void login() {
		
	}
	
	public String getLogin()
	{
		return login;
	}
	
	public void setLogin(String login)
	{
		this.login = login;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}

	public boolean isLogado()
	{
		/*
		if(usuario == null){
			return false;
		}else{
			return true;
		}
		*/
		return true;
	}
}
