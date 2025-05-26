import entities.BlockChain;
import entities.Ledger;
import entities.Wallet;

public class Main {
    public static void main(String[] args) {
        Wallet ore = new Wallet();
        Wallet merci = new Wallet();
        Wallet alice = new Wallet();

        Ledger.credit(ore.getPublicKey(), 1000.0);
        ore.sendMoney(200.0, merci.getPublicKey());
        ore.sendMoney(200.0, alice.getPublicKey());
        // Should log an insufficient balance
        ore.sendMoney(900.0, merci.getPublicKey());

        BlockChain.print();
    }
}
