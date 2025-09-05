package br.com.Banco.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import br.com.Banco.Dao.ContaCorrenteDao;

import br.com.Banco.model.ContaCorrente;

public class EditeProfile extends JFrame {

    private JTextField nameLabel;
    private JTextField password;
    private JTextField newPassword;
    static ContaCorrenteDao dao = new ContaCorrenteDao();

    //#region  UI
    public EditeProfile( String cpf) {
        super("Editar perfil");

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

        ContaCorrente user = dao.buscaEspecifica(cpf);

        JLabel labelCpf = new JLabel("CPF:" + user.getCpf());
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(labelCpf, gbc);

        JLabel labelNome = new JLabel("Nome:");
        gbc.gridwidth = 1;

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(labelNome, gbc);

        nameLabel = new JTextField(user.getTitular());
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
        btnSair.setBackground(new Color(221,95,96));
        btnSair.setFocusPainted(false);
        panel.add(btnSair, gbc);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBackground(new Color(110,206,118));
        gbc.gridx = 2;
        gbc.gridy = 5;
        btnSalvar.addActionListener(e -> verificacao(password, newPassword, newPasswordConfirm, cpf));

        panel.add(btnSalvar, gbc);


        JButton btnExcluir = new JButton("Excluir");
        gbc.gridwidth = 2;
        gbc.gridx = 1;
        gbc.gridy = 6;
        btnExcluir.addActionListener(e -> excluir(cpf));
        btnExcluir.setBackground(new Color(221,95,96));
        panel.add(btnExcluir, gbc);
        add(panel);

    }

    //#endregion

    //#region FUNÇÕES 
    
    private void sair(String cpf) {
        if(cpf.equals("0")){
        this.dispose(); // fecha login
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
        } else{
        this.dispose(); // fecha login
        ContaFrame ContaFrame = new ContaFrame(cpf);
        ContaFrame.setVisible(true);
        return;
        }
    }

    public void excluir(String cpf){
        dao.remover(cpf);
        cpf = "0";
        sair(cpf); 
    }

    private void verificacao(JTextField password, JTextField newPassword, JTextField newPasswordConfirm, String cpf) {
        String senha = password.getText();
        String novaSenha = newPassword.getText();
        String novaSenhaConfirmacao = newPasswordConfirm.getText();
        ContaCorrente user = dao.buscaEspecifica(cpf);
        if (senha.isEmpty() && novaSenha.isEmpty() && novaSenhaConfirmacao.isEmpty()) {
            newPassword.setText(user.getSenha());
            salvar(cpf);
        } else {

            if (senha.equals(user.getSenha()) && novaSenha.equals(novaSenhaConfirmacao)
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
        ContaCorrente user = dao.buscaEspecifica(cpf);
        user.setSenha(senha);
        user.setTitular(nome);
        dao.atualizar(user);
        this.dispose(); // fecha login
        ContaFrame ContaFrame = new ContaFrame(cpf);
        ContaFrame.setVisible(true);
        return;
    }
    //#endregion
}
