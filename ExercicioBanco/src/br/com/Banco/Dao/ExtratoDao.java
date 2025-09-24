package br.com.Banco.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExtratoDao {
    //Listar todos os extratos
    public ArrayList<String> listarExtrato(String cpf) {
        ArrayList<String> extrato = new ArrayList<>();
        String sql = "select * From movimentacoes where cpf = ? order by dia;";

        try (
                Connection conn = Conexao.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                String f = "| CPF: " + rs.getString("cpf") + " | TIPO: " + rs.getString("tipo") + " | VALOR:  "
                        + rs.getDouble("valor") + " | DATA: " + rs.getString("dia") + " |";
                extrato.add(f);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return extrato;
    }
}
