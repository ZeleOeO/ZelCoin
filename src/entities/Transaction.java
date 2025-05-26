package entities;

import java.security.PublicKey;
import java.util.Base64;

public record Transaction(Double amount, PublicKey sender, PublicKey receiver) {
    @Override
    public String toString() {
        String senderKey = Base64.getEncoder().encodeToString(sender.getEncoded());
        String receiverKey = Base64.getEncoder().encodeToString(receiver.getEncoded());

        return "entities.Transaction{" +
               "\n amount=" + amount +
               ",\n sender=" + senderKey +
               ",\n receiver=" + receiverKey +
               "\n }";
    }
}
