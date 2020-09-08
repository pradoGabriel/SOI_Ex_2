package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ProcessosController {
	public ProcessosController() {
		super();
	}
	
	public String os() {
		String os = System.getProperty("os.name");
		String arch = System.getProperty("os.arch");
		String version = System.getProperty("os.version");
		return os +" - v. " + version + " - arch. " + arch;
	}
	
	public void listarProcessos(String os) {
		try {
			String process = null;
			if (os.contains("Windows")) {
				process = "TASKLIST /FO TABLE";
			} else if (os.contains("Linux")) {
				process = "ps -ef";
			}
			readProcess(process);
		} catch (Exception e) {
			String msgErro = e.getMessage();
			System.out.println(msgErro);
		}
	}
	
	public void killProcessPid (String os, int pid) {
		StringBuffer buffer = new StringBuffer();
		try {
			String comando = null;
			if (os.contains("Windows")) {
				comando = "TASKKILL /PID";
			} else if (os.contains("Linux")) {
				comando = "kill";
			}
			buffer.append(comando);
			buffer.append(" ");
			buffer.append(pid);
			System.out.println("Processo encerrado.");
		} catch (NumberFormatException e) {
			String msgErro = e.getMessage();
			System.out.println(msgErro);
		}
		callProcess(buffer.toString());
	}
	
	public void killProcessName (String os, String name) {
		StringBuffer buffer = new StringBuffer();
		try {
			String comando = null;
			if (os.contains("Windows")) {
				comando = "TASKKILL /IM";
			} else if (os.contains("Linux")) {
				comando = "pkill";
			}
			buffer.append(comando);
			buffer.append(" ");
			buffer.append(name);
		} catch (NumberFormatException e) {
			String msgErro = e.getMessage();
			System.out.println(msgErro);
		}
		callProcess(buffer.toString());
		System.out.println("Processo encerrado.");
	}
	
	public void callProcess(String process) {
		try {
			Runtime.getRuntime().exec(process);
		} catch (Exception e) {
			String msgErro = e.getMessage();
			if (msgErro.contains("740")) {
				//cmd /c caminho_do_processo
				StringBuffer buffer = new StringBuffer();
				buffer.append("cmd /c");
				buffer.append(" ");
				buffer.append(process);
				try {
					Runtime.getRuntime().exec(buffer.toString());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else {
				e.printStackTrace();
			}
		}
	}
	
	public void readProcess(String process) {
		try {
			Process p = Runtime.getRuntime().exec(process);
			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			
			while (linha != null) {
				System.out.println(linha);
				linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
