package trabalhointerface.persistencia;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import trabalhointerface.modelo.ProdutoDTO;
import trabalhointerface.util.Mensagens;

public class ProdutoDAO {

    public boolean cadastraProdutoBD(String nomePDTO, float precoPDTO, String iconePDTO) {
        boolean aux = false;
        File file = new File(iconePDTO);
        FileInputStream fileSream;
        try {
            fileSream = new FileInputStream(file);
            String str = "jdbc:mysql://localhost:3307/fat_truck?"
                    + "user=root&password=root";
            Connection conn = DriverManager.getConnection(str);
            String sql = "insert into produto (NOM_PDTO, PRECO_PDTO, ICON_PDTO) values"
                    + " (?, ?, ?)";
            PreparedStatement p = conn.prepareStatement(sql);
            p.setString(1, nomePDTO);
            p.setFloat(2, precoPDTO);
            p.setBinaryStream(3, fileSream, (int) file.length());
            p.execute();
            p.close();
            conn.close();
            aux = true;
        } catch (Exception ex) {
            Mensagens.msgErro("Ocorreu um erro ao cadastrar o produto no banco de dados. \n " + ex.getMessage());
        }
        return aux;
    }

    public boolean alteraProdutoBD(String nomePDTO, Float precoPDTO, Icon iconePDTO, int codigo) {
        boolean aux = false;
        try {
            String str = "jdbc:mysql://localhost:3307/fat_truck?"
                    + "user=root&password=root";
            Connection conn = DriverManager.getConnection(str);
            String sql = "update produto set NOM_PDTO = ?, PRECO_PDTO = ?, ICON_PDTO = ? "
                    + " where COD_PDTO = ?";
            PreparedStatement p = conn.prepareStatement(sql);
            p.setString(1, nomePDTO);
            p.setFloat(2, precoPDTO);
            p.setBytes(3, imageToBlob(iconePDTO));
            p.setInt(4, codigo);
            p.execute();
            p.close();
            conn.close();
            aux = true;
        } catch (Exception ex) {
            Mensagens.msgErro("Ocorreu um erro ao alterar o produto no banco de dados. \n " + ex.getMessage());
        }
        return aux;
    }

    private byte[] imageToBlob(Icon icon) {
        ImageIcon imagem = (ImageIcon) icon;
        BufferedImage newImage = new BufferedImage(imagem.getIconWidth(), imagem.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = newImage.getGraphics();
        g.drawImage(imagem.getImage(), 0, 0, null);
        g.dispose();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(newImage, "png", baos);
            return baos.toByteArray();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean removeProdutoBD(int codigo) {
        boolean aux = false;
        try {
            String str = "jdbc:mysql://localhost:3307/fat_truck?"
                    + "user=root&password=root";
            Connection conn = DriverManager.getConnection(str);
            String sql = "delete from produto where COD_PDTO = ?";
            PreparedStatement p = conn.prepareStatement(sql);
            p.setInt(1, codigo);
            p.execute();
            p.close();
            conn.close();
            aux = true;
        } catch (SQLException ex) {
            Mensagens.msgErro("Ocorreu um erro ao remover o produto do banco de dados. \n " + ex.getMessage());
        }
        return aux;
    }

    public boolean verificaNome(String nome, int cod) {
        boolean aux = false;
        String str = "jdbc:mysql://localhost:3307/FAT_TRUCK?"
                + "user=root&password=root";
        Connection conn;
        try {
            conn = DriverManager.getConnection(str);
            String sql = "select lower(NOM_PDTO) from Produto "
                    + " where NOM_PDTO = ? "
                    + " and COD_PDTO <> ?";
            PreparedStatement p = conn.prepareStatement(sql);
            p.setString(1, nome);
            p.setInt(2, cod);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                aux = true;
            }
            rs.close();
            p.close();
            conn.close();
        } catch (SQLException ex) {
            Mensagens.msgErro("Ocorreu um erro ao verificar o nome do produto no banco de dados. \n " + ex.getMessage());
        }

        return aux;
    }

    public ArrayList<ProdutoDTO> carregaProdutos() {
        ArrayList<ProdutoDTO> listaPDTO = new ArrayList();
        String str = "jdbc:mysql://localhost:3307/fat_truck?"
                + "user=root&password=root";
        Connection conn;
        try {
            conn = DriverManager.getConnection(str);
            String sql = "select COD_PDTO, NOM_PDTO, PRECO_PDTO, ICON_PDTO from Produto";
            PreparedStatement p = conn.prepareStatement(sql);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                ProdutoDTO pp = new ProdutoDTO(rs.getInt(1), rs.getString(2), rs.getFloat(3), blobToImage(rs.getBlob(4), rs.getInt(1)));
                listaPDTO.add(pp);
            }
            rs.close();
            p.close();
            conn.close();
        } catch (SQLException ex) {
            Mensagens.msgErro("Ocorreu um erro ao carregar os produtos do banco de dados. \n " + ex.getMessage());
        }
        return listaPDTO;
    }

    private ImageIcon blobToImage(Blob blob, int cod) {
        ImageIcon icon = null;
        try {
            byte[] imagebytes;
            Image imagem;

            String str = "jdbc:mysql://localhost:3307/FAT_TRUCK?"
                    + "user=root&password=root";
            Connection conn;
            conn = DriverManager.getConnection(str);
            String sql = "select (ICON_PDTO) from Produto where COD_PDTO = ?";
            PreparedStatement p = conn.prepareStatement(sql);
            p.setInt(1, cod);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                imagebytes = rs.getBytes(1);
                imagem = ImageIO.read(new ByteArrayInputStream(imagebytes));
                icon = new ImageIcon(new ImageIcon(imagem).getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT));
            }
            rs.close();
            p.close();
            conn.close();
        } catch (SQLException | IOException ex) {
            Mensagens.msgErro("Ocorreu um erro ao transformar Blob to Image \n " + ex.getMessage());
        }
        return icon;
    }
}
