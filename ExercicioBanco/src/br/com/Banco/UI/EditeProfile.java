package br.com.Banco.UI;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.com.Banco.app.SalvarDados;
import br.com.Banco.app.SearchCpf;
import br.com.Banco.model.ContaCorrente;

public class EditeProfile extends JFrame{
        private List<ContaCorrente> listaDeContas;
        private JTextField nameLabel;
        private JTextField password;
        private JTextField newPassword;
        private JTextField newPasswordConfirm;

        public EditeProfile(List<ContaCorrente> listaDeContas,  String cpf) {
        super("Editar perfil");
        this.listaDeContas = listaDeContas;


             setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 350);
        setLocationRelativeTo(null);
        setResizable(false);

        SearchCpf busca = new SearchCpf();
        int user = busca.search(listaDeContas, cpf);

        JPanel panel = new JPanel();
        panel.setLayout(null);


        JLabel labelCpf = new JLabel("CPF:" + listaDeContas.get(user).getCpf());
        labelCpf.setBounds(50, 30, 200, 25);
        panel.add(labelCpf);

        JLabel labelNome = new JLabel("Nome:");
        labelNome.setBounds(50, 80, 200, 25);
        panel.add(labelNome);

        nameLabel = new JTextField(listaDeContas.get(user).getTitular());
        nameLabel.setBounds(150, 80, 180, 25);
        panel.add(nameLabel);

        JLabel labelPassword = new JLabel("Senha:" );
        labelPassword.setBounds(50, 120, 200, 25);
        panel.add(labelPassword);

        password = new JTextField();
        password.setBounds(150, 120, 180, 25);
        panel.add(password);

        JLabel labelNewPassword = new JLabel("Nova senha:" );
        labelNewPassword.setBounds(50, 150, 200, 25);
        panel.add(labelNewPassword);

        newPassword = new JTextField();
        newPassword.setBounds(150, 150, 180, 25);
        panel.add(newPassword);

        JLabel labelNewPassawordConfirm = new JLabel("Confime a nova senha:");
        labelNewPassawordConfirm.setBounds(50, 180, 200, 25);
        panel.add(labelNewPassawordConfirm);

        newPasswordConfirm = new JTextField();
        newPasswordConfirm.setBounds(150, 180, 180, 25);
        panel.add(newPasswordConfirm);

        JButton btnSair = new JButton("Cancelar");
        btnSair.setBounds(50, 220, 120, 30);
        btnSair.addActionListener(e -> sair(cpf));
        panel.add(btnSair);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(190, 220, 120, 30);
        btnSalvar.addActionListener(e -> salvar(cpf));

        panel.add(btnSalvar);

        add(panel);

    }
     private void sair(String cpf) {
        this.dispose(); // fecha login
        ContaFrame ContaFrame = new ContaFrame(listaDeContas, cpf);
        ContaFrame.setVisible(true);
        return;
    }

     private void salvar(String cpf) {
        String nome = nameLabel.getText();
        SearchCpf busca = new SearchCpf();
        int user = busca.search(listaDeContas, cpf);
        listaDeContas.get(user).setTitular(nome);
        SalvarDados save = new SalvarDados();
        save.salvar(listaDeContas);
        this.dispose(); // fecha login
        ContaFrame ContaFrame = new ContaFrame(listaDeContas, cpf);
        ContaFrame.setVisible(true);
        return;
    }
}
