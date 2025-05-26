import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Wallet {
    private final PublicKey publicKey;
    private final PrivateKey privateKey;
    private static final Logger logger = Logger.getLogger(Wallet.class.getName());

    public Wallet() {
        var keyPair = generateKeyPair();
        this.publicKey = keyPair.getPublic();
        this.privateKey = keyPair.getPrivate();
        Ledger.addPublicKey(publicKey, 0.0);
    }

    public KeyPair generateKeyPair() {
        KeyPairGenerator generator = null;
        try {
            generator = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
           logger.log(Level.SEVERE, e.getMessage());
        }
        assert generator != null;
        generator.initialize(2048);
        return generator.generateKeyPair();
    }

    public void sendMoney(Double amount, PublicKey receiverPublicKey) {
        if (Ledger.getBalance(this.publicKey) < amount) {
            logger.log(Level.SEVERE, "Insufficient funds");
            return;
        }
        var transaction = new Transaction(amount, publicKey, receiverPublicKey);
        Signature sign;
        byte[] signature = null;
        try {
            sign = Signature.getInstance("SHA256withRSA");
            sign.initSign(privateKey);
            sign.update(transaction.toString().getBytes(StandardCharsets.UTF_8));
            signature = sign.sign();
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
           logger.log(Level.SEVERE, e.getMessage());
        }
        BlockChain.addBlock(transaction, publicKey, signature);
    }


    // Getters and Setters
    public PublicKey getPublicKey() {
        return publicKey;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }
}
