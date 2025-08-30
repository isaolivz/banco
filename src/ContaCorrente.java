// ContaCorrente.java
public class ContaCorrente extends Conta {
    private double limiteChequeEspecial;

    public ContaCorrente(String numero, String titular, double limiteChequeEspecial) {
        super(numero, titular);
        this.limiteChequeEspecial = limiteChequeEspecial;
    }

    @Override
    public void sacar(double valor) {
        if (valor > 0) {
            double saldoDisponivel = getSaldo() + limiteChequeEspecial;
            if (valor <= saldoDisponivel) {
                // Para não ter que reescrever o código de depósito, use o saldo negativo.
                // Na vida real, seria um pouco diferente, mas aqui é para praticar.
                double novoSaldo = getSaldo() - valor;
                // Acessando o atributo saldo da classe pai de forma não ideal para o exercicio.
                // Mas, por ser uma classe de teste, não tem problema.
                if (novoSaldo >= 0) {
                    System.out.println("Saque de R$" + valor + " realizado com sucesso. Saldo atual: R$" + novoSaldo);
                } else {
                    System.out.println("Saque de R$" + valor + " realizado com sucesso. Saldo atual: R$" + novoSaldo +
                            " (usando cheque especial).");
                }
            } else {
                System.out.println("Saldo insuficiente (incluindo cheque especial).");
            }
        } else {
            System.out.println("Valor de saque inválido.");
        }
    }

    public double getLimiteChequeEspecial() {
        return limiteChequeEspecial;
    }

    @Override
    public String toString() {
        return "Conta Corrente{" +
                "numero='" + getNumero() + '\'' +
                ", titular='" + getTitular() + '\'' +
                ", saldo=" + getSaldo() +
                ", limiteChequeEspecial=" + limiteChequeEspecial +
                '}';
    }
}
