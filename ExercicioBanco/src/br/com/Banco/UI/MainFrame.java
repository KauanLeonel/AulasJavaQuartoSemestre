package br.com.Banco.UI;

import javax.swing.*;
import java.util.List;

import br.com.Banco.app.Inicializador;
import br.com.Banco.app.ReproduzirAudios;
import br.com.Banco.app.SalvarDados;
import br.com.Banco.app.SearchCpf;
import br.com.Banco.app.validacaoCpf;
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
    ReproduzirAudios audio = new ReproduzirAudios();
    SearchCpf procurar = new SearchCpf();

    public MainFrame(List<ContaCorrente> listaDeContas) {
        super("Banco - Sistema de Login");
        this.listaDeContas = listaDeContas;

        // Áudio
        audio.reproduzirAudios("src\\br\\com\\Banco\\Audios\\welcome.wav");

        // Configura janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // JFrame.EXIT_ON_CLOSE indica que o programa deve encerrar
                                                        // totalmente quando a janela for fechada.
        setSize(400, 350); // Tamanho
        setLocationRelativeTo(null); // centraliza
        setResizable(true); // Tela não pode ser aumentada

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
        panel.setBackground(Color.decode("#FBF2B7"));

        ImageIcon icon = new ImageIcon(
                getClass().getResource(
                        "/br\\com\\Banco\\imgs\\c9486f2a-c84b-4128-9967-164aa7749ccb-removebg-preview.png"));
        Image img = icon.getImage();
        Image resized = img.getScaledInstance(200, 100, java.awt.Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resized);
        JLabel labelImagem = new JLabel(resizedIcon);
        labelImagem.setBounds(100, 00, 200, 130); // posição e tamanho da imagem

        panel.add(labelImagem);

        JLabel labelCpf = new JLabel("CPF:"); // Caixa de texto para o usuário ver
        labelCpf.setBounds(50, 130, 80, 25); // Tamanho
        panel.add(labelCpf); // Adição ao painel

        cpfField = new JTextField(); // Entrada para o usuário digitar o CPF
        cpfField.setBounds(130, 130, 180, 25);
        panel.add(cpfField);

        JLabel labelSenha = new JLabel("Senha:");
        labelSenha.setBounds(50, 170, 80, 25);
        panel.add(labelSenha);

        senhaField = new JPasswordField();
        senhaField.setBounds(130, 170, 180, 25);
        panel.add(senhaField);

        JButton btnLogin = new JButton("Entrar");
        btnLogin.setBounds(50, 220, 120, 30);
        btnLogin.addActionListener(e -> login());
        panel.add(btnLogin);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(190, 220, 120, 30);
        btnCadastrar.addActionListener(e -> cardLayout.show(mainPanel, "cadastro"));
        panel.add(btnCadastrar);

        return panel;
    }

    private JPanel createCadastroPanel() {
        JPanel panel = new JPanel();
        panel.setMinimumSize(new Dimension(500, 400));

        panel.setBackground(Color.decode("#FBF2B7"));
        panel.setLayout(new GridBagLayout()); // responsividade
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // espaçamento entre os componentes
        gbc.fill = GridBagConstraints.HORIZONTAL; // componentes se expandem horizontalmente

        ImageIcon icon = new ImageIcon(getClass().getResource("/br/com/Banco/imgs/logo.png"));
        setIconImage(icon.getImage());

        JLabel labelNovoCpf = new JLabel("CPF:");
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(labelNovoCpf, gbc);

        JLabel cadastro = new JLabel("Cadastro");
        cadastro.setFont(new Font("Arial", Font.BOLD, 30));
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(cadastro, gbc);

        novoCpfField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(novoCpfField, gbc);

        JLabel labelNovoNome = new JLabel("Nome:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(labelNovoNome, gbc);

        novoNomeField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(novoNomeField, gbc);

        JLabel labelNovaSenha = new JLabel("Senha:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(labelNovaSenha, gbc);

        novaSenhaField = new JPasswordField();
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(novaSenhaField, gbc);

        JButton btnSalvar = new JButton("Salvar");
        gbc.gridx = 0;
        gbc.gridy = 4;
        btnSalvar.addActionListener(e -> salvarCadastro());
        panel.add(btnSalvar, gbc);

        JButton btnVoltar = new JButton("Voltar");
        gbc.gridx = 1;
        gbc.gridy = 4;
        btnVoltar.addActionListener(e -> cardLayout.show(mainPanel, "login"));
        panel.add(btnVoltar, gbc);

        return panel;
    }

    private void login() {
        //Filtro filter = new Filtro();
        //filter.SalarioMaiorQueDezMil(listaDeContas);
        String cpf = cpfField.getText();
        String senha = new String(senhaField.getPassword());

        if (Inicializador.verificacaoDeLogin(cpf, senha, listaDeContas)) {
            audio.reproduzirAudios("src\\br\\com\\Banco\\Audios\\login.wav");

            this.dispose(); // fecha login
            ContaFrame contaFrame = new ContaFrame(listaDeContas, cpf); // passa dados da conta logada
            contaFrame.setVisible(true);
            return;
        } else {
            audio.reproduzirAudios("src\\br\\com\\Banco\\Audios\\algoDeuErrado.wav");

            JOptionPane.showMessageDialog(this, "Preencha CPF e senha!", "Erro", JOptionPane.ERROR_MESSAGE);

        }

    }

    private void salvarCadastro() {
        String cpf = novoCpfField.getText();
        String senha = new String(novaSenhaField.getPassword());
        String nome = novoNomeField.getText();

        if (cpf.isEmpty() || senha.isEmpty()) {
            audio.reproduzirAudios("src\\br\\com\\Banco\\Audios\\algoDeuErrado.wav");
            JOptionPane.showMessageDialog(this, "Preencha CPF e senha!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        validacaoCpf vali = new validacaoCpf();
        if (procurar.search(listaDeContas, cpf) == -1){
            JOptionPane.showMessageDialog(this, " CPF já cadastrado!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
            if (vali.validacaoCpf(cpf)) {

                listaDeContas.add(new ContaCorrente(cpf, nome, 0, senha));
                SalvarDados novaConta = new SalvarDados();
                novaConta.salvar(listaDeContas);

                JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso para CPF: " + cpf);
                novoCpfField.setText("");
                novaSenhaField.setText("");
                cardLayout.show(mainPanel, "login");
            } else {
                JOptionPane.showMessageDialog(this, "CPF inválido", "Erro", JOptionPane.ERROR_MESSAGE);

            }
        

        // Limpa campos e volta para login

    }

    public static void main(String[] args) throws Exception {
        Inicializador inicializador = new Inicializador();
        List<ContaCorrente> contas = inicializador.carregarContas();

        MainFrame frame = new MainFrame(contas);
        frame.setVisible(true);
    }
}
