package br.com.Banco.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransferenciaDao {

    ContaCorrenteDao dao = new ContaCorrenteDao();

    public void transferir(String cpf, String cpfRecebedor, double valor) {
        String debitoSql = "UPDATE contacorrente set saldo = saldo - ? where cpf = ?";
        String creditoSql = "UPDATE contacorrente set saldo = saldo + ? where cpf = ?";

        try (Connection conn = Conexao.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement debito = conn.prepareStatement(debitoSql);
                    PreparedStatement credito = conn.prepareStatement(creditoSql)) {
                debito.setDouble(1, valor);
                debito.setString(2, cpf);
                debito.executeUpdate();

                credito.setDouble(1, valor);
                credito.setString(2, cpfRecebedor);
                credito.executeUpdate();

                conn.commit();
                System.out.println("Transferência realizada com sucesso");
            } catch (Exception e) {
                conn.rollback();
                System.err.println("Erro na transação. Rollback realizado!");
                System.out.println("Erro ao transferir bônus: " + e.getMessage());
            } finally {
                conn.setAutoCommit(true); // volta ao padrão
            }
        } catch (SQLException e) {
            System.out.println("Erro ao transferir bônus: " + e.getMessage());

        }
    }
}
