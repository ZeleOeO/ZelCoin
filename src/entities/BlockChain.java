package entities;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BlockChain {
    private static final Logger logger = Logger.getLogger(BlockChain.class.getName());
    private static List<Block> blocks = new ArrayList<>();

    static {
        Wallet genWallet1 = new Wallet();
        Wallet genWallet2 = new Wallet();
        Transaction genesisTransaction = new Transaction(0.0, genWallet1.getPublicKey(), genWallet2.getPublicKey());
        blocks.add(new Block("0", genesisTransaction));
    }

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
            Block newBlock = new Block(blocks.getLast().getHash(), transaction);
            newBlock.mineBlock(3);
            blocks.add(newBlock);
            Ledger.transact(transaction.sender(), transaction.receiver(), transaction.amount());
        }
    }

    public static void print() {
        for (Block block : BlockChain.getBlocks()) {
            System.out.println("------------");
            System.out.println("Hash: " + block.getHash());
            System.out.println("Prev Hash: " + block.getPrevHash());
            System.out.println("Timestamp: " + block.getTimeStamp());
            System.out.println("entities.Transaction: " + block.getTransaction());
            System.out.println("------------\n");
        }
    }

    public static List<Block> getBlocks() {
        return blocks;
    }

}
