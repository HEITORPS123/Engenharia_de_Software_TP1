package util.managers;

import java.util.List;

public interface EntityUtils<T>
{
	public void persistEntity();
	public void removeEntity(Long id);
	public List<T> getAllEntities();
	public T searchEntity(Long id);
	public T searchEntityByName(String nome);
}
