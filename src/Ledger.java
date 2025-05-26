import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Ledger {
    private static final Logger logger = Logger.getLogger(Ledger.class.getName());

    private static Map<PublicKey, Double> balances = new HashMap<PublicKey, Double>();

    private Ledger() {}

    public static void credit(PublicKey publicKey, Double amount) {
        double newBalance = getBalance(publicKey) + amount;
        balances.replace(publicKey, newBalance);
    }

    private static void debit(PublicKey publicKey, Double amount) {
        double newBalance = getBalance(publicKey) - amount;
        balances.replace(publicKey, newBalance);
    }

    public static void addPublicKey(PublicKey publicKey, Double amount) {
        balances.put(publicKey, amount);
    }

    public static void transact(PublicKey publicKeySender, PublicKey publicKeyReceiver, Double amount) {
        credit(publicKeyReceiver, amount);
        debit(publicKeySender, amount);
    }

    public static double getBalance(PublicKey publicKey) {
        return balances.get(publicKey);
    }

    public static Map<PublicKey, Double> getBalances() {
        return balances;
    }
}
