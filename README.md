# ZelCoin

A very naive and NoobChain implementation of a BlockChain

I just did the bare minimum of what a "blockchain" should do, as sited [here](https://builtin.com/blockchain/create-your-own-blockchain).


Which is like
- Block Creation
- Add data
- Block Hash
- Block "Chaining" lol

## Technologies
- Java 21+
- **Optional** - An IDE such as IntelliJ, VSCode, Netbeans 

## Installation (Will work on both powershell and bash/zsh terminals)
1. Clone the repository:
   ```bash
   git clone https://github.com/ZeleOeO/ZelCoin.git
   ```
2. Navigate to the project directory:
   ```bash
   cd ZelCoin
   ```

## Usage
- Run the project on command line
   ```bash
    javac src/Main.java
    java src/Main
    ```
  
## Features

- Wallet Creation
- Transactions
- Transaction Validation Using Signatures
- Keep Track of Balance
- Block Mining

## Running Tests
To run tests run:
```bash
javac -d . tests/**/*.java
```

## File Structure
```shell
ZelCoin/
├───.idea/
├───src/
│   │   Main.java
│   └entities/
├───tests
└─── README.md
```

## Steps to Contribute
Contributions are more than welcome as I've abandoned this project (I don't care about it lk)
1. Open an issue first so I can like keep track, but if that's too much stress that's fine too
2. Fork the Repository
3. Clone your fork
4. Create a new branch
   ```bash
   git checkout -b your-branch-name
   ```
5. Make your change
6. Commit your change, please use [Conventional Commits](https://gist.github.com/qoomon/5dfcdf8eec66a051ecd85625518cfd13) if you can, I didn't really use it here tbh
7. Push your change
8. Make a pull request and reference your issue <br>
   Please stick to conventional methods of programming java and springboot applications, don't mess up my already spaghetti code

## License
[MIT](https://choosealicense.com/licenses/mit/)
