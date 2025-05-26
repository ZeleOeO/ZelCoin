import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WalletTest {

    @Test
    public void testSendMoneyAddsBlock() {
        BlockChain.getBlocks().clear();
        Wallet dummy = new Wallet();
        BlockChain.getBlocks().add(new Block("0", new Transaction(0.0, dummy.getPublicKey(), dummy.getPublicKey())));

        Wallet sender = new Wallet();
        Wallet receiver = new Wallet();

        int before = BlockChain.getBlocks().size();
        sender.sendMoney(75.0, receiver.getPublicKey());
        int after = BlockChain.getBlocks().size();

        assertEquals(before + 1, after);

        Block block = BlockChain.getBlocks().getLast();
        assertEquals(75, block.getTransaction().amount());
        assertEquals(sender.getPublicKey(), block.getTransaction().sender());
        assertEquals(receiver.getPublicKey(), block.getTransaction().receiver());
    }
}
