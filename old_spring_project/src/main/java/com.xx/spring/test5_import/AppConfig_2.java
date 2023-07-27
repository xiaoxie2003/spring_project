package com.xx.spring.test5_import;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({Banana.class, FruitImportSelector.class})
public class AppConfig_2 {

}
