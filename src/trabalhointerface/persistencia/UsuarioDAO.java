package trabalhointerface.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import trabalhointerface.util.Mensagens;

public class UsuarioDAO {

    public boolean validaUsuario(
            String user,
            String senha) {
        boolean verifica = false;
        String aux = "jdbc:mysql://localhost:3307/fat_truck?"
                + "user=root&password=root";
        Connection conexao;
        try {
            conexao = DriverManager.getConnection(aux);
            String sql = "select user_adm, senha_adm from admin"
                    + " where user_adm = ? "
                    + " and senha_adm = ? ";
            PreparedStatement p = conexao.prepareStatement(sql);
            p.setString(1, user);
            p.setString(2, senha);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                verifica = true;
            }
            rs.close();
            p.close();
            conexao.close();
        } catch (SQLException ex) {
            Mensagens.msgErro("Ocorreu um erro ao validar o login. \n " + ex.getMessage());
        }
        return verifica;
    }

    public void alteraLogin(
            String user,
            String senha) {
        String aux = "jdbc:mysql://localhost:3307/fat_truck?"
                + "user=root&password=root";
        Connection conexao;
        try {
            conexao = DriverManager.getConnection(aux);
            String sql = "update admin set user_adm = ?, senha_adm = ?";

            PreparedStatement p = conexao.prepareStatement(sql);
            p.setString(1, user);
            p.setString(2, senha);
            p.execute();
            p.close();
            conexao.close();
        } catch (SQLException ex) {
            Mensagens.msgErro("Ocorreu um erro ao alterar o login. \n " + ex.getMessage());
        }
    }
}