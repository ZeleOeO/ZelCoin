import java.util.ArrayList;
import java.util.List;

public class BlockChain {
    private static List<Block> blocks = new ArrayList<Block>(
            new ArrayList<>(List.of(new Block("0", "Genesis")))
    );

    private BlockChain() {
    }

    public static void addBlock(String data) {
        String previousHash = blocks.getLast().getHash();
        Block block = new Block(previousHash, data);
        blocks.add(block);
    }

    public static List<Block> getBlocks() {
        return blocks;
    }

}
