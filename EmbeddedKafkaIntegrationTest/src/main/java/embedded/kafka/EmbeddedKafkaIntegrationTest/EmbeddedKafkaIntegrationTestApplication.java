package embedded.kafka.EmbeddedKafkaIntegrationTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class EmbeddedKafkaIntegrationTestApplication {
    @Autowired
    private KafkaProducerService service;

    @GetMapping
    public String get(){
		System.out.println("get request...");
        BankModel model = new BankModel("iuou97asdf89", "7703", "13/05/2021", "John", "tim", 100d);
        service.send(model);
        return "Success";
    }

    public static void main(String[] args) {
        SpringApplication.run(EmbeddedKafkaIntegrationTestApplication.class, args);
    }

}
