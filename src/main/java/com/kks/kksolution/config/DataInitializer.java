package com.kks.kksolution.config;

import com.kks.kksolution.entity.Menu;
import com.kks.kksolution.enumeration.MenuType;
import com.kks.kksolution.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final MenuRepository menuRepository;
    @Override
    public void run(String... args) throws Exception {

    }
}
