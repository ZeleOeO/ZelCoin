import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BlockChain {
    private static List<Block> blocks = new ArrayList<Block>(
    );

    private static final Logger logger = Logger.getLogger(BlockChain.class.getName());

    private BlockChain() {
    }

    public static void addBlock(Transaction transaction, PublicKey publicKey, byte[] signature) {
        Signature sign;
        boolean isValid = false;
        try {
            sign = Signature.getInstance("SHA256withRSA");
            sign.initVerify(publicKey);
            sign.update(transaction.toString().getBytes(StandardCharsets.UTF_8));
            isValid = sign.verify(signature);
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }

        if (isValid) {
            blocks.add(new Block(blocks.getLast().getPrevHash(), transaction));
            Ledger.transact(transaction.sender(), transaction.receiver(), transaction.amount());
        }
    }

    public static List<Block> getBlocks() {
        return blocks;
    }

}
