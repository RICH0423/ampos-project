package com.rich.ampos.config;

import com.rich.ampos.repository.MenuRepository;
import com.rich.ampos.repository.entity.Menu;
import com.rich.ampos.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.Arrays;

@Slf4j
@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(MenuRepository menuRepository) {
        return args -> {
            String menu1Id = "pre-created-1";
            log.info(Constants.INIT_DATA_LOG, !menuRepository.existsById(menu1Id) ?
                    menuRepository.save(
                    Menu.builder()
                            .id(menu1Id)
                            .name("Hawaiian Pizza")
                            .description("All-time favourite toppings, Hawaiian pizza in Tropical Hawaii style.")
                            .image("https://s3-ap-southeast-1.amazonaws.com/interview.ampostech.com/backend/restaurant/menu1.jpg")
                            .price(new BigDecimal(300))
                            .type(Arrays.asList("Italian", "Ham", "Pineapple"))
                            .build()
                    ) : Constants.INIT_DATA_EXISTS
            );

            String menu2Id = "pre-created-2";
            log.info(Constants.INIT_DATA_LOG, !menuRepository.existsById(menu2Id) ? menuRepository.save(
                    Menu.builder()
                            .id(menu2Id)
                            .name("Chicken Tom Yum Pizza")
                            .description("Best marinated chicken with pineapple and mushroom on Spicy Lemon sauce. Enjoy our tasty Thai style pizza.")
                            .image("https://s3-ap-southeast-1.amazonaws.com/interview.ampostech.com/backend/restaurant/menu2.jpg")
                            .price(new BigDecimal(350))
                            .type(Arrays.asList("Italian", "Thai", "Chicken", "Mushroom", "Hot"))
                            .build()
                    ) : Constants.INIT_DATA_EXISTS
            );

            String menu3Id = "pre-created-3";
            log.info(Constants.INIT_DATA_LOG, !menuRepository.existsById(menu3Id) ? menuRepository.save(
                    Menu.builder()
                            .id(menu3Id)
                            .name("Xiaolongbao")
                            .description("Chinese steamed bun")
                            .image("https://s3-ap-southeast-1.amazonaws.com/interview.ampostech.com/backend/restaurant/menu3.jpg")
                            .price(new BigDecimal(200))
                            .type(Arrays.asList("Chinese", "Pork", "Recommended", "Recommended"))
                            .build()
                    ) : Constants.INIT_DATA_EXISTS
            );

            String menu4Id = "pre-created-4";
            log.info(Constants.INIT_DATA_LOG, !menuRepository.existsById(menu4Id) ? menuRepository.save(
                    Menu.builder()
                            .id(menu4Id)
                            .name("Kimchi")
                            .description("Traditional side dish made from salted and fermented vegetables")
                            .image("https://s3-ap-southeast-1.amazonaws.com/interview.ampostech.com/backend/restaurant/menu4.jpg")
                            .price(new BigDecimal(50))
                            .type(Arrays.asList("Korean", "Radish", "Cabbage"))
                            .build()
                    ) : Constants.INIT_DATA_EXISTS
            );

            String menu5Id = "pre-created-5";
            log.info(Constants.INIT_DATA_LOG, !menuRepository.existsById(menu5Id) ? menuRepository.save(
                    Menu.builder()
                            .id(menu5Id)
                            .name("Partially fermented tea grown in the Alishan area")
                            .description("Chinese steamed bun")
                            .image("https://s3-ap-southeast-1.amazonaws.com/interview.ampostech.com/backend/restaurant/menu5.jpg")
                            .price(new BigDecimal(200))
                            .type(Arrays.asList("Hot", "Non-alcoh", "ol"))
                            .build()
                    ) : Constants.INIT_DATA_EXISTS
            );
        };
    }

}
