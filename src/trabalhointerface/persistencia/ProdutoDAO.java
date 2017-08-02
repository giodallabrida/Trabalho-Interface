package trabalhointerface.persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdutoDAO {

    public void cadastraProdutoBD(String nomePDTO, float precoPDTO, String iconePDTO) throws SQLException, FileNotFoundException {
        File file = new File(iconePDTO);
        FileInputStream fileSream = new FileInputStream(file);
        
        String str = "jdbc:mysql://localhost:3307/fat_truck?"
                + "user=root&password=root";
        // estabelecer a conexão...mysql-connector-java-5.1.42-bin.jar
        Connection conn = DriverManager.getConnection(str);
        String sql = "insert into produto (NOM_PDTO, PRECO_PDTO, ICON_PDTO) values"
                + " (?, ?, ?)";
        // enviar o select para ser analisado pelo banco
        // de dados...
        PreparedStatement p = conn.prepareStatement(sql);
        // definir o valor de cada um dos parâmetros...
        p.setString(1, nomePDTO);
        p.setFloat(2, precoPDTO);
        p.setBinaryStream(3, fileSream, (int) file.length());
        
        p.execute();

        //BD PARA ATRIBUTO
        /*
        Blob blob = rs.getBlob("binImagem");
        byte[] imagebytes = blob.getBytes(1, (int) blob.length());
        BufferedImage imagemBuff = ImageIO.read(new ByteArrayInputStream(imagebytes));
        image = new ImageIcon(new ImageIcon(imagemBuff).getImage().getScaledInstance(136, 135, Image.SCALE_DEFAULT));

         */
    }

    public void removeProduto() {

    }

    public void editaProduto() {

    }

    public boolean verificaNome(String nome) throws SQLException {
       boolean aux = false;
       String str = "jdbc:mysql://localhost:3307/FAT_TRUCK?"
                + "user=root&password=root";
        Connection conn = DriverManager.getConnection(str);
        String sql = "select NOM_PDTO from Produto "+
                     " where NOM_PDTO = ? ";
        PreparedStatement p = conn.prepareStatement(sql);
        p.setString(1, nome);  
        ResultSet rs = p.executeQuery();
        if (rs.next()) {
            aux = true;
        }
       return aux;
    }

}
