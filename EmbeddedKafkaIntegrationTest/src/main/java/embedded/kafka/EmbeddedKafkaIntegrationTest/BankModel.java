package embedded.kafka.EmbeddedKafkaIntegrationTest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BankModel {
    private String accountNumber;
    private String transactionId;
    private String dateOfBirth;
    private String firstName;
    private String lastName;
    private Double balance;
}
