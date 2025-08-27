package br.com.Banco.UI;

import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.*;

import br.com.Banco.Dao.ContaCorrenteDao;
import br.com.Banco.Exception.SaldoInsuficienteException;
import br.com.Banco.app.ReproduzirAudios;
import br.com.Banco.app.SalvarDados;
import br.com.Banco.app.SearchCpf;
import br.com.Banco.model.ContaCorrente;

public class ContaFrame extends JFrame {

    private List<ContaCorrente> listaDeContas;
    private String cpf;
    private JPanel mainPanel;

    private CardLayout cardLayout;
    private JLabel labelSaldo;

    ReproduzirAudios audio = new ReproduzirAudios();
    // componentes da tela
    private JTextField numeroDaMovimentacao;
        static ContaCorrenteDao dao = new ContaCorrenteDao();


    //#region UI
    public ContaFrame(String cpf) {
        super("Conta");
        this.listaDeContas = listaDeContas;
        this.cpf = cpf;

        ImageIcon icon = new ImageIcon(getClass().getResource("/br/com/Banco/imgs/logo.png"));
        setIconImage(icon.getImage());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 350);
        setLocationRelativeTo(null);
        setResizable(false);

        // Buscar User
        ContaCorrente user = dao.buscaEspecifica(cpf);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.decode("#FBF2B7"));

        JButton btnUser = new JButton("👤");
        btnUser.setBounds(10, 10, 50, 20);
        btnUser.addActionListener(e -> edit());

        panel.add(btnUser);

        JButton btnSair = new JButton("X");
        btnSair.setBounds(320, 10, 50, 20);
        btnSair.addActionListener(e -> sair());
        panel.add(btnSair);

        JLabel labelCpf = new JLabel("CPF:" + user.getCpf());
        labelCpf.setBounds(50, 40, 200, 25);
        panel.add(labelCpf);

        JLabel labelNome = new JLabel("Nome:" + user.getTitular());
        labelNome.setBounds(50, 80, 200, 25);
        panel.add(labelNome);

        labelSaldo = new JLabel("Saldo:" + user.getSaldo());
        labelSaldo.setBounds(50, 120, 200, 25);
        panel.add(labelSaldo);

        JLabel labelNumber = new JLabel("Valor:");
        labelNumber.setBounds(50, 160, 200, 25);
        panel.add(labelNumber);

        numeroDaMovimentacao = new JTextField();
        numeroDaMovimentacao.setBounds(150, 160, 180, 25);
        panel.add(numeroDaMovimentacao);

        JButton btnDepositar = new JButton("Depositar");
        btnDepositar.setBounds(50, 210, 120, 30);
        btnDepositar.addActionListener(e -> depositar(user));
        panel.add(btnDepositar);

        JButton btnSacar = new JButton("Sacar");
        btnSacar.setBounds(190, 210, 120, 30);
        btnSacar.addActionListener(e -> sacar(user));

        panel.add(btnSacar);

        add(panel);

    }
    //#endregion

    //#region Funções

    private void depositar(ContaCorrente user) {
        if (!numeroDaMovimentacao.getText().isEmpty()) {
            Double numero = Double.parseDouble(numeroDaMovimentacao.getText());

            user.depositar(numero);
            audio.reproduzirAudios("src\\br\\com\\Banco\\Audios\\Caixa-Registradora-Dinheiro.wav");

            JOptionPane.showMessageDialog(this, "Deposito de R$ " + numero + " feito!");

            salvar(user);
            labelSaldo.setText("Saldo: " +user.getSaldo());
        }
    }

    private void sacar(ContaCorrente user) {

        if (!numeroDaMovimentacao.getText().isEmpty()) {
            Double numero = Double.parseDouble(numeroDaMovimentacao.getText());

            try {
                if (numero > 1000)
                    audio.reproduzirAudios("src\\br\\com\\Banco\\Audios\\eu-quero-eu-posso-cariani_095031.wav");
                else
                    audio.reproduzirAudios("src\\br\\com\\Banco\\Audios\\classic_hurt.wav");

                user.sacar(numero);
                JOptionPane.showMessageDialog(this, "Saque de R$ " + numero + " feito!");

            } catch (Exception e) {
                audio.reproduzirAudios("ssrc\\br\\com\\Banco\\Audios\\n" + //
                        "ao-consegue-ne_233542.wav");

            }

            salvar(user);
            labelSaldo.setText("Saldo: " + user.getSaldo());
        }
    }

    private void salvar(ContaCorrente user) {
        dao.atualizar(user);
    }

    private void sair() {
        this.dispose(); // fecha login
        MainFrame mainFrame = new MainFrame(listaDeContas);
        mainFrame.setVisible(true);
        return;
    }

    private void edit() {
        this.dispose(); // fecha login
        EditeProfile editeProfile = new EditeProfile(cpf);
        editeProfile.setVisible(true);
        return;

    }
    //#endregion
}
