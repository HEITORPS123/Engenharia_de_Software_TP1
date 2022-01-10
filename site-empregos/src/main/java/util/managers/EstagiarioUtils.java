package util.managers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.apache.commons.io.IOUtils;
import org.primefaces.model.UploadedFile;

import entidades.Estagiario;
import entidades.Usuario;

@ManagedBean
@SessionScoped
public class EstagiarioUtils implements EntityUtils<Estagiario>
{
	@PersistenceContext
	private EntityManager entityManager;
	
	@Resource
	private UserTransaction transaction;
	
	private Estagiario estagiario;
	private Usuario usuario;
	
	private List<Estagiario> listaEntidades;
	
	private UploadedFile arquivo;
	private byte[] conteudo;
	
	public EstagiarioUtils() {
		this.estagiario = new Estagiario();
		this.usuario = new Usuario();
	}
	
	@Override
	public String persistEntity()
	{
		System.out.println("AAAAAAAAAAAA");

		try{
			transaction.begin();
	
			usuario.setEstagiario(estagiario);
			estagiario.setUsuario(usuario);
			if(conteudo != null) {
				estagiario.setImagem(conteudo);
				conteudo = null;
			}else {
				BufferedImage bImage = ImageIO.read(new File("/home/heitor/Desktop/keanu.jpg"));
			    ByteArrayOutputStream bos = new ByteArrayOutputStream();
			    ImageIO.write(bImage, "jpg", bos );
			    estagiario.setImagem(bos.toByteArray());
			}
			entityManager.persist(estagiario);
			transaction.commit();
			this.estagiario = new Estagiario();
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
	public List<Estagiario> getAllEntities()
	{
		Query query = entityManager.createQuery("SELECT e FROM estagiario e",Estagiario.class);
		return query.getResultList();
	}

	@Override
	public Estagiario searchEntityByName(String nome)
	{
		Query query = entityManager.createQuery("SELECT e FROM estagiario e WHERE e.nome = ?1", Estagiario.class).setParameter(1, nome);
		return (Estagiario) query.getSingleResult();
	}

	@Override
	public Estagiario searchEntity(Long id)
	{
		return entityManager.find(Estagiario.class, id);
	}

	public List<Estagiario> getListaEntidades()
	{
		if(listaEntidades == null) {
			listaEntidades = getAllEntities();
		}
		return listaEntidades;
	}

	public void setListaEntidades(List<Estagiario> listaEntidades)
	{
		this.listaEntidades = listaEntidades;
	}

	public Estagiario getEstagiario()
	{
		if(estagiario == null) {
			this.estagiario = new Estagiario();
		}
		return estagiario;
	}

	public void setEstagiario(Estagiario estagiario)
	{
		this.estagiario = estagiario;
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
		System.out.println("Aaa");
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
