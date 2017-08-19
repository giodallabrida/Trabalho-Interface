package trabalhointerface.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import trabalhointerface.persistencia.UsuarioDTO;

public class UsuarioDAO {
    
    public UsuarioDTO validaUsuario(
            String user,
            String senha) throws SQLException {
        // definição da String de conexão
        String aux = "jdbc:mysql://localhost/fat_truck?"
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
        UsuarioDTO usuarioDTO = null;
        if (rs.next()) {
            usuarioDTO = new UsuarioDTO();
            usuarioDTO.setUser(rs.getString(2));
            usuarioDTO.setSenha(rs.getString(3));
        }
        return usuarioDTO;
    }


    public void alteraLogin() {

    }
}
