package br.com.Banco.UI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.com.Banco.app.util.SearchCpf;
import br.com.Banco.dao.ContaCorrenteDao;
import br.com.Banco.dao.TransferenciaDao;
import br.com.Banco.model.ContaCorrente;

public class TransferirFrame extends JFrame {
    static ContaCorrenteDao dao = new ContaCorrenteDao();
    // ContaCorrente user = dao.buscaEspecifica(cpf);
    private JTextField numeroDaMovimentacao;
    private JTextField cpfRecebidor;

    // Refatoração, APENAS DO FRONT(centralizar), pelo chatGPT
    public TransferirFrame(String cpf) {
        super("Transferir");

        // Ícone da janela
        ImageIcon icon = new ImageIcon(getClass().getResource("/br/com/Banco/imgs/logo.png"));
        setIconImage(icon.getImage());

        // Configurações básicas da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 350);
        setLocationRelativeTo(null); // Centraliza a janela
        setResizable(false);

        // Painel principal
        JPanel panel = new JPanel(null); // Layout absoluto
        panel.setBackground(Color.decode("#FBF2B7")); // Cor de fundo suave

        // Botão de sair
        JButton btnSair = new JButton("X");
        btnSair.setBounds(320, 10, 50, 25);
        btnSair.setFocusPainted(false);
        btnSair.addActionListener(e -> sair(cpf));
        panel.add(btnSair);

        // Rótulo do CPF destinatário
        JLabel labelCpf = new JLabel("CPF do destinatário:");
        labelCpf.setFont(new Font("Arial", Font.BOLD, 14));
        labelCpf.setBounds(115, 50, 200, 25);
        panel.add(labelCpf);

        // Campo de texto para CPF destinatário
        cpfRecebidor = new JTextField();
        cpfRecebidor.setBounds(90, 80, 220, 30);
        panel.add(cpfRecebidor);

        // Rótulo do valor da transferência
        JLabel labelNumber = new JLabel("Valor da transferência:");
        labelNumber.setFont(new Font("Arial", Font.BOLD, 14));
        labelNumber.setBounds(115, 120, 200, 25);
        panel.add(labelNumber);

        // Campo de texto para valor
        numeroDaMovimentacao = new JTextField();
        numeroDaMovimentacao.setBounds(90, 150, 220, 30);
        panel.add(numeroDaMovimentacao);

        // Botão de transferir
        JButton btnTransferir = new JButton("Transferir");
        btnTransferir.setBounds(70, 220, 260, 40);
        btnTransferir.setBackground(new Color(46, 139, 87));
        btnTransferir.setForeground(Color.WHITE);
        btnTransferir.setFont(new Font("Arial", Font.BOLD, 16));
        btnTransferir.setFocusPainted(false);
        btnTransferir.addActionListener(e -> transferir(cpf));
        panel.add(btnTransferir);

        // Adiciona o painel à janela
        add(panel);
    }

    private void sair(String cpf) {

        this.dispose(); // fecha login
        ContaFrame frame = new ContaFrame(cpf);
        frame.setVisible(true);
        return;
    }

    private void transferir(String cpf) {
        SearchCpf search = new SearchCpf();
        try {
            String cpfRecebedor = new String(cpfRecebidor.getText()); // Recebedor
            ContaCorrente user = dao.buscaEspecifica(cpf); // Doador
            if (search.search(cpfRecebedor) == false) {
                JOptionPane.showMessageDialog(this, "Cpf inválido", "erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            TransferenciaDao transferenciaDao = new TransferenciaDao(); // API
            Double numero = Double.parseDouble(numeroDaMovimentacao.getText()); // Valor
            if (user.getSaldo() < numero) { // Se o saldo for menor que o valor
                JOptionPane.showMessageDialog(this, "Saldo inválido", "erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (numeroDaMovimentacao.getText().isEmpty() || numero < 0) {
                JOptionPane.showMessageDialog(this, "Insira um valor válido!", "erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                transferenciaDao.transferir(cpf, cpfRecebedor, numero);
                JOptionPane.showMessageDialog(this, "Transação realizada com sucesso");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Não foi possível realizar a transação.\nErro: " + e);
            }

            sair(cpf);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Insira um valor válido!", "erro", JOptionPane.ERROR_MESSAGE);
        }

    }
}
