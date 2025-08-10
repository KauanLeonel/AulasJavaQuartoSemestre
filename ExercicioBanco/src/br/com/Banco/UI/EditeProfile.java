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

        JLabel labelNome = new JLabel("Nome:" + listaDeContas.get(user).getTitular());
        labelNome.setBounds(50, 80, 200, 25);
        panel.add(labelNome);

        nameLabel = new JTextField(listaDeContas.get(user).getTitular());
        nameLabel.setBounds(150, 160, 180, 25);
        panel.add(nameLabel);

        JButton btnSair = new JButton("Cancelar");
        btnSair.setBounds(50, 210, 120, 30);
        btnSair.addActionListener(e -> sair(cpf));
        panel.add(btnSair);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(190, 210, 120, 30);
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
