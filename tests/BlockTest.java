import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BlockTest {
    Block block;

    @Test
    void createBlock() {
        block = new Block("0", "Hello");
        System.out.println(block.getHash());
        assertEquals("0", block.getPrevHash());
        assertEquals("Hello", block.getData());
    }
}