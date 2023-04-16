package ru.ocupuc.smartHome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.ocupuc.smartHome.entity.Lamp;
import ru.ocupuc.smartHome.entity.Script;

@SpringBootApplication
public class SmartHomeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartHomeApplication.class, args);
		Script script = new Script();

	}

}
