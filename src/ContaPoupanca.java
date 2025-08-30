// ContaPoupanca.java
public class ContaPoupanca extends Conta {
    private static final double TAXA_RENDIMENTO = 0.05; // 5% de rendimento

    public ContaPoupanca(String numero, String titular) {
        super(numero, titular);
    }

    @Override
    public void sacar(double valor) {
        if (valor > 0) {
            if (valor <= getSaldo()) {
                double novoSaldo = getSaldo() - valor;
                // Da mesma forma que no ContaCorrente, esse acesso direto não seria o ideal.
                System.out.println("Saque de R$" + valor + " realizado com sucesso. Saldo atual: R$" + novoSaldo);
            } else {
                System.out.println("Saldo insuficiente.");
            }
        } else {
            System.out.println("Valor de saque inválido.");
        }
    }

    public void aplicarRendimento() {
        double rendimento = getSaldo() * TAXA_RENDIMENTO;
        depositar(rendimento);
        System.out.println("Rendimento de R$" + rendimento + " aplicado com sucesso.");
    }

    @Override
    public String toString() {
        return "Conta Poupanca{" +
                "numero='" + getNumero() + '\'' +
                ", titular='" + getTitular() + '\'' +
                ", saldo=" + getSaldo() +
                '}';
    }
}
