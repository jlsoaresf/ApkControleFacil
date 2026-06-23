package j.l.soares.f.controlefacil;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProdutoDao {

    @Insert
    void insert(Produto produto);
    @Update
    void update(Produto produto);
    @Delete
    void delete(Produto produto);

    @Query("SELECT * FROM produto_tabela ORDER BY produto ASC")
    LiveData<List<Produto>> getAllProdutos();
}
