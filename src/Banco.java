
public class Banco {
    private List<Conta> contas;

    public Banco() {
        this.contas = new ArrayList<>();
    }

    public void adicionarConta(Conta conta) {
        contas.add(conta);
        System.out.println("Conta adicionada com sucesso.");
    }

    public Optional<Conta> buscarConta(String numero) {
        return contas.stream()
                .filter(c -> c.getNumero().equals(numero))
                .findFirst();
    }

    public void exibirContas() {
        if (contas.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada.");
            return;
        }
        System.out.println("--- Lista de Contas ---");
        contas.forEach(System.out::println);
        System.out.println("-----------------------");
    }
}
