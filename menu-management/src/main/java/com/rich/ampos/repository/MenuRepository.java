package com.rich.ampos.repository;

import com.rich.ampos.repository.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface MenuRepository extends MongoRepository<Menu, String> {

    @Query(value = "{ $or: [ { 'name' : {$regex:?0,$options:'i'} }, { 'description' : {$regex:?0,$options:'i'} } ] }")
    Page<Menu> search(String keyword, Pageable page);
}
