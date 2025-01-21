package com.example.Menu.Config;


import com.example.Menu.Entity.Menu;
import com.example.Menu.Repository.MenuRepository;
import com.example.Menu.Service.MenuService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class AppRunnerConfig {

    MenuRepository menuRepository;

    @Bean
    ApplicationRunner applicationRunnerConfig(){
        return args -> {
            List<Menu> menus = new ArrayList<>();
            menus.add(new Menu("nuong", 150000l, 120000l, "FOOD","ON", "món nướng"));
            menus.add(new Menu("lau", 250000l,200000l,"FOOD","ON", "món lẩu"));
            menus.add(new Menu("hap", 120000l,100000l, "FOOD", "ON", "món hấp"));
            menus.add(new Menu("coca", 10000l,10000l, "DRINK", "ON", "nước ngọt"));
            menus.stream().forEach(menu -> {
                if(!menuRepository.existsById(menu.getItemID())){
                    menuRepository.save(menu);
                }
            });
        };
    }
}
