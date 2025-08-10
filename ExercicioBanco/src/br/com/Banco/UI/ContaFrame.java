package br.com.Banco.UI;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.*;

import br.com.Banco.Exception.SaldoInsuficienteException;
import br.com.Banco.app.SalvarDados;
import br.com.Banco.app.SearchCpf;
import br.com.Banco.model.ContaCorrente;

public class ContaFrame extends JFrame {

    private List<ContaCorrente> listaDeContas;
    private String cpf;
    private JPanel mainPanel;

    private CardLayout cardLayout;
    private JLabel labelSaldo;

    // componentes da tela
    private JTextField numeroDaMovimentacao;

    public ContaFrame(List<ContaCorrente> listaDeContas, String cpf) {
        super("Conta");
        this.listaDeContas = listaDeContas;
        this.cpf = cpf;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 350);
        setLocationRelativeTo(null);
        setResizable(false);

        // Buscar User
        SearchCpf busca = new SearchCpf();
        int user = busca.search(listaDeContas, cpf);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JButton btnUser = new JButton("...");
        btnUser.setBounds(10, 10, 50, 20);
        btnUser.addActionListener(e -> edit());

        panel.add(btnUser);

        JButton btnSair = new JButton("X");
        btnSair.setBounds(320, 10, 50, 20);
        btnSair.addActionListener(e -> sair());
        panel.add(btnSair);

        JLabel labelCpf = new JLabel("CPF:" + listaDeContas.get(user).getCpf());
        labelCpf.setBounds(50, 30, 200, 25);
        panel.add(labelCpf);

        JLabel labelNome = new JLabel("Nome:" + listaDeContas.get(user).getTitular());
        labelNome.setBounds(50, 80, 200, 25);
        panel.add(labelNome);

        labelSaldo = new JLabel("Saldo:" + listaDeContas.get(user).getSaldo());
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

    private void depositar(int user) {
        Double numero = Double.parseDouble(numeroDaMovimentacao.getText());
        listaDeContas.get(user).depositar(numero);
        salvar();
        labelSaldo.setText("Saldo: " + listaDeContas.get(user).getSaldo());
    }

    private void sacar(int user) {
        try {
            Double numero = Double.parseDouble(numeroDaMovimentacao.getText());
            listaDeContas.get(user).sacar(numero);
        } catch (Exception e) {
        }
        salvar();
        labelSaldo.setText("Saldo: " + listaDeContas.get(user).getSaldo());

    }

    private void salvar() {
        SalvarDados save = new SalvarDados();
        save.salvar(listaDeContas);
    }

    private void sair() {
        this.dispose(); // fecha login
        MainFrame mainFrame = new MainFrame(listaDeContas);
        mainFrame.setVisible(true);
        return;
    }

    private void edit() {
        this.dispose(); // fecha login
        EditeProfile editeProfile = new EditeProfile(listaDeContas, cpf);
        editeProfile.setVisible(true);
        return;

    }
}
