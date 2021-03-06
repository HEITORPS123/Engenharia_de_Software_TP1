package util.managers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import org.primefaces.model.DefaultStreamedContent;

import entidades.Aplicacao;
import entidades.Curriculo;
import entidades.Empresa;
import entidades.Estagiario;
import entidades.Usuario;
import entidades.Vaga;
import seguranca.LoginManager;

@ManagedBean
@ViewScoped
public class VagaUtils implements EntityUtils<Vaga>
{
	@PersistenceContext
	private EntityManager entityManager;
	
	@Resource
	private UserTransaction transaction;
	
	private Vaga vaga;
	
	private List<Vaga> listaEntidades;
	private List<Aplicacao> listaAplicacoes;
	
	private boolean telaInfo = false;
	
	public VagaUtils() {
		this.vaga = new Vaga();
	}
	
	public String persistEntity(Empresa empresa)
	{
		try{
			transaction.begin();
			vaga.setEmpresa(empresa);
			entityManager.persist(vaga);
			transaction.commit();
			this.vaga = new Vaga();
			return "/index.xhtml?faces-redirect=true";
		} catch (Exception e) {
		    e.printStackTrace();
		    return "/index.xhtml?faces-redirect=true";
		}
	}
	
	public String persistAplicacao(Estagiario estagiario) {
		try{
			Aplicacao aplicacao = new Aplicacao();
			transaction.begin();
			aplicacao.setEstagiario(estagiario);
			aplicacao.setVaga(vaga);
			entityManager.persist(aplicacao);
			transaction.commit();
			return "/index.xhtml?faces-redirect=true";
		} catch (Exception e) {
		    e.printStackTrace();
		    return "/index.xhtml?faces-redirect=true";
		}
	}

	public List<Vaga> listarVagasPorEmpresa(Empresa empresa){
		Query query = entityManager.createQuery("SELECT v FROM Vaga v WHERE v.empresa.id = :empresaId",Vaga.class);
		query.setParameter("empresaId", empresa.getId());
		return query.getResultList();
	}
	
	public String removeEntity(Long id)
	{
		try {
			transaction.begin();
			entityManager.remove(this.searchEntity(id));
			transaction.commit();
			return "/index.xhtml?faces-redirect=true";
		} catch(Exception e) {
			e.printStackTrace();
			return "/index.xhtml?faces-redirect=true";
		}
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
		return entityManager.find(Vaga.class, id);
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
	
	public boolean getTelaInfo()
	{
		return telaInfo;
	}

	public void setTelaInfo(boolean telaInfo)
	{
		this.telaInfo = telaInfo;
	}
	
	public void irPaginaInfo(Vaga v) {
		//System.out.println("lmao");
		this.vaga = v;
		this.telaInfo = true;
	}

	public Vaga getVaga()
	{
		if(vaga == null) {
			this.vaga = new Vaga();
		}
		return vaga;
	}

	public void setVaga(Vaga vaga)
	{
		this.vaga = vaga;
	}

	@Override
	public String persistEntity()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public List<Aplicacao> getListaAplicacoes()
	{
		Query query = entityManager.createQuery("SELECT a FROM Aplicacao a WHERE a.vaga.id = :vagaId",Aplicacao.class);
		query.setParameter("vagaId", vaga.getId());
		return query.getResultList();
	}

	public void setListaAplicacoes(List<Aplicacao> listaAplicacoes)
	{
		this.listaAplicacoes = listaAplicacoes;
	}
	
	public DefaultStreamedContent download(Curriculo curriculo) throws IOException {
		InputStream byteStream = new ByteArrayInputStream(curriculo.getArquivo());
		DefaultStreamedContent downloadArquivo = new DefaultStreamedContent(byteStream, "text/csv", "arquivo.pdf");
    	return downloadArquivo;
	}
	
	
}
