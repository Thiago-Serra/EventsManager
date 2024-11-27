package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import modelo.Usuario;

public class UsuarioDao {

	private static List<Usuario> usuarios = new ArrayList<Usuario>();
	private static int maior = 0;
	
	
	public static void carregarUsuarios() {
		
		
		File arquivo = new File ("../Usuario.txt");
		
		
		boolean existe = arquivo.exists();
		if (existe) {
			
			try {
				// arquivo.createNewFile();
				// FileWriter fw = new FileWriter(arquivo);
				
				FileReader fr = new FileReader(arquivo);
				BufferedReader br = new BufferedReader(fr);
				while (br.ready()) {
					String linha = br.readLine();
					int id = Integer.parseInt(linha);
					linha = br.readLine();
					String nome = linha;
					linha = br.readLine();
					String login = linha;
					linha = br.readLine();
					String senha = linha;
					Usuario u = new Usuario(id,nome,login,senha);
					insereUsuario(u);
					
					
				}
				
				fr.close();
				br.close();
				
						
			}
			catch (Exception e ) {
				System.out.print("Error... \n ");
				e.printStackTrace();
			}
			
			
		
		}
		
	}


	public static void insereUsuario(Usuario u) {
		maior++;
		usuarios.add(u);
		
	}
	
	public static List<Usuario> getUsuarios(){
		return usuarios;
	}
	
	public static void setUsuarios(List<Usuario> aUsuarios) {
		usuarios = aUsuarios;
		
	}


	public static int getMaior() {
		
		return maior;
	}


	public static boolean verificarUsuario(String login) {
		boolean achou = false;
		for(Usuario user : usuarios) {
			if(login.equalsIgnoreCase(user.getLogin())) {
				achou = true;
			}
		}
		
		return achou;
	}


	public static boolean salvaUsuario(Usuario u) {
		insereUsuario(u);
		
		File arquivo = new File("../Usuario.txt");
		
		boolean existe = arquivo.exists();
		try {
			//arquivo.createNewFile();
			FileWriter fw = new FileWriter(arquivo,true); //true para continuar onde o arquivo parou 
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(Integer.toString(u.getId()));
			bw.newLine();
			bw.write(u.getNome());
			bw.newLine();
			bw.write(u.getLogin());
			bw.newLine();
			bw.write(u.getSenha());
			bw.newLine();
			bw.close();
			fw.close();
		}catch (Exception e ) {
			System.out.println("error...");
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	

}
