package SIIT.Final.Project;

import SIIT.Final.Project.controller.FileUpload;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.File;

@SpringBootApplication
@ComponentScan({"SIIT.Final.Project","controller"})
public class FinalProjectApplication {

	public static void main(String[] args) {
		new File(FileUpload.uploadDirectory).mkdir();
		SpringApplication.run(FinalProjectApplication.class, args);
	}

}
