package com.example.mvcdemo;

import com.example.mvcdemo.service.DataService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MvcDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MvcDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(DataService service){
        return args -> {
			String author = "lin-xin";
			String repo = "vue-manage-system";
			service.addContributors(author,repo);
			service.addIssues(author,repo);
			service.addReleases(author,repo);
			service.addCommits(author,repo);
        };
    }

}
