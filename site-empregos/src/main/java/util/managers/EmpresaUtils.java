package util.managers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import org.apache.commons.io.IOUtils;
import org.primefaces.model.UploadedFile;

import entidades.Empresa;
import entidades.Usuario;

@ManagedBean
@ViewScoped
public class EmpresaUtils implements EntityUtils<Empresa>
{
	@PersistenceContext
	private EntityManager entityManager;
	
	@Resource
	private UserTransaction transaction;
	
	private Empresa empresa;
	private Usuario usuario;
	
	private List<Empresa> listaEntidades;
	
	private UploadedFile arquivo;
	private byte[] conteudo;
	
	public EmpresaUtils() {

	}
	
	@Override
	public String persistEntity()
	{
		System.out.println(empresa.getNomeComercial());
		
		try{
			transaction.begin();
			usuario.setEmpresa(empresa);
			empresa.setUsuario(usuario);
			if(conteudo != null) {
				empresa.setImagem(conteudo);
				conteudo = null;
			}
			entityManager.persist(empresa);
			transaction.commit();
			this.empresa = new Empresa();
			this.usuario = new Usuario();
			return "/index.xhtml?faces-redirect=true";
		} catch (Exception e) {
		    e.printStackTrace();
		    return "/index.xhtml?faces-redirect=true";
		}
	}

	@Override
	public String removeEntity(Long id)
	{
		try {
			entityManager.remove(this.searchEntity(id));
			transaction.commit();
			return null;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Empresa> getAllEntities()
	{
		Query query = entityManager.createQuery("SELECT e FROM Empresa e",Empresa.class);
		return query.getResultList();
	}

	@Override
	public Empresa searchEntityByName(String nome)
	{
		Query query = entityManager.createQuery("SELECT e FROM empresa e WHERE e.nomeComercial = ?1", Empresa.class).setParameter(1, nome);
		return (Empresa) query.getSingleResult();
	}

	@Override
	public Empresa searchEntity(Long id)
	{
		return entityManager.find(Empresa.class, id);
	}

	public List<Empresa> getListaEntidades()
	{
		if(listaEntidades == null) {
			listaEntidades = getAllEntities();
		}
		return listaEntidades;
	}

	public void setListaEntidades(List<Empresa> listaEntidades)
	{
		this.listaEntidades = listaEntidades;
	}

	public Empresa getEmpresa()
	{
		if(empresa == null) {
			System.out.println("aaaa");
			this.empresa = new Empresa();
		}
		return empresa;
	}

	public void setEmpresa(Empresa estagiario)
	{
		this.empresa = estagiario;
	}

	public Usuario getUsuario()
	{
		if(usuario == null) {
			this.usuario = new Usuario();
		}
		return usuario;
	}

	public void setUsuario(Usuario usuario)
	{
		this.usuario = usuario;
	}

	public UploadedFile getArquivo()
	{
		return arquivo;
	}

	public void setArquivo(UploadedFile arquivo)
	{
		InputStream input;
		try
		{
			input = arquivo.getInputstream();
			conteudo = IOUtils.toByteArray(input);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}
