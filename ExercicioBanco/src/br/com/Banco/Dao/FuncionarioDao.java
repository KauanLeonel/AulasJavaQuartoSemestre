package br.com.Banco.Dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;

import br.com.Banco.model.ContaCorrente;

public class FuncionarioDao {

    public void inserir(ContaCorrente contaCorrente) {
        String sql = " INSERT INTO funcionario(cpf, nome, salario, senha) VALUES (?, ?, ?, ?)";

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
}
