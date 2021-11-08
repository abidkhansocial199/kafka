package embedded.kafka.EmbeddedKafkaIntegrationTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@SpringBootTest
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:39092", "port=39092"})
@ActiveProfiles("test")
class EmbeddedKafkaIntegrationTestApplicationTests {
    private final ObjectMapper mapper=new ObjectMapper();

    @SpyBean
    private KafkaConsumerService consumer;

    @Autowired
    private KafkaProducerService producer;

    @Captor
    ArgumentCaptor<ConsumerRecord<String,Object>> consumerArgumentCaptor;


    @Test
    public void givenEmbeddedKafkaBroker_whenSendingToSimpleProducer_thenMessageReceived() {
         BankModel model = new BankModel("iuou97asdf89","7703", "13/05/2021", "John", "tim", 100d);

        //Producer
        producer.send(model);

        //wait for kafka consumer to catch up record
        Awaitility.await().atMost(Duration.ofMinutes(1))
                .ignoreExceptions()
                .pollInterval(Duration.ofMillis(100)).until(()->consumer.getRecordReceived());

        //consumer
        verify(consumer).listen(consumerArgumentCaptor.capture());

        //verify
        ConsumerRecord<String,Object> record= consumerArgumentCaptor.getValue();
        assertNotNull(record);
        assertTrue("foo".contains(record.topic()));
        try {
            assertEquals(model.getClass().getName(),
                    mapper.readValue(record.value().toString(), BankModel.class).getClass().getName());
            assertEquals(mapper.writeValueAsString(model), record.value());
        }
        catch (JsonProcessingException e){
           e.printStackTrace();
        }

    }
}
