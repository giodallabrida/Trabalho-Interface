package trabalhointerface.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import trabalhointerface.persistencia.UsuarioDTO;
import trabalhointerface.util.Validacao;

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

    public boolean fazLogin(JTextField user, JPasswordField senha) {
        // validar nome de usuário e senha - não vazios...
        boolean aux = false;
        if (Validacao.validaCampo(user)
                && Validacao.validaSenha(senha)) {
            if (user.getText().equals("juca")
                    && String.copyValueOf(senha.getPassword()).equals("juca")) {
                // chamar o menu principal...
                aux = true;
            }
        }
        return aux;
    }

    public void alteraLogin() {

    }
}
