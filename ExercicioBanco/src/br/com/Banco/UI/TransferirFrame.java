package br.com.Banco.UI;

import java.awt.Color;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.com.Banco.Dao.ContaCorrenteDao;
import br.com.Banco.Dao.TransferenciaDao;
import br.com.Banco.model.ContaCorrente;

public class TransferirFrame extends JFrame {
    static ContaCorrenteDao dao = new ContaCorrenteDao();
    // ContaCorrente user = dao.buscaEspecifica(cpf);
    private JTextField numeroDaMovimentacao;
    private JTextField cpfRecebidor;

    TransferirFrame(String cpf) {
        super("Transferir");

        ImageIcon icon = new ImageIcon(getClass().getResource("/br/com/Banco/imgs/logo.png"));
        setIconImage(icon.getImage());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 350);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.decode("#FBF2B7"));

        JButton btnSair = new JButton("X");
        btnSair.setBounds(320, 10, 50, 20);
        btnSair.addActionListener(e -> sair(cpf));
        panel.add(btnSair);

        JLabel labelCpf = new JLabel("Cpf para transferir:");
        labelCpf.setBounds(110, 10, 200, 25);
        panel.add(labelCpf);

        cpfRecebidor = new JTextField();
        cpfRecebidor.setBounds(90, 45, 180, 25);
        panel.add(cpfRecebidor);

        JLabel labelNumber = new JLabel("Valor:");
        labelNumber.setBounds(180, 80, 200, 25);
        panel.add(labelNumber);

        numeroDaMovimentacao = new JTextField();
        numeroDaMovimentacao.setBounds(90, 115, 180, 25);
        panel.add(numeroDaMovimentacao);

        JButton btnTransferir = new JButton("Transferir");
        btnTransferir.setBounds(70, 260, 270, 30);
        btnTransferir.addActionListener(e -> transferir(cpf));
        panel.add(btnTransferir);

        add(panel);

    }

    private void sair(String cpf) {

        List<ContaCorrente> contas = dao.listar();
        this.dispose(); // fecha login
        ContaFrame frame = new ContaFrame(cpf);
        frame.setVisible(true);
        return;
    }

    private void transferir(String cpf) {
        String cpfRecebedor = new String(cpfRecebidor.getText());
        ContaCorrente user = dao.buscaEspecifica(cpf);
        TransferenciaDao transferenciaDao = new TransferenciaDao();
        Double numero = Double.parseDouble(numeroDaMovimentacao.getText());
        try {
            transferenciaDao.transferir(cpf, cpfRecebedor, numero);
            JOptionPane.showMessageDialog(this, "Transação realizada com sucesso");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Não foi possível realizar a transação.\nErro: " + e);
        }
        

        sair(cpf);
    }
}
