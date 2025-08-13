package br.com.Banco.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import br.com.Banco.app.SalvarDados;
import br.com.Banco.app.SearchCpf;
import br.com.Banco.model.ContaCorrente;

public class EditeProfile extends JFrame {
    private List<ContaCorrente> listaDeContas;
    private JTextField nameLabel;
    private JTextField password;
    private JTextField newPassword;
    private JTextField newPasswordConfirm;

    public EditeProfile(List<ContaCorrente> listaDeContas, String cpf) {
        super("Editar perfil");
        this.listaDeContas = listaDeContas;

        ImageIcon icon = new ImageIcon(getClass().getResource("/br/com/Banco/imgs/logo.png"));
        setIconImage(icon.getImage());
        JPanel panel = new JPanel();

        panel.setMinimumSize(new Dimension(500, 400));
        setSize(500, 400); // Tamanho da janela quando abrir
        setLocationRelativeTo(null); // Janela começa no meio

        panel.setBackground(Color.decode("#FBF2B7"));
        panel.setLayout(new GridBagLayout()); // responsividade
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // espaçamento entre os componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        SearchCpf busca = new SearchCpf();
        int user = busca.search(listaDeContas, cpf);

        JLabel labelCpf = new JLabel("CPF:" + listaDeContas.get(user).getCpf());
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(labelCpf, gbc);

        JLabel labelNome = new JLabel("Nome:");
        gbc.gridwidth = 1;

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(labelNome, gbc);

        nameLabel = new JTextField(listaDeContas.get(user).getTitular());
        gbc.gridx = 2;
        gbc.gridy = 1;
        panel.add(nameLabel, gbc);

        JLabel labelPassword = new JLabel("Senha:");
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(labelPassword, gbc);

        password = new JTextField();
        gbc.gridx = 2;
        gbc.gridy = 2;
        panel.add(password, gbc);

        JLabel labelNewPassword = new JLabel("Nova senha:");
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(labelNewPassword, gbc);

        newPassword = new JTextField();
        gbc.gridx = 2;
        gbc.gridy = 3;
        panel.add(newPassword, gbc);

        JLabel labelNewPassawordConfirm = new JLabel("Confime a nova senha:");
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(labelNewPassawordConfirm, gbc);

        JPasswordField newPasswordConfirm = new JPasswordField();
        //Fazer uma checkbox para alterar o boolean
        // newPasswordConfirm.setEchoChar('*'); //Para esconder a senha com *
        // newPasswordConfirm.setEchoChar('\u0000'); //Para mostrar a senha
        gbc.gridx = 2;
        gbc.gridy = 4;
        panel.add(newPasswordConfirm, gbc);


        JButton btnSair = new JButton("Cancelar");
        gbc.gridx = 1;
        gbc.gridy = 5;
        btnSair.addActionListener(e -> sair(cpf));
        panel.add(btnSair, gbc);

        JButton btnSalvar = new JButton("Salvar");
        gbc.gridx = 2;
        gbc.gridy = 5;
        btnSalvar.addActionListener(e -> verificacao(password, newPassword, newPasswordConfirm, cpf));

        panel.add(btnSalvar, gbc);

        add(panel);

    }

    private void sair(String cpf) {
        this.dispose(); // fecha login
        ContaFrame ContaFrame = new ContaFrame(listaDeContas, cpf);
        ContaFrame.setVisible(true);
        return;
    }

    private void verificacao(JTextField password, JTextField newPassword, JTextField newPasswordConfirm, String cpf) {
        String senha = password.getText();
        String novaSenha = newPassword.getText();
        String novaSenhaConfirmacao = newPasswordConfirm.getText();
        SearchCpf busca = new SearchCpf();
        int user = busca.search(listaDeContas, cpf);
        if (senha.isEmpty() && novaSenha.isEmpty() && novaSenhaConfirmacao.isEmpty()) {
            newPassword.setText(listaDeContas.get(user).getSenha());
            salvar(cpf);
        } else {

            if (senha.equals(listaDeContas.get(user).getSenha()) && novaSenha.equals(novaSenhaConfirmacao)
                    && !novaSenha.isEmpty() && !novaSenhaConfirmacao.isEmpty()) {
                salvar(cpf);
            } else {
                JOptionPane.showMessageDialog(this, "Algo está errado", "Erro", JOptionPane.ERROR_MESSAGE);

            }
        }
    }

    private void salvar(String cpf) {
        String nome = nameLabel.getText();
        String senha = newPassword.getText();
        SearchCpf busca = new SearchCpf();
        int user = busca.search(listaDeContas, cpf);
        listaDeContas.get(user).setSenha(senha);
        listaDeContas.get(user).setTitular(nome);
        SalvarDados save = new SalvarDados();
        save.salvar(listaDeContas);
        this.dispose(); // fecha login
        ContaFrame ContaFrame = new ContaFrame(listaDeContas, cpf);
        ContaFrame.setVisible(true);
        return;
    }
}
