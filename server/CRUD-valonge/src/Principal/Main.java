package Principal;

import java.util.List;
import java.util.Scanner;

import ClassesTabelas.Hospedagem;
import ClassesTabelas.Viagem;
import Menu.SubMenu;
import TabelasDAO.HospedagemDAO;
import TabelasDAO.ViagemDAO;

public class Main {

	public static void main(String[] args) {
	
		Scanner input = new Scanner(System.in);
		SubMenu subMenu = new SubMenu();
		
		int menuNumber = 1;
		
		while(menuNumber != 0) {
			System.out.println("____________________________________________________________");
			System.out.println("|                                                          |");
			System.out.println("|----- Bem vindo ao nosso sistema de viagens 'Válonge' ----|");
			System.out.println("|            Digite [ 1 ] para inserir dados               |");
			System.out.println("|            Digite [ 2 ] para leitura de  dados           |");
			System.out.println("|            Digite [ 3 ] para atualizar dados             |");
			System.out.println("|            Digite [ 4 ] para deletar dados               |");
			System.out.println("|            Digite [ 0 ] para sair do menu                |");
			System.out.println("|__________________________________________________________|");
			
			menuNumber = input.nextInt();
			
			switch (menuNumber) {
			case 0:{
				System.out.println("Saindo do Menu...");
				input.close();
				break;
			}
			case 1:{ 
				subMenu.insert();
				break;
			}
			case 2:{ 
				subMenu.read();
				break;
			}
			case 3:{
				subMenu.update();
				break;
			}
			case 4:{
				subMenu.delete();
				break;
			}
			default:
				System.out.println("Opção inexistente");
			}
		}	
	}

}
