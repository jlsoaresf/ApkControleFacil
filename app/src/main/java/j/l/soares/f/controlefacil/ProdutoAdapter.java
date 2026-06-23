package j.l.soares.f.controlefacil;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.ProdutoHolder> {

    private List<Produto> produtos = new ArrayList<>();

    @NonNull
    @Override
    public ProdutoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.produto_item, parent, false);
        return new ProdutoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdutoHolder holder, int position) {

        Produto currentProduto = produtos.get(position);
        holder.textViewProduto.setText(currentProduto.getProduto());
        holder.textViewPreco.setText(currentProduto.getPreco().toPlainString());
        holder.textViewQuantidade.setText(""+currentProduto.getQtdEstoque());
    }

    @Override
    public int getItemCount() {

        return produtos.size();
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
        notifyDataSetChanged();
    }

    public Produto getProdutos(int position) {

        return  produtos.get(position);
    }

    class ProdutoHolder extends RecyclerView.ViewHolder {

        TextView textViewProduto;
        TextView textViewPreco;
        TextView textViewQuantidade;

        public ProdutoHolder(@NonNull View itemView) {
            super(itemView);

            textViewProduto = itemView.findViewById(R.id.textViewProduto);
            textViewPreco = itemView.findViewById(R.id.textViewPreco);
            textViewQuantidade = itemView.findViewById(R.id.textViewQuantidade);
        }
    }
}
