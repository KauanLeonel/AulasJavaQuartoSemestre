package br.com.Banco.UI;

import javax.swing.*;
import java.util.List;

import br.com.Banco.app.Inicializador;
import br.com.Banco.app.SalvarDados;
import br.com.Banco.model.Conta;
import br.com.Banco.model.ContaCorrente;

import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {

    private List<ContaCorrente> listaDeContas;

    private CardLayout cardLayout;
    private JPanel mainPanel;

    // Componentes da tela de login
    private JTextField cpfField;
    private JPasswordField senhaField;

    // Componentes da tela de cadastro
    private JTextField novoCpfField;
    private JTextField novoNomeField;
    private JPasswordField novaSenhaField;

    public MainFrame(List<ContaCorrente> listaDeContas) {
        super("Banco - Sistema de Login");
        this.listaDeContas = listaDeContas;

        // Configura janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // JFrame.EXIT_ON_CLOSE indica que o programa deve encerrar
                                                        // totalmente quando a janela for fechada.
        setSize(400, 250); // Tamanho
        setLocationRelativeTo(null); // centraliza
        setResizable(false); // Tela não pode ser aumentada

        cardLayout = new CardLayout(); // Você cria um array de telas, mas só pode mostrar uma de cada vez
        mainPanel = new JPanel(cardLayout); // mainPanel é um array de telas

        // Inicializa telas
        mainPanel.add(createLoginPanel(), "login"); // Cria as telas
        mainPanel.add(createCadastroPanel(), "cadastro");

        add(mainPanel); // Vai inicializar a tela pro usuário
        cardLayout.show(mainPanel, "login"); // mostra a tela de login por padrão
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(); // Cria o painel
        panel.setLayout(null); // layout absoluto, mais controle manual (pode usar outros)

        JLabel labelCpf = new JLabel("CPF:"); // Caixa de texto para o usuário ver
        labelCpf.setBounds(50, 30, 80, 25); // Tamanho
        panel.add(labelCpf); // Adição ao painel

        cpfField = new JTextField(); // Entrada para o usuário digitar o CPF
        cpfField.setBounds(130, 30, 180, 25);
        panel.add(cpfField);

        JLabel labelSenha = new JLabel("Senha:");
        labelSenha.setBounds(50, 70, 80, 25);
        panel.add(labelSenha);

        senhaField = new JPasswordField();
        senhaField.setBounds(130, 70, 180, 25);
        panel.add(senhaField);

        JButton btnLogin = new JButton("Entrar");
        btnLogin.setBounds(50, 120, 120, 30);
        btnLogin.addActionListener(e -> login());
        panel.add(btnLogin);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(190, 120, 120, 30);
        btnCadastrar.addActionListener(e -> cardLayout.show(mainPanel, "cadastro"));
        panel.add(btnCadastrar);

        return panel;
    }

    private JPanel createCadastroPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel labelNovoCpf = new JLabel("CPF:");
        labelNovoCpf.setBounds(50, 30, 80, 25);
        panel.add(labelNovoCpf);

        novoCpfField = new JTextField();
        novoCpfField.setBounds(130, 30, 180, 25);
        panel.add(novoCpfField);

        JLabel labelNovoNome = new JLabel("Nome:");
        labelNovoNome.setBounds(50, 70, 80, 25);
        panel.add(labelNovoNome);

        novoNomeField = new JTextField();
        novoNomeField.setBounds(130, 70, 180, 25);
        panel.add(novoNomeField);

        JLabel labelNovaSenha = new JLabel("Senha:");
        labelNovaSenha.setBounds(50, 110, 80, 25);
        panel.add(labelNovaSenha);

        novaSenhaField = new JPasswordField();
        novaSenhaField.setBounds(130, 110, 180, 25);
        panel.add(novaSenhaField);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(50, 160, 120, 30);
        btnSalvar.addActionListener(e -> salvarCadastro());
        panel.add(btnSalvar);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(190, 160, 120, 30);
        btnVoltar.addActionListener(e -> cardLayout.show(mainPanel, "login"));
        panel.add(btnVoltar);

        return panel;
    }

    private void login() {
        String cpf = cpfField.getText();
        String senha = new String(senhaField.getPassword());

        if (Inicializador.verificacaoDeLogin(cpf, senha, listaDeContas)) {
            this.dispose(); // fecha login
            ContaFrame contaFrame = new ContaFrame(listaDeContas, cpf); // passa dados da conta logada
            contaFrame.setVisible(true);
            return;
        } else {
            JOptionPane.showMessageDialog(this, "Preencha CPF e senha!", "Erro", JOptionPane.ERROR_MESSAGE);

        }

    }

    private void salvarCadastro() {
        String cpf = novoCpfField.getText();
        String senha = new String(novaSenhaField.getPassword());
        String nome = novoNomeField.getText();

        if (cpf.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha CPF e senha!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        listaDeContas.add(new ContaCorrente(cpf, nome, 0, senha));
        SalvarDados novaConta = new SalvarDados();
        novaConta.salvar(listaDeContas);

        JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso para CPF: " + cpf);

        // Limpa campos e volta para login
        novoCpfField.setText("");
        novaSenhaField.setText("");
        cardLayout.show(mainPanel, "login");
    }

    public static void main(String[] args) throws Exception {
        Inicializador inicializador = new Inicializador();
        List<ContaCorrente> contas = inicializador.carregarContas();

        MainFrame frame = new MainFrame(contas);
        frame.setVisible(true);
    }
}
