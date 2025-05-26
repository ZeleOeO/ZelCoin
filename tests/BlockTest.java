import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BlockTest {

    @Test
    public void testHashIsConsistent() {
        Transaction tx = new Transaction(25, "Alice", "Bob");
        Block block = new Block("prevHash", tx);

        String firstHash = block.calculateHash();
        String secondHash = block.calculateHash();

        assertNotNull(firstHash);
        assertEquals(firstHash, secondHash);
    }

    @Test
    public void testGetters() {
        Transaction tx = new Transaction(10, "sender", "receiver");
        Block block = new Block("prevHash", tx);

        assertEquals("prevHash", block.getPrevHash());
        assertEquals(tx, block.getTransaction());
        assertNotNull(block.getTimeStamp());
    }
}
