package com.rich.ampos.controller;

import com.rich.ampos.exception.EntityNotFoundException;
import com.rich.ampos.repository.entity.Menu;
import com.rich.ampos.service.MenuService;
import com.rich.ampos.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

/**
 * @author rich
 */
@Slf4j
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(Constants.API_MENU)
public class MenuController {

    @Value("${pagination.start}")
    private int pageStart;

    @Value("${pagination.size}")
    private int pageSize;

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    Page<Menu> findAll(
            @RequestParam(value="page", required=false) Integer page,
            @RequestParam(value="size", required=false) Integer size) {
        if(page == null) {
            page = pageStart;
        }
        if(size == null) {
            size = pageSize;
        }

        return menuService.find(PageRequest.of(page, size));
    }

    @GetMapping("/search")
    Page<Menu> search(
            @RequestParam(value="page", required=false) Integer page,
            @RequestParam(value="size", required=false) Integer size,
            @RequestParam(value="keyword") String keyword) {
        if(page == null) {
            page = pageStart;
        }
        if(size == null) {
            size = pageSize;
        }

        return menuService.search(keyword, PageRequest.of(page, size));
    }

    @PostMapping
    Menu newMenu(@RequestBody Menu newMenu) {
        return menuService.create(newMenu);
    }

    @GetMapping("/{id}")
    Menu findById(@PathVariable String id) {
        return menuService.find(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
    }

    @PutMapping("/{id}")
    Menu updateMenu(@RequestBody Menu newMenu, @PathVariable String id) {
        return menuService.update(id, newMenu);
    }

    @DeleteMapping("/{id}")
    void deleteMenu(@PathVariable String id) {
        menuService.delete(id);
    }

}
