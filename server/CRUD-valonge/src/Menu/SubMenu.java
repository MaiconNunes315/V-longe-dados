package Menu;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import ClassesTabelas.Contato;
import ClassesTabelas.Destino;
import ClassesTabelas.Hospedagem;
import ClassesTabelas.Usuario;
import ClassesTabelas.Viagem;
import TabelasDAO.ContatoDAO;
import TabelasDAO.DestinoDAO;
import TabelasDAO.HospedagemDAO;
import TabelasDAO.UsuarioDAO;
import TabelasDAO.ViagemDAO;

public class SubMenu {

	// atributos do sub menun
	private Scanner inputStr = new Scanner(System.in);
	private Scanner inputNumber = new Scanner(System.in);
	private Usuario usuario = new Usuario();
	private Destino destino = new Destino();
	private Hospedagem hospedagem = new Hospedagem();
	private Viagem viagem = new Viagem();
	private Contato contato = new Contato();
	private UsuarioDAO usuarioDao = new UsuarioDAO();
	private DestinoDAO destinoDao = new DestinoDAO();
	private HospedagemDAO hospedagemDao = new HospedagemDAO();
	private ViagemDAO viagemDao = new ViagemDAO();
	private ContatoDAO contatoDao = new ContatoDAO();

	//metodo de inserir dados
	public void insert() {

		int subMenuNumber = 1;

		//sub menun de inserir dados
		while (subMenuNumber != 0) {
			System.out.println(" ____________________________________________________________");
			System.out.println("|                                                            |");
			System.out.println("|       Em qual tabela deseja inserir novos dados ?          |");
			System.out.println("|                [ 1 ] para Usuário                          |");
			System.out.println("|                [ 2 ] para Destino                          |");
			System.out.println("|                [ 3 ] para Hospedagem                       |");
			System.out.println("|                [ 4 ] para Viagem                           |");
			System.out.println("|                [ 5 ] para Contato                          |");
			System.out.println("|                [ 0 ] para Voltar ao menu anterior          |");
			System.out.println("|____________________________________________________________|");
			subMenuNumber = inputNumber.nextInt();
			switch (subMenuNumber) {
			case 0: {
				System.out.println("Vontando para o menu anterior");
				break;
			}
			//inseri dados do usuario
			case 1: {

				System.out.println("Digite o nome do usuário");
				String nome = (inputStr.nextLine());
				System.out.println("Digite o RG do usuário *somente numeros");
				String rg = inputStr.nextLine();
				System.out.println("Digite o endereço do usuário");
				String endereco = inputStr.nextLine();
				System.out.println("Digite o CPF do usuário *somente numeros");
				String cpf = inputStr.nextLine();
				String[] nascimento = { "Dia", "Mês", "Ano" };
				int[] data = { 0, 0, 0 };
				for (int i = 0; i < 3; i++) {

					System.out.println("Digite o " + nascimento[i] + " nascimento *somente numeros");
					data[i] = inputNumber.nextInt();
				}
				System.out.println("Digite o telefone do usuário com DDD *somente numeros ");
				String telefone = inputStr.nextLine();
				System.out.println("Digite o email do usuário");
				String email = inputStr.nextLine();
				System.out.println("Digite o Estado que reside o usuário");
				String estado = inputStr.nextLine();
				System.out.println("Digite a senha do usuário");
				String senha = inputStr.nextLine();
				System.out.println("Digite o tipo de usuário do usuário - Apenas user, admin, client");
				String user = inputStr.nextLine();

				if ("client".equalsIgnoreCase(user) || "user".equalsIgnoreCase(user)
						|| "admin".equalsIgnoreCase(user)) {
					usuario.setTipoUsuario(user);
				} else {
					System.out.println("tipo de usuário não existe será adicionado como client");
					usuario.setTipoUsuario("client");
				}

				usuario.setRg(rg);
				usuario.setNome(nome);
				usuario.setEndereco(endereco);
				usuario.setCpf(cpf);
				usuario.setDataNascimento(LocalDate.of(data[2], data[1], data[0]));
				usuario.setTelefone(telefone);
				usuario.setCriado_em(LocalDateTime.now());
				usuario.setEmail(email);
				usuario.setEstado(estado);
				usuario.setModificado_em(LocalDateTime.now());
				usuario.setSenha(senha);

				usuarioDao.creatUsuario(usuario);
				break;
			}
			// inserir dados do destino
			case 2: {

				System.out.println("Digite a cidade do destino");
				destino.setCidade(inputStr.nextLine());
				System.out.println("Digite detalhes do destino, *pontos turisticos,baladas... ");
				destino.setDetalhes(inputStr.nextLine());
				System.out.println("Digite o estado do destino");
				destino.setEstado(inputStr.nextLine());
				System.out.println("Um link com a imagem do destino");
				destino.setImg(inputStr.nextLine());
				System.out.println("Digite o pais do destino");
				destino.setPais(inputStr.nextLine());

				destinoDao.createDestino(destino);
				break;
			}
			//inserir dados de  hospedagem
			case 3: {

				System.out.println("Digite o endereço do local de hospedagem");
				hospedagem.setEndereco(inputStr.nextLine());
				System.out.println("Digite o nome do local, ex: Hotel Copacabana Palace");
				hospedagem.setNomeLocal(inputStr.nextLine());
				System.out.println("Digite o valor da diária ex. 153.90");
				hospedagem.setPrecoDiaria(inputNumber.useLocale(Locale.US).nextDouble());
				destinoDao.readDestino();
				System.out.println("Para localização correta da hospedagem escolha o id do destino listado.");
				destino.setId_destino(inputNumber.nextInt());
				hospedagem.setDestino(destino);
				hospedagemDao.createHospedagem(hospedagem);

				break;
			}
			// inserir daddos de hospedagem
			case 4: {

				String[] dataViagem = { "* Dia *", "* Mês *", "* Ano *", "* Hora *", "* Minuto *" };
				int[] data = { 0, 0, 0, 0, 0 };
				
				for (int i = 0; i < 5; i++) {

					System.out.println("Digite o " + dataViagem[i] + " embarque viagem *somente numeros");
					data[i] = inputNumber.nextInt();
				}

				viagem.setDataEntrada(LocalDateTime.of(data[2], data[1], data[0], data[3], data[4]));

				for (int i = 0; i < 5; i++) {

					System.out.println("Digite o " + dataViagem[i] + " desembarque viagem *somente numeros");
					data[i] = inputNumber.nextInt();
				}
				
				viagem.setDataSaida(LocalDateTime.of(data[2], data[1], data[0], data[3], data[4]));

				int qtDias = (int) viagem.getDataEntrada().until(viagem.getDataSaida(), ChronoUnit.DAYS);

				if (qtDias < 1) {
					System.out.println("*** data inválida o dia da sua viagem de ida \n "
							+ "é menor que o de volta, tente novamente ***");
					break;
				}

				System.out.println("Se possuir desconto digite o valor do desconto em numero inteiro"
						+ " ex: 10 para 10%, se não possuir digite 0");

				viagem.setDesconto(inputNumber.nextInt());
				System.out.println("Digite o valor dessa viagem");
				viagem.setPreco(inputNumber.useLocale(Locale.US).nextDouble());
				usuarioDao.readUsuarios();
				System.out.println("Qual id do usuário da viagem ?");
				usuario.setId(inputNumber.nextInt());
				destinoDao.readDestino();
				System.out.println("Qual id do destino da viagem do usuário ?");
				int idDestino = inputNumber.nextInt();
				System.out.println("Possui hospedagem ? digite 1 para sim 0 para não");
				int temHospedagem = inputNumber.nextInt();
				
				if(temHospedagem == 1 || temHospedagem == 0) {
					viagem.setPossuiHospedagem(temHospedagem);					
				}else {
					System.out.println("Valor inexistente, tente novamente");
				break;
				}
				
				if (temHospedagem == 1) {
					List<Hospedagem> hospedagemList = hospedagemDao.searchHospedagem_Destino(idDestino);

					if (hospedagemList.size() > 0) {

						for (Hospedagem hh : hospedagemList) {
							System.out.println("id: " + hh.getId() + ", nome: " + hh.getNomeLocal() + ", preço diária: "
									+ hh.getPrecoDiaria());
							
						}

						System.out.println("Digite o id da hospedagem");
						int idHospedagem = inputNumber.nextInt();
						for (Hospedagem hh : hospedagemList) {
							if (hh.getId() == idHospedagem) {
								hospedagem.setId(hh.getId());
								viagem.setHospedagem(hh);
								viagem.setPrecoDiaria(hh.getPrecoDiaria());
							}
						}

					} else {
						
						System.out.println("                                                       ");
						System.out.println("*** Nenhuma hospedagem disponivel para esse destino ***");
						System.out.println("                                                       ");
					}
				}

				System.out.println("Digite alguma observações para viagem, tipo limite de bagagem, check-in...");
				viagem.setObservacoes(inputStr.nextLine());
				destino.setId_destino(idDestino);
				viagem.setDestino(destino);
				viagem.setUsuario(usuario);
				viagemDao.createViagem(viagem);
				break;
			}
			// inserir dados de contato
			case 5: {

				contato.setData(LocalDateTime.now());
				System.out.println("Digite o email");

				contato.setEmail(inputStr.nextLine());
				System.out.println("Digite a mensagem ");
				contato.setMensagem(inputStr.nextLine());
				System.out.println("Digite o nome");
				contato.setNome(inputStr.nextLine());
				System.out.println("Digite o telefone de contato *somente numeros");
				contato.setTelefone(inputStr.nextLine());

				contatoDao.createContato(contato);
				break;
			}

			default:
				System.out.println("Digito inválido, tenten novamente.");
				break;
			}

		}

	}

	public void read() {

		int subMenuNumber = 1;

		while (subMenuNumber != 0) {
			System.out.println(" ____________________________________________________________");
			System.out.println("|       Digite o numero correspondente ao dado desejado      |");
			System.out.println("|                [ 1 ] para Usuário                          |");
			System.out.println("|                [ 2 ] para Destino                          |");
			System.out.println("|                [ 3 ] para Hospedagem                       |");
			System.out.println("|                [ 4 ] para Viagem                           |");
			System.out.println("|                [ 5 ] para Contato                          |");
			System.out.println("|                [ 0 ] para Voltar ao menu anterior          |");
			System.out.println("|____________________________________________________________|");
			subMenuNumber = inputNumber.nextInt();
			switch (subMenuNumber) {
			case 0: {
				System.out.println("Voltando ao menu anterior...");
				break;
			}
			case 1: {
				usuarioDao.readUsuarios();
				break;
			}
			case 2: {
				destinoDao.readDestino();
				break;
			}
			case 3: {

				hospedagemDao.readHospedagem();
				break;
			}
			case 4: {
				viagemDao.readViagem();
				break;
			}
			case 5: {
				contatoDao.readContato();
				break;
			}
			default: {
				System.out.println("Digito inválido tente novamente");
				break;
			}
			}
		}
	}

	public void update() {
		int subMenuNumber = 1;

		while (subMenuNumber != 0) {
			System.out.println(" _________________________________________________________________");
			System.out.println("|Digite o numero correspondente para atualizar a tabela desejada  |");
			System.out.println("|                [ 1 ] para Usuário                               |");
			System.out.println("|                [ 2 ] para Destino                               |");
			System.out.println("|                [ 3 ] para Hospedagem                            |");
			System.out.println("|                [ 4 ] para Viagem                                |");
			System.out.println("|                [ 5 ] para Contato                               |");
			System.out.println("|                [ 0 ] para Voltar ao menu anterior               |");
			System.out.println("|_________________________________________________________________|");
			subMenuNumber = inputNumber.nextInt();
			switch (subMenuNumber) {
			case 0: {
				System.out.println("Voltando ao menu anterior...");
				break;
			}
			case 1: {

				System.out.println("Digite o id do usuário para alteração");
				int id = inputNumber.nextInt();
				System.out.println("Digite o campo que deseja fazer a alteração");
				System.out.println("Campo disponiveis, nome, rg, endereco, cpf, "
						+ "dataNascimento, telefone, senha, email, tipoUsuario");
				String campo = inputStr.nextLine();
				switch (campo) {
				case "nome": {
					System.out.println("Digite o nome do usuário");

					usuario.setNome(inputStr.nextLine());
					break;
				}
				case "rg": {
					System.out.println("Digite rg do usuário");
					usuario.setRg(inputStr.nextLine());
					break;
				}
				case "endereco": {
					System.out.println("Digite o endereço do usuário");
					usuario.setEndereco(inputStr.nextLine());
					break;
				}
				case "cpf": {

					System.out.println("Digite o cpf do usuário");
					usuario.setCpf(inputStr.nextLine());
					break;
				}
				case "estado": {
					System.out.println("Digite o estado do usuário");
					usuario.setEstado(inputStr.nextLine());
					break;
				}
				case "dataNascimento": {

					String[] nascimento = { "Dia", "Mês", "Ano" };
					int[] data = { 0, 0, 0 };
					for (int i = 0; i < 3; i++) {

						System.out.println("Digite o " + nascimento[i] + " nascimento *somente numeros");
						data[i] = inputNumber.nextInt();
					}
					usuario.setDataNascimento(LocalDate.of(data[2], data[1], data[0]));
					break;
				}
				case "telefone": {

					System.out.println("Digite o telefone do usuário");
					usuario.setTelefone(inputStr.nextLine());
					break;
				}
				case "email": {

					System.out.println("Digite o email do usuário");
					usuario.setEmail(inputStr.nextLine());
					break;
				}
				case "senha": {

					System.out.println("Digite a senha do usuário");
					usuario.setSenha(inputStr.nextLine());
					break;
				}
				case "tipoUsuario": {
					System.out.println("Digite o tipo do usuário");
					String user = inputStr.nextLine();
					if ("client".equalsIgnoreCase(user) || "user".equalsIgnoreCase(user)
							|| "admin".equalsIgnoreCase(user)) {
						usuario.setTipoUsuario(user);
					} else {
						System.out.println("tipo de usuário não existe será adicionado como client");
						usuario.setTipoUsuario("client");
					}

					break;
				}
				default: {
					System.out.println("Opção incorreta");
					break;
				}
				}
				usuarioDao.updateUsuario(usuario, id, campo);
				break;
			}
			case 2: {
				System.out.println("Digite o id do destino para alteração");
				int id = inputNumber.nextInt();
				System.out.println("Digite o campo que deseja fazer a alteração");
				System.out.println("Campo disponiveis, detalhes,estado,pais,cidade, img");
				String campo = inputStr.nextLine();
				switch (campo) {
				case "detalhes": {
					System.out.println("Digite os detalhes do destino");
					destino.setDetalhes(inputStr.nextLine());
					break;
				}
				case "estado": {
					System.out.println("Digite estado do usuário");
					destino.setEstado(inputStr.nextLine());
					break;
				}
				case "pais": {
					System.out.println("Digite o pais do destino");
					destino.setPais(inputStr.nextLine());
					break;
				}
				case "img": {

					System.out.println("Digite o link de imagem do destino");
					destino.setImg(inputStr.nextLine());
					break;
				}

				case "cidade": {
					System.out.println("Digite a cidade do destino");
					destino.setCidade(inputStr.nextLine());
					break;
				}
				default: {
					System.out.println("Opção incorreta");
					break;
				}
				}

				destinoDao.updateDestino(destino, id, campo);
				break;
			}
			case 3: {

				System.out.println("Digite o id do destino para alteração");
				int id = inputNumber.nextInt();
				System.out.println("Digite o campo que deseja fazer a alteração");
				System.out.println("Campo disponiveis, precoDiaria, nomeLocal,endereco,id_destino");
				String campo = inputStr.nextLine();
				switch (campo) {
				case "nomeLocal": {
					System.out.println("Digite o nome do local de hospedagem");
					hospedagem.setNomeLocal(inputStr.nextLine());
					break;
				}
				case "precoDiaria": {
					System.out.println("Digite o preço da diária do local");
					hospedagem.setPrecoDiaria(inputNumber.useLocale(Locale.US).nextDouble());
					break;
				}
				case "endereco": {
					System.out.println("Digite o endereço do local de hospedagem");
					hospedagem.setEndereco(inputStr.nextLine());
					break;
				}
				case "id_destino": {
					destinoDao.readDestino();
					System.out.println("Digite o id correto do destino");
					destino.setId_destino(inputNumber.nextInt());
					hospedagem.setDestino(destino);
					break;
				}

				default: {
					System.out.println("Opção incorreta");
					break;
				}
				}
				hospedagemDao.updateHospedagem(hospedagem, id, campo);
				break;
			}
			case 4: {
				
				viagemDao.readViagem();
				System.out.println("Digite o id da viagem para alteração");
				int id = inputNumber.nextInt();				
				System.out.println("Digite o campo que deseja fazer a alteração");
				System.out.println(
						"Campo disponiveis, obsevacoes, desconto,dataSaida,dataEntrada,preco");
				String campo = inputStr.nextLine();
				
				switch (campo) {
				case "observacoes": {
					System.out.println("Digite as observações da viagem");
					viagem.setObservacoes(inputStr.nextLine());
					viagemDao.updateViagem(viagem, id, campo);
					break;
				}
				case "desconto": {
					System.out.println("Digite o desconto da viagem");
					viagem.setDesconto(inputNumber.nextInt());
					viagemDao.updateViagem(viagem, id, campo);
					break;
				}
				case "dataEntrada": {
					String[] dataViagem = { "* Dia *", "* Mês *", "* Ano *", "* Hora *", "* Minuto *" };
					int[] data = { 0, 0, 0, 0, 0 };
					for (int i = 0; i < 5; i++) {

						System.out.println("Digite o " + dataViagem[i] + " embarque viagem *somente numeros");
						data[i] = inputNumber.nextInt();
					}
					
					viagem.setDataEntrada(LocalDateTime.of(data[2], data[1], data[0], data[3], 
							data[4]));
					viagemDao.updateViagem(viagem, id, campo);
					break;
				}
				case "dataSaida": {
					String[] dataViagem = { "* Dia *", "* Mês *", "* Ano *", "* Hora *", "* Minuto *" };
					int[] data = { 0, 0, 0, 0, 0 };
					
					for (int i = 0; i < 5; i++) {
						System.out.println("Digite o " + dataViagem[i] + " desembarque viagem *somente numeros");
						data[i] = inputNumber.nextInt();
					}
					
					viagem.setDataSaida(LocalDateTime.of(data[2], data[1], data[0], data[3], 
							data[4]));
					viagemDao.updateViagem(viagem, id, campo);
					break;
				}
				
				case "possuiHospedagem":{
					System.out.println("Se possui hospedagem digite 1 senão digite 0");
					int ifPossui = inputNumber.nextInt();
					
					if(ifPossui == 1 || ifPossui == 0) {
						viagem.setPossuiHospedagem(ifPossui);
					}else {
						System.out.println("Valor incorreto neste caso a viagem foi cadastrada sem hospedagem");
						viagem.setPossuiHospedagem(0);
					}
				}
				case "preco": {
					System.out.println("Digite o preco da viagem");
					viagem.setPreco(inputNumber.nextDouble());
					viagemDao.updateViagem(viagem, id, campo);
					break;
				}
				default: {
					System.out.println("Opção incorreta");	
					id = 0;
					break;
				}
				}
				break;
			}
			case 5: {

				System.out.println("Digite o id do contato para alteração");
				int id = inputNumber.nextInt();
				System.out.println("Digite o campo que deseja fazer a alteração");
				System.out.println("Campo disponiveis, email, mensagem,telefone,nome");
				String campo = inputStr.nextLine();
				switch (campo) {
				case "email": {
					System.out.println("Digite o email do contato");
					contato.setEmail(inputStr.nextLine());
					break;
				}
				case "mensagem": {
					System.out.println("Digite a mensagem para alteração");
					contato.setMensagem(inputStr.nextLine());
					break;
				}
				case "telefone": {
					System.out.println("Digite o telefone do contato");
					contato.setTelefone(inputStr.nextLine());
					break;
				}
				case "nome": {

					System.out.println("Digite o nome do contato");
					contato.setNome(inputStr.nextLine());
					break;
				}

				default: {
					System.out.println("Opção incorreta");
					break;
				}
				}

				contatoDao.updateContato(contato, id, campo);
				break;
			}
			default: {
				System.out.println("Digito inválido tente novamente");
				break;
			}
			}
		}
	}

	public void delete() {
		int subMenuNumber = 1;

		while (subMenuNumber != 0) {
			System.out.println(" ___________________________________________________________________");
			System.out.println("|Digite o numero correspondente para deletar a informação desejada  |");
			System.out.println("|                    [ 1 ] para Usuário                             |");
			System.out.println("|                    [ 2 ] para Destino                             |");
			System.out.println("|                    [ 3 ] para Hospedagem                          |");
			System.out.println("|                    [ 4 ] para Viagem                              |");
			System.out.println("|                    [ 5 ] para Contato                             |");
			System.out.println("|                    [ 0 ] para Voltar ao menu anterior             |");
			System.out.println("|___________________________________________________________________|");
			subMenuNumber = inputNumber.nextInt();
			switch (subMenuNumber) {
			case 0: {
				System.out.println("Voltando ao menu anterior...");
				break;
			}
			case 1: {
				System.out.println("Digite o id do usuário para deletar");
				usuarioDao.readUsuarios();
				usuarioDao.deleteUsuario(inputNumber.nextInt());
				break;
			}
			case 2: {
				System.out.println("Digite o id do destino para deletar");
				destinoDao.readDestino();
				destinoDao.deleteDestino(inputNumber.nextInt());
				break;
			}
			case 3: {
				System.out.println("Digite o id da hospedagem para deletar");
				hospedagemDao.readHospedagem();
				hospedagemDao.deleteHospedagem(inputNumber.nextInt());
				break;
			}
			case 4: {
				System.out.println("Digite o id da viagem para deletar");
				viagemDao.readViagem();
				viagemDao.deleteViagem(inputNumber.nextInt());
				break;
			}
			case 5: {
				System.out.println("Digite o id do contato para deletar");
				contatoDao.readContato();
				contatoDao.deleteContato(inputNumber.nextInt());
				break;
			}

			default: {
				System.out.println("Valor inválido tente novamente");
				break;
			}
			}
		}

	}
	

}
