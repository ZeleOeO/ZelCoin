import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.security.PublicKey;
import java.security.Signature;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BlockChainTest {

    @BeforeEach
    public void resetBlockchain() {
        BlockChain.getBlocks().clear();
        Transaction genesisTransaction = new Transaction(0, "GENESIS", "GENESIS");
        Block genesisBlock = new Block("0", genesisTransaction);
        BlockChain.getBlocks().add(genesisBlock);
    }

    @Test
    public void testAddBlockValidTransaction() {
        Wallet sender = new Wallet();
        Wallet receiver = new Wallet();
        int initialSize = BlockChain.getBlocks().size();

        sender.sendMoney(100, receiver.getPublicKey().toString());

        List<Block> blocks = BlockChain.getBlocks();
        assertEquals(initialSize + 1, blocks.size());

        Block newBlock = blocks.getLast();
        assertEquals(receiver.getPublicKey().toString(), newBlock.getTransaction().receiver());
        assertEquals(sender.getPublicKey().toString(), newBlock.getTransaction().sender());
    }

    @Test
    public void testBlockHashNotNull() {
        Transaction tx = new Transaction(10, "sender", "receiver");
        Block block = new Block("prevhash", tx);
        assertNotNull(block.getHash());
        assertEquals(block.getHash(), block.calculateHash());
    }

    @Test
    public void testInvalidSignatureShouldNotAddBlock() throws Exception {
        Wallet wallet1 = new Wallet();
        Wallet wallet2 = new Wallet();

        Transaction tx = new Transaction(50, wallet1.getPublicKey().toString(), wallet2.getPublicKey().toString());

        // Create invalid signature with wrong private key
        Wallet anotherWallet = new Wallet();
        Signature sig = Signature.getInstance("SHA256withRSA");
        sig.initSign(anotherWallet.getPrivateKey());
        sig.update(tx.toString().getBytes(StandardCharsets.UTF_8));
        byte[] fakeSignature = sig.sign();

        int beforeAdd = BlockChain.getBlocks().size();
        BlockChain.addBlock(tx, wallet1.getPublicKey(), fakeSignature);
        int afterAdd = BlockChain.getBlocks().size();

        assertEquals(beforeAdd, afterAdd);
    }
}
