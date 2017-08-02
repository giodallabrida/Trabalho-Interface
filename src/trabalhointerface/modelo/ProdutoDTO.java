package trabalhointerface.modelo;

import java.awt.Image;

public class ProdutoDTO {
    private Image icone;
    private String nome;
    private float preco;

    public Image getIcone() {
        return icone;
    }

    public void setIcone(Image icone) {
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

    public ProdutoDTO(String nome, float preco, Image icone) {
        this.nome = nome;
        this.preco = preco;
        this.icone = icone;
    }
}