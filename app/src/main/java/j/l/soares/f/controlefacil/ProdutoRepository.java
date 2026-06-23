package j.l.soares.f.controlefacil;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProdutoRepository {

    private ProdutoDao produtoDao;
    private LiveData<List<Produto>> produtos;

    ExecutorService executors = Executors.newSingleThreadExecutor();

    public ProdutoRepository(Application application) {
        ProdutoDatabase database = ProdutoDatabase.getInstance(application);
        produtoDao = database.produtoDao();
        produtos = produtoDao.getAllProdutos();
    }

    public void insert(Produto produto) {
        executors.execute(new Runnable() {
            @Override
            public void run() {
                produtoDao.insert(produto);
            }
        });
    }

    public void update(Produto produto) {
        executors.execute(new Runnable() {
            @Override
            public void run() {
                produtoDao.update(produto);
            }
        });
    }

    public void delete(Produto produto) {
        executors.execute(new Runnable() {
            @Override
            public void run() {
                produtoDao.delete(produto);
            }
        });
    }

    public LiveData<List<Produto>>  getAllProdutos() {

        return produtos;

    }
}
