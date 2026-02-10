package tech.ermakovich;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class NewsAnalyzerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsAnalyzerApplication.class, args);
	}

}
