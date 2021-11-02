package embedded.kafka.EmbeddedKafkaIntegrationTest;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    private static final Logger log = LoggerFactory.getLogger(KafkaConsumerService.class);
    private Boolean recordReceived;

    @KafkaListener(topics = "foo")
    public void listen(ConsumerRecord<String,Object> record) {
        log.info("------------------------------------------");
        log.info("Topic "+record.topic());
        log.info("Bank model : " + record.value());
        log.info("------------------------------------------");
        recordReceived=true;
    }

    public Boolean getRecordReceived() {
        return recordReceived;
    }
}
