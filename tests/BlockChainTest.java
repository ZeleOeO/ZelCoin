import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BlockChainTest {

    @Test
    public void testGenesisBlock() {
        List<Block> blocks = BlockChain.getBlocks();
        assertEquals(1, blocks.size(), "Blockchain should always start with Genesis block");
        assertEquals("Genesis", blocks.getFirst().getData(), "First block should have data 'Genesis'");
    }

    @Test
    public void testAddBlock() {
        int initialSize = BlockChain.getBlocks().size();
        BlockChain.addBlock("Block 1 Data");
        BlockChain.addBlock("Block 2 Data");

        List<Block> blocks = BlockChain.getBlocks();

        assertEquals(initialSize + 2, blocks.size(), "Blockchain size should increase after adding blocks");

        Block lastBlock = blocks.getLast();
        Block secondLastBlock = blocks.get(blocks.size() - 2);

        assertEquals("Block 2 Data", lastBlock.getData());
        assertEquals(secondLastBlock.getHash(), lastBlock.getPrevHash(), "Previous hash should match the hash of the previous block");
    }
}
