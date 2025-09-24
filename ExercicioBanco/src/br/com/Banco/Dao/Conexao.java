package br.com.Banco.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
   private static final String URL = "jdbc:mysql://localhost:3306/contas";
   private static final String USER = "root";
   //private static final String PASSWORD = "Aluno"; // No trabalho
   private static final String PASSWORD = "aluno"; // No IF

   public Conexao() {
   }

   public static Connection getConnection() throws SQLException {
      return DriverManager.getConnection(URL, USER, PASSWORD);
   }
}