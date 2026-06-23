package j.l.soares.f.controlefacil;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ProdutoViewModel extends AndroidViewModel {

    private ProdutoRepository repository;
    private LiveData<List<Produto>> produtos;

    public ProdutoViewModel(@NonNull Application application) {
        super(application);

        repository = new ProdutoRepository(application);
        produtos = repository.getAllProdutos();
    }

    public void insert(Produto produto) {
        repository.insert(produto);
    }

    public void update(Produto produto) {
        repository.update(produto);
    }

    public void delete(Produto produto) {
        repository.delete(produto);
    }

    public LiveData<List<Produto>> getAllProdutos() {
        return produtos;
    }
}
