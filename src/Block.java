import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Block {
    private static final Logger logger = Logger.getLogger(Block.class.getName());
    private final String hash;
    private final Transaction transaction;
    private final String prevHash;
    private final String timeStamp;
    private String nonce;

    public Block(String prevHash, Transaction data) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.prevHash = prevHash;
        this.transaction = data;
        this.timeStamp = LocalDateTime.now().format(dtf);
        this.hash = calculateHash();
    }


    public String calculateHash() {
        String calculatedHash = prevHash + transaction + timeStamp;
        byte[] hash = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            hash = digest.digest(calculatedHash.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
        StringBuilder builder = new StringBuilder();
        for (byte b : Objects.requireNonNull(hash)) builder.append(String.format("%02x", b));
        return builder.toString();
    }

    // Getters and Setters
    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getPrevHash() {
        return prevHash;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public String getHash() {
        return hash;
    }
}