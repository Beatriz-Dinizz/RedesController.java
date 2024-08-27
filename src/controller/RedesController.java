package controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class RedesController {

	public RedesController() {
		super();
	}
	
	private String os() {
		String os = System.getProperty("os.name");		
		System.out.println(os);
		
		return os;
	}
	
	public void ip() {
        try {
            String os = os();
            String proc = "";

            if (os.contains("Win")) {
                proc = "ipconfig";
            } else if (os.contains("nux") || os.contains("mac")) {
                proc = "ifconfig";
            }

            Process process = Runtime.getRuntime().exec(proc);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String linha;

            while ((linha = reader.readLine()) != null) {
                if (os.contains("Win")) {
                    if (linha.trim().contains("Adaptador") || linha.trim().contains("Ethernet")) {
                    	// Exibe o nome do adaptador
                        System.out.println(linha.trim());  
                    } else if (linha.trim().contains("IPv4")) {
                        String[] vetorTexto = linha.split(":");
                     // Exibe o IPv4
                        System.out.println("IPv4: " + vetorTexto[1].trim());  
                    }
                } else {
                    if (linha.contains("inet ") && !linha.contains("inet6")) {
                        String[] vetorTexto = linha.trim().split(" ");
                     // Exibe o nome do adaptador
                        System.out.println("Adaptador: " + vetorTexto[vetorTexto.length - 1]);
                     // Exibe o IPv4
                        System.out.println("IPv4: " + vetorTexto[1].trim());  
                    }
                }
            }

            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public void ping() {
        try {
            String os = os();
            String proc = "";

            if (os.contains("Win")) {
                proc = "ping -4 -n 10 www.google.com.br";
            } else if (os.contains("nux") || os.contains("mac")) {
                proc = "ping -4 -c 10 www.google.com.br";
            }

            Process process = Runtime.getRuntime().exec(proc);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), Charset.forName("Windows-1252")));
            String linha;

            while ((linha = reader.readLine()) != null) {
                // Substituir os caracteres corrompidos manualmente
            	linha = linha.replace("¡", "í")
                           .replace("‚", "é")
                           .replace(" ", "á")
                           .replace("M�nimo", "Mínimo")
                           .replace("M�ximo", "Máximo")
                           .replace("M�dia", "Média");

                if (linha.toLowerCase().contains("média")) {
                    String[] vetorTexto = linha.split(",");
                    String mediaPing = vetorTexto[vetorTexto.length - 1].split("=")[1].trim();
                    System.out.println("Tempo médio de ping: " + mediaPing);
                }
            }

            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

