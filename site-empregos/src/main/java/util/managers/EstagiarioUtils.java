package util.managers;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
	
	private List<Estagiario> listaEntidades;
	
	public EstagiarioUtils() {
		this.entityManagerFactory = Persistence.createEntityManagerFactory("site-empregos");
		this.entityManager = entityManagerFactory.createEntityManager();
		this.estagiario = new Estagiario();
	}
	
	@Override
	public void persistEntity()
	{
		entityManager.persist(estagiario);
		entityManager.getTransaction().commit();
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

}
