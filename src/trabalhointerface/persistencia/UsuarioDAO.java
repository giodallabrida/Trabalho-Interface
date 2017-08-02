package trabalhointerface.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public boolean validaUsuario(
            String user,
            String senha) throws SQLException {
        boolean verifica = false;
        // definição da String de conexão
        String aux = "jdbc:mysql://localhost:3307/fat_truck?"
                + "user=root&password=root";
        // estabelecer a conexão...mysql-connector-java-5.1.42-bin.jar
        Connection conexao = DriverManager.getConnection(aux);
        String sql = "select user_adm, senha_adm from admin"
                + " where user_adm = ? "
                + " and senha_adm = ? ";
        // enviar o select para ser analisado pelo banco
        // de dados...
        PreparedStatement p = conexao.prepareStatement(sql);
        // definir o valor de cada um dos parâmetros...
        p.setString(1, user);
        p.setString(2, senha);
        ResultSet rs = p.executeQuery();
        if (rs.next()) {
            verifica = true;
        }
        return verifica;
    }

    public void alteraLogin(
            String user,
            String senha) throws SQLException {
        boolean verifica = false;
        String aux = "jdbc:mysql://localhost:3307/fat_truck?"
                + "user=root&password=root";
        Connection conexao = DriverManager.getConnection(aux);
        String sql = "update admin set user_adm = ?, senha_adm = ?";

        PreparedStatement p = conexao.prepareStatement(sql);
        // definir o valor de cada um dos parâmetros...
        p.setString(1, user);
        p.setString(2, senha);
        p.execute();
    }

}
