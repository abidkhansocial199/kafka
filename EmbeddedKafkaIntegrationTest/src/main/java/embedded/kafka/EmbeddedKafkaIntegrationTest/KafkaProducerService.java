package embedded.kafka.EmbeddedKafkaIntegrationTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class KafkaProducerService {
    private final ObjectMapper mapper=new ObjectMapper();

    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void send(BankModel bankModel) {
        try {
            kafkaTemplate.send("foo",mapper.writeValueAsString(bankModel));
        }
        catch (JsonProcessingException e){
            e.printStackTrace();
        }
    }

}
