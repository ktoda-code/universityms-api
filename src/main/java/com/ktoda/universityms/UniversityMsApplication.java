package com.ktoda.universityms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {
                "com.ktoda.universityms",
                "com.ktoda.api"
        }
)
public class UniversityMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(UniversityMsApplication.class, args);
    }

}
