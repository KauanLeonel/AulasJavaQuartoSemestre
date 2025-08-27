package br.com.Banco.Dao;

import java.util.ArrayList;
import java.sql.*;
import java.util.List;

import br.com.Banco.model.ContaCorrente;

public class ContaCorrenteDao {

    // CREATE
    public void inserir(ContaCorrente contaCorrente) {
        String sql = " INSERT INTO contas(cpf, nome, saldo, senha) VALUES (?, ?, ?, ?)";

        try {
            Connection conn = Conexao.getConnection();
            try {
                PreparedStatement stmt = conn.prepareStatement(sql);

                try {
                    stmt.setString(1, contaCorrente.getCpf());
                    stmt.setString(2, contaCorrente.getTitular());
                    stmt.setDouble(3, contaCorrente.getSaldo());
                    stmt.setString(4, contaCorrente.getSenha());
                } catch (Throwable var9) {
                    if (stmt != null) {
                        try {
                            stmt.close();
                        } catch (Throwable var8) {
                            var9.addSuppressed(var8);
                        }
                    }

                    throw var9;
                }

                if (stmt != null) {
                    stmt.close();
                }
            } catch (Throwable var10) {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (Throwable var7) {
                        var10.addSuppressed(var7);
                    }

                }
                throw var10;
            }

            if (conn != null) {
                conn.close();
            }
        } catch (SQLException var11) {
            var11.printStackTrace();
        }

    }

    // UPTADE
    public void atualizar(ContaCorrente contaCorrente) {
        String sql = "UPTADE contas SET nome=?, saldo =?, senha=? WHERE cpf =?";
        try (Connection conn = Conexao.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, contaCorrente.getTitular());
            stmt.setDouble(2, contaCorrente.getSaldo());
            stmt.setString(3, contaCorrente.getSenha());
            stmt.setString(4, contaCorrente.getCpf());
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // DELETAR
    public void remover(String cpf) {
        String sql = "DELETE from contas WHERE cpf = ?";
        try (Connection conn = Conexao.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // LISTAR

    public List<ContaCorrente> listar() {
        List<ContaCorrente> contasCorrentes = new ArrayList<>();
        String sql = "SELECT * from contas";
        try (
                Connection conn = Conexao.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                ContaCorrente f = new ContaCorrente(
                        rs.getString("cpf"),
                        rs.getString("nome"),
                        rs.getDouble("saldo"),
                        rs.getString("senha")

                );
                contasCorrentes.add(f);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contasCorrentes;
    }

    // Buscar conta específica
    public ContaCorrente buscaEspecifica(String cpf) {
        String sql = "SELECT * from contas Where cpf = ?";
        ContaCorrente conta = new ContaCorrente("ERROR", cpf, 0, sql); // Para conseguir retornar
        try (
                Connection conn = Conexao.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery(sql)) {
            conta = new ContaCorrente(
                    rs.getString("cpf"),
                    rs.getString("nome"),
                    rs.getDouble("saldo"),
                    rs.getString("senha"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return conta;

    }
}
