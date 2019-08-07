package com.rich.ampos.controller;

import com.rich.ampos.repository.entity.Menu;
import com.rich.ampos.service.MenuService;
import com.rich.ampos.util.Constants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(MenuController.class)
public class MenuControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MenuService menuService;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {
        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }


    @Test
    public void findAll_GivenSpecificPage_ReturnsTheCorrectStatusAndData() throws Exception {
        List<Menu> menus = new ArrayList<>();
        menus.add(Menu.builder().id("1").name("menu1").build());
        menus.add(Menu.builder().id("2").name("menu2").build());
        menus.add(Menu.builder().id("3").name("menu3").build());

        Pageable pageable = PageRequest.of(0, 10);
        given(menuService.find(pageable)).willReturn(new PageImpl<>(menus, pageable, menus.size()));

        mockMvc.perform(get("/" + Constants.API_MENU)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", hasSize(3)))
                .andExpect(jsonPath("$.content[0].name", is("menu1")));

    }

    @Test
    public void newMenu_GivenSpecificMenu_ReturnsTheCorrectStatusAndData() throws Exception {
        Menu menu = Menu.builder()
                .id("1")
                .name("menu1")
                .build();

        given(menuService.create(menu)).willReturn(menu);

        mockMvc.perform(post("/" + Constants.API_MENU)
                .content(convertToJson(menu))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is("1")))
                .andExpect(jsonPath("$.name", is("menu1")));
    }

    @Test
    public void deleteMenu_GivenSpecificMenuId_ReturnsTheCorrectStatus() throws Exception {
        String menuId = "1";

        mockMvc.perform(delete("/" + Constants.API_MENU + "/{id}", menuId)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    protected String convertToJson(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}