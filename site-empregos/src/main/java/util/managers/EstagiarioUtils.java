package util.managers;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entidades.Estagiario;
import entidades.Usuario;

@ManagedBean
@SessionScoped
public class EstagiarioUtils implements EntityUtils<Estagiario>
{
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private Estagiario estagiario;
	private Usuario usuario;
	
	private List<Estagiario> listaEntidades;
	
	public EstagiarioUtils() {
		this.entityManagerFactory = Persistence.createEntityManagerFactory("site-empregos");
		this.entityManager = entityManagerFactory.createEntityManager();
		this.estagiario = new Estagiario();
	}
	
	@Override
	public void persistEntity()
	{
		EntityTransaction transactionObj = entityManager.getTransaction();
		if(!transactionObj.isActive()) {
			transactionObj.begin();
	    }
		usuario.setEstagiario(estagiario);
		estagiario.setUsuario(usuario);
		entityManager.persist(estagiario);
		transactionObj.commit();
		this.estagiario = new Estagiario();
		this.usuario = new Usuario();
	}

	@Override
	public void removeEntity(Long id)
	{
		entityManager.remove(this.searchEntity(id));
		entityManager.getTransaction().commit();
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

}