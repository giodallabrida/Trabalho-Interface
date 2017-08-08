package trabalhointerface.modelo;

import javax.swing.ImageIcon;

public class ProdutoDTO {
    private ImageIcon icone;
    private String nome;
    private float preco;

    public ImageIcon getIcone() {
        return icone;
    }

    public void setIcone(ImageIcon icone) {
        this.icone = icone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }
    
    public String[] getLinhaTabela(){
        String[] retorno = {(this.nome), String.valueOf(this.preco), String.valueOf(this.icone)};
        return retorno;
    }

    public ProdutoDTO(String nome, float preco, ImageIcon icone) {
        this.nome = nome;
        this.preco = preco;
        this.icone = icone;
    }
}