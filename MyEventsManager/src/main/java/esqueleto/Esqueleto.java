package esqueleto;

import java.util.List;
import java.util.Scanner;
import javax.swing.tree.ExpandVetoException;

import dao.UsuarioDao;
import modelo.Usuario;

public class Esqueleto {
	
	/*
- A solução deve ser desenvolvida dentro do paradigma orientado a objetos;
- Opcionalmente o projeto pode ser organizado em um repositório GIT;
- O uso de padrões arquiteturais como o MVC não é obrigatório, mas fortemente encorajado;
- A estruturação de um diagrama de classes é obrigatória, mas desejada;
- O projeto deve ser desenvolvido em Console;
- Deve implementar um sistema de cadastro e notificação de eventos que estejam ocorrendo na cidade em
que o estudante reside;
- O sistema deve prover um espaço para cadastro do usuário. Você deve definir os atributos do usuário, que
devem ser no mínimo 3 (quanto mais completo, melhor);
- Deve ser possível cadastrar eventos, definindo um horário (dentre outros atributos). Estes eventos devem
ter, obrigatoriamente, os atributos: nome, endereço, categoria, horário e descrição;
- Você deve delimitar as categorias para criação de eventos (festas, eventos esportivos, shows, entre outros
exemplos);
 - Deve ser possível consultar os eventos cadastrados e decidir participar de qualquer um que esteja listado;
- Da mesma forma, deve ser possível visualizar os eventos em que a presença do usuário foi confirmada e
que seja possível cancelar a participação;
- Através do horário, o programa deve ordenar os eventos mais próximos e informar se um evento está
ocorrendo no momento (é desejável utilizar a estrutura DateTime para o controle de horários);
 - O sistema também deve informar os eventos que já ocorreram;
 - As informações dos eventos devem ser salvas em um arquivo de texto chamado events.data;
- Toda vez que o programa for aberto, deve carregar os eventos a partir da leitura deste arquivo;

   */
	
	private static Scanner sc;
	private static Scanner cd;

	public static void main(String[]artgs) {
		
		UsuarioDao.carregarUsuarios();
		List<Usuario> usuarios = UsuarioDao.getUsuarios();
		
		/* O comentário a seguir é somente para testar se o usuário está sendo lido
		   
		   for(Usuario user : usuarios){
		   System.out.println(user.getId());
		   System.out.println(user.getNome());
		   System.out.println(user.getLogin());
		   System.out.println(user.getSenha());
		   
		   }
		    
		 */
		
		boolean autenticado = false;
		sc = new Scanner(System.in);
		Usuario userAtual = null;
		do {
			System.out.println("Digite o login");
			String login = sc.nextLine();
			System.out.println("Digite a senha");
			String senha = sc.nextLine();
			for(Usuario user: usuarios) {
				if(login.equals(user.getLogin())) {
					if(senha.equals(user.getSenha())) {
						userAtual = user;
						System.out.println("Bem vindo" + userAtual.getNome());
						autenticado = true;
						break;
					}else {
						System.out.println("Senha ou usuario inválido");
						System.out.println("Deseja sair? s/n");
						String sair = sc.nextLine();
						char s = sair.charAt(0);
						if((s=='s')||(s=='S')) {
							System.exit(0);
						}
						
					}
				}
			}
		}while(!autenticado);
		
		try {
			if(userAtual != null) {
				System.out.print("Login realizado com sucesso! \n ID: " + userAtual.getId() + 
						"Nome: " + userAtual.getNome() + 
						"Login: " + userAtual.getLogin() + 
						"Senha: " + userAtual.getSenha());
				
			}
			
			char s = 'n';
			do {
				System.out.println("\n Digite a opção: \n"
						+ " 8 para listar usuarios \n"
						+ " 9 para cadastrar usuario \n"
						+ "s para sair");
				String op = sc.nextLine();
				s = op.charAt(0);
				
				switch (s) {
				case '8':
					listaUsuarios();
					break;
				case '9':
					 cadastroUsuario();
					 break;
				
				}
				
				
			}while (!(s=='s')||(s=='S'));
		}catch(Exception e) {
			System.out.println("Erro de Login");
			e.printStackTrace();
		}
		
	}

	public static void listaUsuarios() {
		List<Usuario> usuarios = UsuarioDao.getUsuarios();
		System.out.println("=====  Lista de Usuarios  =====");
		
		for(Usuario user : usuarios) {
			System.out.println(user.getId());
			System.out.println(user.getNome());
			System.out.println(user.getLogin());
			
		}
		
		System.out.println("=====  Fim da Lista  =====");
		
		
	}

	public static void cadastroUsuario() {
		cd = new Scanner(System.in);
		int id = UsuarioDao.getMaior()+1;
		System.out.println("Digite o nome");
		String nome = cd.nextLine();
		System.out.println("Digite o login");
		String login = cd.nextLine();
		System.out.println("Digite a senha");
		String senha = cd.nextLine();
		if(UsuarioDao.verificarUsuario(login)) {
			System.out.println("Não foi possível cadastrar o usuario \n"
					+ "motivo: ");
		}else {
			Usuario user = new Usuario(id,nome,login,senha);
			if(UsuarioDao.salvaUsuario(user)) {
				System.out.println("Usuário cadastrado com sucesso!!");
			}else {
				System.out.println("Erro ao cadastrar usuário");
			}
		}
		
		
	}
	
	

}
