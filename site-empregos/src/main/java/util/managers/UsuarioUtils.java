package util.managers;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entidades.Usuario;

@Named
@RequestScoped
public class UsuarioUtils implements EntityUtils<Usuario>
{
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;

	private Usuario usuario;
	
	private List<Usuario> listaEntidades;
	
	public UsuarioUtils() {
		this.entityManagerFactory = Persistence.createEntityManagerFactory("site-empregos");
		this.entityManager = entityManagerFactory.createEntityManager();
		this.usuario = new Usuario();
	}
	
	@Override
	public void persistEntity()
	{
		EntityTransaction transactionObj = entityManager.getTransaction();
		if(!transactionObj.isActive()) {
			transactionObj.begin();
	    }
		entityManager.persist(usuario);
		transactionObj.commit();
		this.usuario = new Usuario();
	}

	@Override
	public void removeEntity(Long id)
	{
		entityManager.remove(this.searchEntity(id));
		entityManager.getTransaction().commit();
	}

	@Override
	public List<Usuario> getAllEntities()
	{
		Query query = entityManager.createQuery("SELECT e FROM usuario e",Usuario.class);
		return query.getResultList();
	}

	@Override
	public Usuario searchEntityByName(String nome)
	{
		Query query = entityManager.createQuery("SELECT e FROM Usuario e WHERE e.login = ?1", Usuario.class).setParameter(1, nome);
		return (Usuario) query.getSingleResult();
	}

	@Override
	public Usuario searchEntity(Long id)
	{
		return entityManager.find(Usuario.class, id);
	}

	public List<Usuario> getListaEntidades()
	{
		if(listaEntidades == null) {
			listaEntidades = getAllEntities();
		}
		return listaEntidades;
	}

	public void setListaEntidades(List<Usuario> listaEntidades)
	{
		this.listaEntidades = listaEntidades;
	}

	public Usuario getUsuario()
	{
		if(usuario == null) {
			usuario = new Usuario();
		}
		return usuario;
	}

	public void setUsuario(Usuario usuario)
	{
		this.usuario = usuario;
	}

}
