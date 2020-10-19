package com.domgo.commons.excel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GenericDao<O, T, ID> extends JpaRepository<T, ID>{

}
