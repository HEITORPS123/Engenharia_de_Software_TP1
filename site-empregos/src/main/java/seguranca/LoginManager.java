package seguranca;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import entidades.Usuario;
import util.managers.UsuarioUtils;

@ManagedBean
@SessionScoped
public class LoginManager implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioUtils usuarioUtils;
	
	private String login;
	private String senha;
	
	private Usuario usuario;
	
	public String login() {
		usuario = usuarioUtils.searchEntityByName(login);
        FacesContext context = FacesContext.getCurrentInstance();
        
		if(!usuario.getSenha().equals(senha)) {
            context.addMessage(null, new FacesMessage("usuario nao existe"));
			usuario = null;
			return null;
		} else {
			context.getExternalContext().getSessionMap().put("usuario", usuario);
			return "/index.xhtml?faces-redirect=true";
		}
	}
	
	public String getLogin()
	{
		return login;
	}
	
	public void setLogin(String login)
	{
		this.login = login;
	}

	public String getSenha()
	{
		return senha;
	}

	public void setSenha(String senha)
	{
		this.senha = senha;
	}
	
	public boolean isLogado()
	{
		if(usuario == null){
			return false;
		}else{
			return true;
		}
	}
}
