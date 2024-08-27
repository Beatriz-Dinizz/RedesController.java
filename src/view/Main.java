package view;

import javax.swing.JOptionPane;

import controller.RedesController;

public class Main {

	public static void main(String[] args) {
		RedesController controller = new RedesController();
        int opc = 0;

        while (opc != 9) {
            try {
                opc = Integer.parseInt(JOptionPane.showInputDialog(
                        "1 - Consultar o nome do adaptador de rede e o IPv4\n" +
                        "2 - Consultar tempo médio do ping\n" +
                        "9 - Finalizar"));

                switch (opc) {
                    case 1:
                        controller.ip();
                        break;
                    case 2:
                        controller.ping();
                        break;
                    case 9:
                        JOptionPane.showMessageDialog(null, "Programa finalizado!");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opção inválida!");
                        break;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida! Por favor, insira um número.");
            }
        }
    }
}
