package br.com.Banco.UI;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.com.Banco.Dao.ContaCorrenteDao;
import br.com.Banco.Dao.ExtratoDao;

public class ExtratoFrame extends JFrame {

    private int index = 0;
    private String str = " ";
    private JLabel labelNumber;

    static ContaCorrenteDao dao = new ContaCorrenteDao();
    ExtratoDao extratoDao = new ExtratoDao();

    // Refatoração, APENAS DO FRONT(centralizar), pelo chatGPT
    public ExtratoFrame(String cpf) {
        super("Extrato");
        // int index = 0;
        str = listar(cpf, index);
        // Ícone da janela
        ImageIcon icon = new ImageIcon(getClass().getResource("/br/com/Banco/imgs/logo.png"));
        setIconImage(icon.getImage());

        // Configurações básicas da janela
        int wid = 600;
        int hei = 500;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(wid, hei);
        setLocationRelativeTo(null); // Centraliza a janela
        setResizable(false);

        // Salvar extrato
        ArrayList<String> extrato = new ArrayList<>();
        SalvarExtrato salvar = new SalvarExtrato();
        extrato = extratoDao.listarExtrato(cpf);
        salvar.salvar(extrato);

        // Painel principal
        JPanel panel = new JPanel(null); // Layout absoluto
        panel.setBackground(Color.decode("#FBF2B7")); // Cor de fundo suave

        // Botão de sair
        JButton btnSair = new JButton("X");
        btnSair.setBounds(wid - 80, 10, 50, 25);
        btnSair.setFocusPainted(false);
        btnSair.addActionListener(e -> sair(cpf));
        panel.add(btnSair);

        // Rótulo do CPF destinatário
        JLabel labelCpf = new JLabel("Extrato:");
        labelCpf.setFont(new Font("Arial", Font.BOLD, 14));
        labelCpf.setBounds(wid / 2 - 30, 50, 200, 25);
        panel.add(labelCpf);

        // Rótulo do valor da transferência
        labelNumber = new JLabel(str);
        labelNumber.setFont(new Font("Arial", Font.BOLD, 14));
        labelNumber.setBounds(10, 120, wid - 10, 200);
        panel.add(labelNumber);

        JButton btnMaisCinco = new JButton("->");
        btnMaisCinco.setBounds(wid - 80, hei - 80, 50, 25);
        btnMaisCinco.setFocusPainted(false);
        btnMaisCinco.addActionListener(e -> alterarIndice(true, cpf));
        panel.add(btnMaisCinco);

        JButton btnMenosCinco = new JButton("<-");
        btnMenosCinco.setBounds(10, hei - 80, 50, 25);
        btnMenosCinco.setFocusPainted(false);
        btnMenosCinco.addActionListener(e -> alterarIndice(false, cpf));
        panel.add(btnMenosCinco);

        // Adiciona o painel à janela
        add(panel);
    }

    //Trocar o indíce da página
    private void alterarIndice(boolean condicao, String cpf) {
        ArrayList<String> extrato = new ArrayList<>();
        ExtratoDao extratoDao = new ExtratoDao();
        extrato = extratoDao.listarExtrato(cpf);
        if (condicao) {
            if (index < extrato.size() - 5) {
                index = index + 5;
            }
        } else {
            if (index != 0)
                index = index - 5;

        }
        str = listar(cpf, index);
        labelNumber.setText(str);
    }

    //Sair
    private void sair(String cpf) {

        this.dispose(); // fecha login
        ContaFrame frame = new ContaFrame(cpf);
        frame.setVisible(true);
        return;
    }

    //Listar o extrato da página
    private String listar(String cpf, int inicio) {
        ArrayList<String> extrato = new ArrayList<>();
        ExtratoDao extratoDao = new ExtratoDao();
        extrato = extratoDao.listarExtrato(cpf);
        String str = "<html>";
        if (inicio + 5 > extrato.size()) {
            for (int i = inicio; i < extrato.size(); i++) {
                str += extrato.get(i) + "<br> <br>";
            }
        } else {
            int ultimo = inicio + 5;
            for (int i = inicio; i < ultimo; i++) {
                str += extrato.get(i) + "<br> <br>";
            }
        }
        str += "</html>";
        return str;

    }

}
