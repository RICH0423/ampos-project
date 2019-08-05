package com.rich.ampos.service;

import com.rich.ampos.repository.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MenuService {

    Page<Menu> find(Pageable pageable);

    Optional<Menu> find(String id);

    Menu create(Menu menu);

    Menu update(String id, Menu menu);

    void delete(String id);

    Page<Menu> search(String keyword, Pageable pageable);
}
