package trabalhointerface.persistencia;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import trabalhointerface.modelo.ProdutoDTO;

public class ProdutoDAO {

    public void cadastraProdutoBD(String nomePDTO, float precoPDTO, String iconePDTO) {
        File file = new File(iconePDTO);
        FileInputStream fileSream;
        try {
            fileSream = new FileInputStream(file);
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
        } catch (FileNotFoundException | SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
 
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

    public boolean verificaNome(String nome) {
        boolean aux = false;
        String str = "jdbc:mysql://localhost:3307/FAT_TRUCK?"
                + "user=root&password=root";
        Connection conn;
        try {
            conn = DriverManager.getConnection(str);
            String sql = "select NOM_PDTO from Produto "
                    + " where NOM_PDTO = ? ";
            PreparedStatement p = conn.prepareStatement(sql);
            p.setString(1, nome);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                aux = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return aux;
    }

    public ArrayList<ProdutoDTO> carregaProdutos() {
        ArrayList<ProdutoDTO> listaPDTO = new ArrayList();
        String str = "jdbc:mysql://localhost:3307/FAT_TRUCK?"
                + "user=root&password=root";
        Connection conn;
        try {
            conn = DriverManager.getConnection(str);
            String sql = "select NOM_PDTO, PRECO_PDTO, ICON_PDTO from Produto";
            PreparedStatement p = conn.prepareStatement(sql);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                ProdutoDTO pp = new ProdutoDTO(rs.getString(1), rs.getFloat(2), blobToImage(rs.getBlob(3)));
                listaPDTO.add(pp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaPDTO;
    }

    private ImageIcon blobToImage(Blob blob) {
        byte[] imagebytes;
        ImageIcon imagem = null;
        try {
            imagebytes = blob.getBytes(1, (int) blob.length());
            BufferedImage imagemBuff = ImageIO.read(new ByteArrayInputStream(imagebytes));
            imagem = new ImageIcon(new ImageIcon(imagemBuff).getImage().getScaledInstance(136, 135, Image.SCALE_DEFAULT));

        } catch (SQLException | IOException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return imagem;
    }

}
