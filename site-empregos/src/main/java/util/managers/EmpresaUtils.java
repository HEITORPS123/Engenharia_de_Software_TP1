package util.managers;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entidades.Empresa;
import entidades.Usuario;

@ManagedBean
@SessionScoped
public class EmpresaUtils implements EntityUtils<Empresa>
{
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private Empresa empresa;
	private Usuario usuario;
	
	private List<Empresa> listaEntidades;
	
	public EmpresaUtils() {
		this.entityManagerFactory = Persistence.createEntityManagerFactory("site-empregos");
		this.entityManager = entityManagerFactory.createEntityManager();
		this.empresa = new Empresa();
	}
	
	@Override
	public void persistEntity()
	{
		EntityTransaction transactionObj = entityManager.getTransaction();
		if(!transactionObj.isActive()) {
			transactionObj.begin();
	    }
		usuario.setEmpresa(empresa);
		empresa.setUsuario(usuario);
		entityManager.persist(empresa);
		transactionObj.commit();
		this.empresa = new Empresa();
		this.usuario = new Usuario();
	}

	@Override
	public void removeEntity(Long id)
	{
		entityManager.remove(this.searchEntity(id));
		entityManager.getTransaction().commit();
	}

	@Override
	public List<Empresa> getAllEntities()
	{
		Query query = entityManager.createQuery("SELECT e FROM empresa e",Empresa.class);
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

}
