package esqueleto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import modelo.Usuario;

public class Instalar {
	
	  public static void main(String[]args) {
		  
		  //Criando o arquivo do primeiro usuario
		  
		  Usuario u = new Usuario (1, "Administrador", "ADM", "admin");
		  
		  File arquivo = new File( "../Usuario.txt" );
		  
		  boolean existe = arquivo.exists();
		  try {
		  arquivo.createNewFile();
		  FileWriter fw = new FileWriter(arquivo);
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
		    
		  }catch (Exception e) {
			// TODO: handle exception
			  System.out.println("error...");
			  e.printStackTrace();
		}
		   
		  
	  }
	   

}
