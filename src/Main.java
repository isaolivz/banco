// Main.java
import java.util.Scanner;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Banco banco = new Banco();

        while (true) {
            System.out.println("\n--- Menu do Banco ---");
            System.out.println("1. Criar nova conta");
            System.out.println("2. Depositar");
            System.out.println("3. Sacar");
            System.out.println("4. Verificar saldo");
            System.out.println("5. Aplicar rendimento (Poupanca)");
            System.out.println("6. Exibir todas as contas");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            switch (opcao) {
                case 1:
                    criarConta(scanner, banco);
                    break;
                case 2:
                    depositar(scanner, banco);
                    break;
                case 3:
                    sacar(scanner, banco);
                    break;
                case 4:
                    verificarSaldo(scanner, banco);
                    break;
                case 5:
                    aplicarRendimento(scanner, banco);
                    break;
                case 6:
                    banco.exibirContas();
                    break;
                case 7:
                    System.out.println("Obrigado por usar o nosso banco digital!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void criarConta(Scanner scanner, Banco banco) {
        System.out.print("Digite o número da conta: ");
        String numero = scanner.nextLine();
        System.out.print("Digite o nome do titular: ");
        String titular = scanner.nextLine();
        System.out.print("Tipo de conta (1 - Corrente, 2 - Poupanca): ");
        int tipo = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer

        if (tipo == 1) {
            System.out.print("Digite o limite do cheque especial: ");
            double limite = scanner.nextDouble();
            scanner.nextLine();
            banco.adicionarConta(new ContaCorrente(numero, titular, limite));
        } else if (tipo == 2) {
            banco.adicionarConta(new ContaPoupanca(numero, titular));
        } else {
            System.out.println("Tipo de conta inválido.");
        }
    }

    private static void depositar(Scanner scanner, Banco banco) {
        System.out.print("Digite o número da conta: ");
        String numero = scanner.nextLine();
        Optional<Conta> conta = banco.buscarConta(numero);

        if (conta.isPresent()) {
            System.out.print("Digite o valor para depósito: ");
            double valor = scanner.nextDouble();
            scanner.nextLine();
            conta.get().depositar(valor);
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    private static void sacar(Scanner scanner, Banco banco) {
        System.out.print("Digite o número da conta: ");
        String numero = scanner.nextLine();
        Optional<Conta> conta = banco.buscarConta(numero);

        if (conta.isPresent()) {
            System.out.print("Digite o valor para saque: ");
            double valor = scanner.nextDouble();
            scanner.nextLine();
            conta.get().sacar(valor);
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    private static void verificarSaldo(Scanner scanner, Banco banco) {
        System.out.print("Digite o número da conta: ");
        String numero = scanner.nextLine();
        Optional<Conta> conta = banco.buscarConta(numero);

        if (conta.isPresent()) {
            System.out.println("Saldo atual: R$" + conta.get().getSaldo());
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    private static void aplicarRendimento(Scanner scanner, Banco banco) {
        System.out.print("Digite o número da conta poupança: ");
        String numero = scanner.nextLine();
        Optional<Conta> conta = banco.buscarConta(numero);

        if (conta.isPresent()) {
            if (conta.get() instanceof ContaPoupanca) {
                ((ContaPoupanca) conta.get()).aplicarRendimento();
            } else {
                System.out.println("Esta não é uma conta poupança.");
            }
        } else {
            System.out.println("Conta não encontrada.");
        }
    }
}
