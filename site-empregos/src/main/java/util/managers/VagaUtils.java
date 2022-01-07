package util.managers;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entidades.Empresa;
import entidades.Vaga;

@ManagedBean
@SessionScoped
public class VagaUtils implements EntityUtils<Vaga>
{
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private Vaga vaga;
	
	private List<Vaga> listaEntidades;
	
	public VagaUtils() {
		this.entityManagerFactory = Persistence.createEntityManagerFactory("site-empregos");
		this.entityManager = entityManagerFactory.createEntityManager();
		this.vaga = new Vaga();
	}
	
	@Override
	public void persistEntity()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeEntity(Long id)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Vaga> getAllEntities()
	{
		Query query = entityManager.createQuery("SELECT e FROM Vaga e",Vaga.class);
		return query.getResultList();
	}

	@Override
	public Vaga searchEntity(Long id)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vaga searchEntityByName(String nome)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public List<Vaga> getListaEntidades()
	{
		if(listaEntidades == null) {
			listaEntidades = getAllEntities();
		}
		return listaEntidades;
	}

	public void setListaEntidades(List<Vaga> listaEntidades)
	{
		this.listaEntidades = listaEntidades;
	}
	
}
