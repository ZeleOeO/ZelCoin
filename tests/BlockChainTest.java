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

        Wallet dummy = new Wallet();
        Transaction genesis = new Transaction(0.0, dummy.getPublicKey(), dummy.getPublicKey());
        BlockChain.getBlocks().add(new Block("0", genesis));
    }

    @Test
    public void testAddBlockValidTransaction() {
        Wallet sender = new Wallet();
        Wallet receiver = new Wallet();

        Ledger.credit(sender.getPublicKey(), 300.0);

        int initialSize = BlockChain.getBlocks().size();
        sender.sendMoney(100.0, receiver.getPublicKey());

        List<Block> blocks = BlockChain.getBlocks();
        assertEquals(initialSize + 1, blocks.size());

        Block newBlock = blocks.getLast();
        assertEquals(receiver.getPublicKey(), newBlock.getTransaction().receiver());
        assertEquals(sender.getPublicKey(), newBlock.getTransaction().sender());
        assertEquals(100.0, newBlock.getTransaction().amount());
        assertEquals(200.0, Ledger.getBalance(sender.getPublicKey()));
        assertEquals(100.0, Ledger.getBalance(receiver.getPublicKey()));
    }

    @Test
    public void testInvalidSignatureShouldNotAddBlock() throws Exception {
        Wallet wallet1 = new Wallet();
        Wallet wallet2 = new Wallet();

        Transaction tx = new Transaction(50.0, wallet1.getPublicKey(), wallet2.getPublicKey());

        Wallet fakeSigner = new Wallet();
        var sign = Signature.getInstance("SHA256withRSA");
        sign.initSign(fakeSigner.getPrivateKey());
        sign.update(tx.toString().getBytes(StandardCharsets.UTF_8));
        byte[] badSig = sign.sign();

        int before = BlockChain.getBlocks().size();
        BlockChain.addBlock(tx, wallet1.getPublicKey(), badSig);
        int after = BlockChain.getBlocks().size();

        assertEquals(before, after);
    }
}
