import byteassistencia.exception.PagamentoInsuficienteException;
import byteassistencia.model.*;
import byteassistencia.repository.*;
import byteassistencia.service.OrdemDeServicoService;
import byteassistencia.exception.DadosInvalidosException;
import byteassistencia.exception.ClienteNaoEncontradoException;
import byteassistencia.service.PagamentoService;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // instanciar repos e service
        ClienteRepository clienteRepo = new ClienteRepository();
        TecnicoRepository tecnicoRepo = new TecnicoRepository();
        AparelhoRepository aparelhoRepo = new AparelhoRepository();
        OrdemDeServicoRepository osRepo = new OrdemDeServicoRepository();
        PagamentoRepository pagRepo = new PagamentoRepository();
        OrdemDeServicoService ordemDeServicoService = new OrdemDeServicoService(clienteRepo, tecnicoRepo, aparelhoRepo, osRepo);
        PagamentoService pagamentoService = new PagamentoService(osRepo, pagRepo);


        Scanner sc = new Scanner(System.in);

        boolean rodando = true;
        while (rodando) {
            System.out.println("=== Byte Assistência ===");
            System.out.println("1 - Cadastrar cliente");
            System.out.println("2 - Cadastrar técnico");
            System.out.println("3 - Cadastrar aparelho");
            System.out.println("4 - Abrir ordem de serviço");
            System.out.println("5 - Adicionar item à OS");
            System.out.println("6 - Registrar pagamento");
            System.out.println("7 - Detalhar OS");
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

                    Cliente clienteDono = clienteRepo.buscarPorId(idClienteAparelho);
                    if (clienteDono == null) {
                        System.out.println("Cliente não encontrado. Aparelho NÃO cadastrado.");
                        break;
                    }


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
                case 4:
                    try {
                        System.out.print("ID do cliente: ");
                        Long idCliente = sc.nextLong();

                        System.out.print("ID do aparelho: ");
                        Long idAparelho = sc.nextLong();

                        System.out.print("ID do técnico: ");
                        Long idTecnico = sc.nextLong();
                        sc.nextLine(); // consumir quebra de linha

                        System.out.print("Descrição do problema: ");
                        String descricao = sc.nextLine();

                        OrdemDeServico os = ordemDeServicoService.abrirOrdemDeServico(
                                idCliente, idAparelho, idTecnico, descricao
                        );

                        System.out.println("OS aberta com ID: " + os.getIdOS());
                    } catch (ClienteNaoEncontradoException e) {
                        System.out.println("Erro: " + e.getMessage());
                    } catch (DadosInvalidosException e) {
                        System.out.println("Erro de dados: " + e.getMessage());
                    }
                    break;
                case 5:
                    try {
                        System.out.print("ID da OS: ");
                        Long idOs = sc.nextLong();
                        sc.nextLine(); // consumir quebra de linha

                        // Buscar a OS no repositório
                        OrdemDeServico ordem = osRepo.buscarPorId(idOs);
                        if (ordem == null) {
                            System.out.println("OS não encontrada.");
                            break;
                        }

                        System.out.print("Descrição do serviço: ");
                        String descServico = sc.nextLine();

                        System.out.print("Quantidade de horas: ");
                        double horas = sc.nextDouble();

                        System.out.print("Valor por hora: ");
                        double valorHora = sc.nextDouble();
                        sc.nextLine(); // consumir quebra de linha

                        Servico servico = new Servico(descServico, horas, valorHora);

                        ordemDeServicoService.adicionarItemOrdem(ordem, servico);

                        System.out.println("Serviço adicionado com sucesso à OS " + idOs);
                    } catch (DadosInvalidosException e) {
                        System.out.println("Erro de dados: " + e.getMessage());
                    }
                    break;
                case 6:
                    try {
                        System.out.print("ID da OS: ");
                        Long idOs = sc.nextLong();
                        sc.nextLine(); // consumir quebra de linha

                        System.out.println("Forma de pagamento: 1-Dinheiro, 2-Cartão, 3-Pix");
                        System.out.print("Opção: ");
                        int opcaoForma = sc.nextInt();
                        sc.nextLine(); // consumir quebra de linha

                        String forma = null;

                        switch (opcaoForma) {
                            case 1:
                                forma = "DINHEIRO";
                                break;
                            case 2:
                                forma = "CARTAO";
                                break;
                            case 3:
                                forma = "PIX";
                                break;
                            default:
                                System.out.println("Opção de forma de pagamento inválida.");
                                return; // sai do case 6 sem tentar registrar
                        }

                        System.out.print("Valor do pagamento: ");
                        double valor = sc.nextDouble();
                        sc.nextLine(); // consumir quebra de linha

                        pagamentoService.registrarPagamento(idOs, forma, valor);
                        System.out.println("Pagamento registrado com sucesso para OS " + idOs);
                    } catch (DadosInvalidosException e) {
                        System.out.println("Erro de dados: " + e.getMessage());
                    } catch (PagamentoInsuficienteException e) {
                        System.out.println("Erro de pagamento: " + e.getMessage());
                    }
                    break;

                case 7:
                    System.out.print("ID da OS: ");
                    Long idOsDetalhe = sc.nextLong();
                    sc.nextLine(); // consumir quebra de linha

                    OrdemDeServico osDetalhe = osRepo.buscarPorId(idOsDetalhe);
                    if (osDetalhe == null) {
                        System.out.println("OS não encontrada.");
                        break;
                    }

                    System.out.println("=== Detalhes da OS " + osDetalhe.getIdOS() + " ===");
                    System.out.println("Descrição do problema: " + osDetalhe.getDescricaoProblema());
                    System.out.println("ID do cliente: " + osDetalhe.getIdCliente());
                    System.out.println("ID do aparelho: " + osDetalhe.getIdAparelho());
                    System.out.println("ID do técnico: " + osDetalhe.getIdTecnico());

                    System.out.println("\nItens cobrados:");
                    if (osDetalhe.getItens() == null || osDetalhe.getItens().isEmpty()) {
                        System.out.println("Nenhum item adicionado ainda.");
                    } else {
                        int i = 1;
                        for (ItemCobravel item : osDetalhe.getItens()) {
                            if (item instanceof Servico) {
                                Servico s = (Servico) item;
                                System.out.println(i + ") Serviço: " + s.getDescricao()
                                        + " | Horas: " + s.getHoras()
                                        + " | Valor/hora: " + s.getValorHora()
                                        + " | Valor: " + s.calcularValor());
                            } else if (item instanceof Produto) {
                                Produto p = (Produto) item;
                                System.out.println(i + ") Produto: " + p.getDescricao()
                                        + " | Qtde: " + p.getQuantidade()
                                        + " | Valor unitário: " + p.getValorUnitario()
                                        + " | Valor: " + p.calcularValor());
                            } else {
                                System.out.println(i + ") Item: valor = " + item.calcularValor());
                            }
                            i++;
                        }
                    }

                    System.out.println("\nValor total da OS: " + osDetalhe.calcularValorTotal());
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
