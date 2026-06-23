package j.l.soares.f.controlefacil;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.math.BigDecimal;

@Entity(tableName = "produto_tabela")
public class Produto {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String produto;
    public BigDecimal preco;
    public int qtdEstoque;

    public Produto(String produto, BigDecimal preco, int qtdEstoque) {
        this.produto = produto;
        this.preco = preco;
        this.qtdEstoque = qtdEstoque;
    }

    public int getId() {
        return id;
    }

    public String getProduto() {
        return produto;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public void setId(int id) {
        this.id = id;
    }
}
