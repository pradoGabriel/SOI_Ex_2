package View;

import java.util.Scanner;
import Controller.ProcessosController;


public class Principal {

    public static void main(String[] args) {
        ProcessosController procController = new ProcessosController();
        Scanner ler = new Scanner(System.in);
        int escolha = 0;
        while (escolha != 9) {
        	System.out.println("Escolha uma opção:"
                    + "\n1-Identificar SO "
                    + "\n2-Lista de tarefas "
                    + "\n3-Finalizar por PID "
                    + "\n4-Finalizar por nome "
                    + "\n9-Encerrar");
        	escolha = ler.nextInt();
            String os = procController.os();
            switch (escolha) {
                case 1:
                	System.out.println(os);
                    break;
                case 2:
                    procController.listarProcessos(os);
                    break;
                case 3:
                    int pid;
                	System.out.println("Digite o pid: ");
                	pid = ler.nextInt();
                    procController.killProcessPid(os, pid);
                    break;
                case 4:
                    String name;
                	System.out.println("Digite o nome: ");
                	name = ler.nextLine();
                    procController.killProcessName(os, name);
                    break;
                case 9:
                    System.out.print("Encerrando");
                    break;
            }
        }

    }
}
