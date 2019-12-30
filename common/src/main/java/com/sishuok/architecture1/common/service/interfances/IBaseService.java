package com.sishuok.architecture1.common.service.interfances;

import java.util.List;


public interface IBaseService<E> {
	public void create(E customerModel);

	public void update(E customerModel);

	public void delete(int uuid);

	public E getByUuid(int uuid);

	public List<E> getByCondition(E customerModel);
	
	public List<E>  getAll();
}
