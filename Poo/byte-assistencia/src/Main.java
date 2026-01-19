import byteassistencia.model.Aparelho;
import byteassistencia.model.Cliente;
import byteassistencia.model.Tecnico;
import byteassistencia.repository.*;
import byteassistencia.service.OrdemDeServicoService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // instanciar repos e service
        ClienteRepository clienteRepo = new ClienteRepository();
        TecnicoRepository tecnicoRepo = new TecnicoRepository();
        AparelhoRepository aparelhoRepo = new AparelhoRepository();
        OrdemDeServicoRepository osRepo = new OrdemDeServicoRepository();
        PagamentoRepository pagRepo = new PagamentoRepository();
        OrdemDeServicoService osService = new OrdemDeServicoService();

        Scanner sc = new Scanner(System.in);

        boolean rodando = true;
        while (rodando) {
            System.out.println("=== Byte Assistência ===");
            System.out.println("1 - Cadastrar cliente");
            System.out.println("2 - Cadastrar técnico");
            System.out.println("3 - Cadastrar aparelho");
            System.out.println("0 - Sair");

            System.out.print("Escolha: ");
            int opcao = sc.nextInt();
            sc.nextLine(); // consumir quebra de linha

            switch (opcao) {
                case 1:
                    System.out.print("Nome do cliente: ");
                    String nome = sc.nextLine();

                    System.out.print("CPF do cliente: ");
                    String cpf = sc.nextLine();

                    System.out.print("Telefone do cliente: ");
                    String telefone = sc.nextLine();

                    // id começa null, o repositório gera
                    Cliente cliente = new Cliente(null, nome, cpf, telefone);

                    clienteRepo.salvar(cliente);
                    System.out.println("Cliente cadastrado com ID: " + cliente.getId());
                    break;
                case 2:
                    System.out.print("Nome do técnico: ");
                    String nomeTec = sc.nextLine();

                    System.out.print("CPF do técnico: ");
                    String cpfTec = sc.nextLine();

                    System.out.print("Especialidade: ");
                    String esp = sc.nextLine();

                    Tecnico tecnico = new Tecnico(null, nomeTec, cpfTec, esp);
                    tecnicoRepo.salvar(tecnico);
                    System.out.println("Técnico cadastrado com ID: " + tecnico.getId());
                    break;
                case 3:
                    System.out.print("ID do cliente dono do aparelho: ");
                    Long idClienteAparelho = sc.nextLong();
                    sc.nextLine(); // consumir quebra de linha

                    System.out.print("Tipo do aparelho: ");
                    String tipo = sc.nextLine();

                    System.out.print("Marca: ");
                    String marca = sc.nextLine();

                    System.out.print("Modelo: ");
                    String modelo = sc.nextLine();

                    System.out.print("Número de série: ");
                    String numeroSerie = sc.nextLine();

                    Aparelho aparelho = new Aparelho(
                            null,             // idAparelho será gerado pelo repositório
                            idClienteAparelho,
                            tipo,
                            marca,
                            modelo,
                            numeroSerie
                    );

                    aparelhoRepo.salvar(aparelho);
                    System.out.println("Aparelho cadastrado com ID: " + aparelho.getIdAparelho());
                    break;


                case 0:
                    rodando = false;
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }

        sc.close();
    }
}
