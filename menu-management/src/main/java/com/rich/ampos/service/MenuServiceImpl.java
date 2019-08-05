package com.rich.ampos.service;

import com.rich.ampos.exception.EntityNotFoundException;
import com.rich.ampos.repository.MenuRepository;
import com.rich.ampos.repository.entity.Menu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author rich
 */
@Slf4j
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;


    @Override
    public Page<Menu> find(Pageable pageable) {
        return menuRepository.findAll(pageable);
    }

    @Override
    public Optional<Menu> find(String id) {
        return menuRepository.findById(id);
    }

    @Override
    public Menu create(Menu menu) {
        return menuRepository.save(menu);
    }

    @Override
    public Menu update(String id, Menu newMenu) {
        return menuRepository.findById(id)
                .map(menu -> {
                    menu.setName(newMenu.getName());
                    menu.setDescription(newMenu.getDescription());
                    menu.setPrice(newMenu.getPrice());
                    menu.setType(newMenu.getType());
                    return menuRepository.save(menu);
                })
                .orElseThrow(() -> new EntityNotFoundException(id));
    }

    @Override
    public void delete(String id) {
        menuRepository.deleteById(id);
    }

    @Override
    public Page<Menu> search(String keyword, Pageable pageable) {
        return menuRepository.search(keyword, pageable);
    }
}
