import byteassistencia.exception.PagamentoInsuficienteException;
import byteassistencia.model.*;
import byteassistencia.repository.*;
import byteassistencia.service.OrdemDeServicoService;
import byteassistencia.exception.DadosInvalidosException;
import byteassistencia.exception.ClienteNaoEncontradoException;
import byteassistencia.service.PagamentoService;
import byteassistencia.exception.CPFDuplicadoException;
import byteassistencia.exception.OSFechadaException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static boolean validarFormatoCPF(String cpf) {
        if (cpf == null) return false;
        String cpfLimpo = cpf.replaceAll("[^0-9]", "");
        return cpfLimpo.length() == 11;
    }

    private static boolean validarCampoVazio(String campo) {
        return campo == null || campo.trim().isEmpty();
    }

    private static boolean validarId(Long id) {
        return id != null && id > 0;
    }


    public static void main(String[] args) {

        ClienteRepository clienteRepo = new ClienteRepository();
        TecnicoRepository tecnicoRepo = new TecnicoRepository();
        AparelhoRepository aparelhoRepo = new AparelhoRepository();
        OrdemDeServicoRepository osRepo = new OrdemDeServicoRepository();
        PagamentoRepository pagRepo = new PagamentoRepository();
        OrdemDeServicoService ordemDeServicoService = new OrdemDeServicoService(clienteRepo, tecnicoRepo, aparelhoRepo, osRepo);
        PagamentoService pagamentoService = new PagamentoService(osRepo, pagRepo, clienteRepo);



        Scanner sc = new Scanner(System.in);

        boolean rodando = true;
        while (rodando) {
            try {
                System.out.println("\n=== Byte Assistência ===");
                System.out.println("1 - Cadastrar cliente");
                System.out.println("2 - Cadastrar técnico");
                System.out.println("3 - Cadastrar aparelho");
                System.out.println("4 - Abrir ordem de serviço");
                System.out.println("5 - Adicionar item à OS");
                System.out.println("6 - Registrar pagamento");
                System.out.println("7 - Detalhar OS");
                System.out.println("0 - Sair");
                System.out.println("----------------------------");
                System.out.print("Escolha: ");

                int opcao = sc.nextInt();
                sc.nextLine();

            switch (opcao) {
                case 1:
                    try {
                        System.out.print("Nome do cliente: ");
                        String nome = sc.nextLine();
                        if (validarCampoVazio(nome)) {
                            System.out.println("Erro: Nome não pode ser vazio!");
                            break;
                        }

                        System.out.print("CPF do cliente: ");
                        String cpf = sc.nextLine();
                        if (validarCampoVazio(cpf)) {
                            System.out.println("Erro: CPF não pode ser vazio!");
                            break;
                        }
                        if (!validarFormatoCPF(cpf)) {
                            System.out.println("Erro: CPF deve ter 11 dígitos!");
                            break;
                        }


                        if (clienteRepo.buscarPorCPF(cpf) != null) {
                            System.out.println("Erro: CPF já cadastrado!");
                            break;
                        }

                        System.out.print("Telefone do cliente: ");
                        String telefone = sc.nextLine();
                        if (validarCampoVazio(telefone)) {
                            System.out.println("Erro: Telefone não pode ser vazio!");
                            break;
                        }

                        Cliente cliente = new Cliente(null, nome, cpf, telefone);
                        clienteRepo.salvar(cliente);
                        System.out.println("✓ Cliente cadastrado com ID: " + cliente.getId());

                    } catch (CPFDuplicadoException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;

                case 2:
                    try {
                        System.out.print("Nome do técnico: ");
                        String nomeTec = sc.nextLine();
                        if (validarCampoVazio(nomeTec)) {
                            System.out.println("Erro: Nome não pode ser vazio!");
                            break;
                        }

                        System.out.print("CPF do técnico: ");
                        String cpfTec = sc.nextLine();
                        if (validarCampoVazio(cpfTec)) {
                            System.out.println("Erro: CPF não pode ser vazio!");
                            break;
                        }
                        if (!validarFormatoCPF(cpfTec)) {
                            System.out.println("Erro: CPF deve ter 11 dígitos!");
                            break;
                        }


                        if (tecnicoRepo.buscarPorCPF(cpfTec) != null) {
                            System.out.println("Erro: CPF já cadastrado!");
                            break;
                        }

                        System.out.print("Especialidade: ");
                        String esp = sc.nextLine();
                        if (validarCampoVazio(esp)) {
                            System.out.println("Erro: Especialidade não pode ser vazia!");
                            break;
                        }

                        Tecnico tecnico = new Tecnico(null, nomeTec, cpfTec, esp);
                        tecnicoRepo.salvar(tecnico);
                        System.out.println("✓ Técnico cadastrado com ID: " + tecnico.getId());

                    } catch (CPFDuplicadoException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;


                case 3:
                    try {
                        System.out.print("ID do cliente dono do aparelho: ");
                        Long idClienteAparelho = sc.nextLong();
                        sc.nextLine();

                        if (!validarId(idClienteAparelho)) {
                            System.out.println("Erro: ID do cliente inválido!");
                            break;
                        }

                        Cliente clienteDono = clienteRepo.buscarPorId(idClienteAparelho);
                        if (clienteDono == null) {
                            System.out.println("Erro: Cliente não encontrado. Aparelho NÃO cadastrado.");
                            break;
                        }

                        System.out.print("Tipo do aparelho: ");
                        String tipo = sc.nextLine();
                        if (validarCampoVazio(tipo)) {
                            System.out.println("Erro: Tipo não pode ser vazio!");
                            break;
                        }

                        System.out.print("Marca: ");
                        String marca = sc.nextLine();
                        if (validarCampoVazio(marca)) {
                            System.out.println("Erro: Marca não pode ser vazia!");
                            break;
                        }

                        System.out.print("Modelo: ");
                        String modelo = sc.nextLine();
                        if (validarCampoVazio(modelo)) {
                            System.out.println("Erro: Modelo não pode ser vazio!");
                            break;
                        }

                        System.out.print("Número de série: ");
                        String numeroSerie = sc.nextLine();
                        if (validarCampoVazio(numeroSerie)) {
                            System.out.println("Erro: Número de série não pode ser vazio!");
                            break;
                        }

                        Aparelho aparelho = new Aparelho(
                                null,
                                idClienteAparelho,
                                tipo,
                                marca,
                                modelo,
                                numeroSerie
                        );
                        aparelhoRepo.salvar(aparelho);
                        System.out.println("✓ Aparelho cadastrado com ID: " + aparelho.getIdAparelho());

                    } catch (InputMismatchException e) {
                        System.out.println("Erro: Digite apenas números para o ID!");
                        sc.nextLine();
                    }
                    break;

                case 4:
                    try {
                        System.out.print("ID do cliente: ");
                        Long idCliente = sc.nextLong();

                        System.out.print("ID do aparelho: ");
                        Long idAparelho = sc.nextLong();

                        System.out.print("ID do técnico: ");
                        Long idTecnico = sc.nextLong();
                        sc.nextLine();

                        if (!validarId(idCliente)) {
                            System.out.println("Erro: ID do cliente inválido!");
                            break;
                        }
                        if (!validarId(idAparelho)) {
                            System.out.println("Erro: ID do aparelho inválido!");
                            break;
                        }
                        if (!validarId(idTecnico)) {
                            System.out.println("Erro: ID do técnico inválido!");
                            break;
                        }

                        System.out.print("Descrição do problema: ");
                        String descricao = sc.nextLine();

                        OrdemDeServico os = ordemDeServicoService.abrirOrdemDeServico(
                                idCliente, idAparelho, idTecnico, descricao
                        );
                        System.out.println("✓ OS aberta com ID: " + os.getIdOS());

                    } catch (ClienteNaoEncontradoException e) {
                        System.out.println("Erro: " + e.getMessage());
                    } catch (DadosInvalidosException e) {
                        System.out.println("Erro de dados: " + e.getMessage());
                    } catch (InputMismatchException e) {
                        System.out.println("Erro: Digite apenas números para os IDs!");
                        sc.nextLine();
                    }
                    break;

                case 5:
                    try {
                        System.out.print("ID da OS: ");
                        Long idOs = sc.nextLong();
                        sc.nextLine();

                        if (!validarId(idOs)) {
                            System.out.println("Erro: ID da OS inválido!");
                            break;
                        }

                        OrdemDeServico ordem = osRepo.buscarPorId(idOs);
                        if (ordem == null) {
                            System.out.println("Erro: OS não encontrada.");
                            break;
                        }

                        System.out.print("Descrição do serviço: ");
                        String descServico = sc.nextLine();
                        if (validarCampoVazio(descServico)) {
                            System.out.println("Erro: Descrição não pode ser vazia!");
                            break;
                        }

                        System.out.print("Quantidade de horas: ");
                        double horas = sc.nextDouble();
                        if (horas <= 0) {
                            System.out.println("Erro: Quantidade de horas deve ser positiva!");
                            sc.nextLine();
                            break;
                        }

                        System.out.print("Valor por hora: ");
                        double valorHora = sc.nextDouble();
                        sc.nextLine();
                        if (valorHora <= 0) {
                            System.out.println("Erro: Valor por hora deve ser positivo!");
                            break;
                        }

                        Servico servico = new Servico(descServico, horas, valorHora);
                        ordemDeServicoService.adicionarItemOrdem(ordem, servico);
                        System.out.println("✓ Serviço adicionado com sucesso à OS " + idOs);

                    } catch (DadosInvalidosException e) {
                        System.out.println("Erro de dados: " + e.getMessage());
                    } catch (OSFechadaException e) {
                        System.out.println("Erro: " + e.getMessage());
                    } catch (InputMismatchException e) {
                        System.out.println("Erro: Digite valores numéricos válidos!");
                        sc.nextLine();
                    }
                    break;

                case 6:
                    try {
                        System.out.print("ID da OS: ");
                        Long idOs = sc.nextLong();
                        sc.nextLine();

                        if (!validarId(idOs)) {
                            System.out.println("Erro: ID da OS inválido!");
                            break;
                        }


                        OrdemDeServico osParaPagamento = osRepo.buscarPorId(idOs);
                        if (osParaPagamento == null) {
                            System.out.println("Erro: OS não encontrada com ID " + idOs);
                            break;
                        }

                        System.out.println("Forma de pagamento: 1-Dinheiro, 2-Cartão, 3-Pix");
                        System.out.print("Opção: ");
                        int opcaoForma = sc.nextInt();
                        sc.nextLine();

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
                                break;
                        }

                        if (forma == null) {
                            break;
                        }

                        System.out.print("Valor do pagamento: ");
                        double valor = sc.nextDouble();
                        sc.nextLine();

                        pagamentoService.registrarPagamento(idOs, forma, valor);
                        System.out.println("✓ Pagamento registrado com sucesso para OS " + idOs);

                    } catch (DadosInvalidosException e) {
                        System.out.println("Erro de dados: " + e.getMessage());
                    } catch (PagamentoInsuficienteException e) {
                        System.out.println("Erro de pagamento: " + e.getMessage());
                    } catch (ClienteNaoEncontradoException e) {
                        System.out.println("Erro: " + e.getMessage());
                    } catch (OSFechadaException e) {
                        System.out.println("Erro: " + e.getMessage());
                    } catch (InputMismatchException e) {
                        System.out.println("Erro: Digite valores numéricos válidos!");
                        sc.nextLine();
                    }
                    break;



                case 7:
                    try {
                        System.out.print("ID da OS: ");
                        Long idOsDetalhe = sc.nextLong();
                        sc.nextLine();

                        OrdemDeServico osDetalhe = osRepo.buscarPorId(idOsDetalhe);
                        if (osDetalhe == null) {
                            System.out.println("Erro: OS não encontrada.");
                            break;
                        }

                        Cliente clienteOS = clienteRepo.buscarPorId(osDetalhe.getIdCliente());
                        Aparelho aparelhoOS = aparelhoRepo.buscarPorId(osDetalhe.getIdAparelho());
                        Tecnico tecnicoOS = tecnicoRepo.buscarPorId(osDetalhe.getIdTecnico());

                        System.out.println("\n=== Detalhes da OS " + osDetalhe.getIdOS() + " ===");

                        System.out.println("Status: " + osDetalhe.getStatus());
                        System.out.println("Data de Abertura: " + osDetalhe.getDataAbertura());
                        System.out.println("Descrição do problema: " + osDetalhe.getDescricaoProblema());

                        System.out.println("\n--- CLIENTE ---");
                        if (clienteOS != null) {
                            System.out.println("Nome: " + clienteOS.getNome());
                            System.out.println("CPF: " + clienteOS.getCpf());
                            System.out.println("Telefone: " + clienteOS.getTelefone());
                        } else {
                            System.out.println("ID do cliente: " + osDetalhe.getIdCliente());
                        }

                        System.out.println("\n--- APARELHO ---");
                        if (aparelhoOS != null) {
                            System.out.println("Tipo: " + aparelhoOS.getTipo());
                            System.out.println("Marca: " + aparelhoOS.getMarca());
                            System.out.println("Modelo: " + aparelhoOS.getModelo());
                            System.out.println("Número de série: " + aparelhoOS.getNumeroDeSerie());
                        } else {
                            System.out.println("ID do aparelho: " + osDetalhe.getIdAparelho());
                        }

                        System.out.println("\n--- TÉCNICO ---");
                        if (tecnicoOS != null) {
                            System.out.println("Nome: " + tecnicoOS.getNome());
                            System.out.println("Especialidade: " + tecnicoOS.getEspecialidade());
                        } else {
                            System.out.println("ID do técnico: " + osDetalhe.getIdTecnico());
                        }

                        System.out.println("\n--- ITENS COBRADOS ---");
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

                    } catch (InputMismatchException e) {
                        System.out.println("Erro: Digite apenas números para o ID!");
                        sc.nextLine();
                    }
                    break;



                case 0:
                    rodando = false;
                    break;
                default:
                    System.out.println("Opção inválida");
            }  // Fecha o switch

            } catch (InputMismatchException e) {
                System.out.println("Erro: Digite apenas números válidos no menu!");
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
                e.printStackTrace();
            }
        }

        sc.close();
    }
}

