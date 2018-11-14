package com.d2c.common.search.repository;

import java.io.Serializable;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.d2c.common.search.model.ParentSearchDO;

@NoRepositoryBean
public interface BaseRepository<T extends ParentSearchDO<ID>, ID extends Serializable> extends ElasticsearchRepository<T, ID> {

}
