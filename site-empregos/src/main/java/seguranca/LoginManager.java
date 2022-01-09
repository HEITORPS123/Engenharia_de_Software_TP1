package seguranca;

import java.io.Serializable;

import javax.ejb.Stateful;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import entidades.Empresa;
import entidades.Usuario;
import util.managers.UsuarioUtils;

@SuppressWarnings("deprecation")
@ManagedBean
@SessionScoped
public class LoginManager
{
	
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
			return "/index.xhtml?faces-redirect=true";
		}
	}
	
	public String logout() {
		usuario = null;
		return "/index.xhtml?faces-redirect=true";
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
	
	public boolean isEmpresa() {
		if(isLogado()) {
			if(usuario.getEmpresa() == null) {
				return false;
			}else {
				return true;
			}
		}else {
			return false;
		}
	}
	
	public boolean isEstagiario() {
		if(isLogado()) {
			if(usuario.getEstagiario() == null) {
				return false;
			}else {
				return true;
			}
		}else {
			return false;
		}
	}
	
	public Empresa getEmpresa() {
		return usuario.getEmpresa();
	}
}
