package util.managers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import org.apache.commons.io.IOUtils;
import org.primefaces.model.UploadedFile;

import entidades.Curriculo;
import entidades.Empresa;
import entidades.Estagiario;
import entidades.Usuario;

@ManagedBean
@ViewScoped
public class CurriculoUtils implements EntityUtils<Curriculo>
{
	@PersistenceContext
	private EntityManager entityManager;
	
	@Resource
	private UserTransaction transaction;
	
	private Curriculo curriculo;
	
	private UploadedFile arquivo;
	private byte[] conteudo;
	
	public String persistEntity(Estagiario estagiario)
	{
		System.out.println("AAAAAAAAAAAA");
		curriculo = new Curriculo();
		try{
			transaction.begin();
	
			InputStream input = arquivo.getInputstream();
			conteudo = IOUtils.toByteArray(input);
			curriculo.setArquivo(conteudo);
			conteudo = null;
			
			curriculo.setEstagiario(estagiario);
			estagiario.setCurriculo(curriculo);
			entityManager.persist(curriculo);
			transaction.commit();
			this.curriculo = new Curriculo();
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
	public List<Curriculo> getAllEntities()
	{
		Query query = entityManager.createQuery("SELECT c FROM Curriculo c",Curriculo.class);
		return query.getResultList();
	}

	@Override
	public Curriculo searchEntity(Long id)
	{
		return entityManager.find(Curriculo.class, id);
	}

	@Override
	public Curriculo searchEntityByName(String nome)
	{
		return null;
	}

	public Curriculo getCurriculo()
	{
		if(curriculo == null) {
			curriculo = new Curriculo();
		}
		return curriculo;
	}

	public void setCurriculo(Curriculo curriculo)
	{
		this.curriculo = curriculo;
	}

	public UploadedFile getArquivo()
	{
		return arquivo;
	}

	public void setArquivo(UploadedFile arquivo)
	{
		this.arquivo = arquivo;
	}

	@Override
	public String persistEntity()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
