import java.security.PublicKey;

public record Transaction(Double amount, PublicKey sender, PublicKey receiver) {
}
