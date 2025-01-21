package com.example.Table.Config;

import com.example.Table.Entity.Table;
import com.example.Table.Repository.TableRepository;
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
public class ApplicationRunnerConfig {
    TableRepository tableRepository;

    @Bean
    ApplicationRunner appConfig(){
        return args -> {
            List<Table> tables = new ArrayList<>();
            tables.add(new Table("ban_1","NOT",4l,"NOT"));
            tables.add(new Table("ban_2","NOT",4l,"HAD"));
            tables.add(new Table("ban_3","NOT",4l,"HAD"));
            tables.add(new Table("ban_4","NOT",4l,"NOT"));
            tables.add(new Table("ban_5","NOT",4l,"HAD"));

            tables.stream().forEach(table -> {
                if(!tableRepository.existsById(table.getTableID())){
                    tableRepository.save(table);
                }
            });

        };
    }


}
