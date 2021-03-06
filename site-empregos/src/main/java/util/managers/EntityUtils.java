package util.managers;

import java.util.List;

public interface EntityUtils<T>
{
	public String persistEntity();
	public String removeEntity(Long id);
	public List<T> getAllEntities();
	public T searchEntity(Long id);
	public T searchEntityByName(String nome);
}
