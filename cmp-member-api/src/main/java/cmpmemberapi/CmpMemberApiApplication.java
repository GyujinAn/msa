package cmpmemberapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class CmpMemberApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CmpMemberApiApplication.class, args);
	}


}
