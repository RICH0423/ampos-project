package com.rich.ampos.service;

import com.rich.ampos.repository.MenuRepository;
import com.rich.ampos.repository.entity.Menu;
import lombok.extern.slf4j.Slf4j;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MenuServiceImplTest {

    @MockBean
    MenuRepository menuRepository;

    @Autowired
    MenuService menuService;

    private static Menu menu;

    @BeforeClass
    public static void initMockData() {
        menu = Menu.builder().id("1").name("menu1").build();
    }

    @Test
    public void find_GivenPageable_ReturnsMenus() {
        List<Menu> menus = new ArrayList<>();
        menus.add(menu);
        Pageable pageable = PageRequest.of(0, 10);

        when(menuRepository.findAll(pageable)).thenReturn(new PageImpl<>(menus, pageable, menus.size()));

        Page<Menu> result = menuService.find(pageable);
        assertThat(result.getContent(), hasSize(1));
    }

    @Test
    public void findById() {
    }

    @Test
    public void create_GivenValidMenu_NoExceptionThrown() {
        assertThatCode(() -> menuService.create(menu))
                .doesNotThrowAnyException();
    }

    @Test
    public void update() {

    }

    @Test
    public void delete() {
    }

    @Test
    public void search() {
    }
}