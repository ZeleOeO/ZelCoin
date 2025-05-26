import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BlockTest {

    @Test
    public void testHashIsConsistent() {
        Wallet dummy = new Wallet();
        Wallet dummy2 = new Wallet();
        Transaction tx = new Transaction(25.0, dummy.getPublicKey(), dummy2.getPublicKey());
        Block block = new Block("prevHash", tx);

        String firstHash = block.calculateHash();
        String secondHash = block.calculateHash();

        assertNotNull(firstHash);
        assertEquals(firstHash, secondHash);
    }

    @Test
    public void testGetters() {
        Wallet dummy = new Wallet();
        Wallet dummy2 = new Wallet();
        Transaction tx = new Transaction(10.0, dummy.getPublicKey(), dummy2.getPublicKey());
        Block block = new Block("prevHash", tx);

        assertEquals("prevHash", block.getPrevHash());
        assertEquals(tx, block.getTransaction());
        assertNotNull(block.getTimeStamp());
    }
}