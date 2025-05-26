import org.junit.jupiter.api.Test;

import java.security.KeyPair;

import static org.junit.jupiter.api.Assertions.*;

public class WalletTest {

    @Test
    public void testWalletKeyGeneration() {
        Wallet wallet = new Wallet();
        assertNotNull(wallet.getPublicKey());
        assertNotNull(wallet.getPrivateKey());
    }

    @Test
    public void testSendMoneyAddsBlock() {
        BlockChain.getBlocks().clear();
        Transaction genesis = new Transaction(0, "GENESIS", "GENESIS");
        BlockChain.getBlocks().add(new Block("0", genesis));

        Wallet sender = new Wallet();
        Wallet receiver = new Wallet();

        int sizeBefore = BlockChain.getBlocks().size();
        sender.sendMoney(20, receiver.getPublicKey().toString());
        int sizeAfter = BlockChain.getBlocks().size();

        assertEquals(sizeBefore + 1, sizeAfter);
    }
}
