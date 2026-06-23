package j.l.soares.f.controlefacil;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Produto.class}, version = 1, exportSchema = false)
@TypeConverters({NumericConverter.class})
public abstract class ProdutoDatabase extends RoomDatabase {

    private static ProdutoDatabase instance;

    public abstract ProdutoDao produtoDao();

    public static synchronized ProdutoDatabase getInstance(Context context) {

        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), ProdutoDatabase.class
                    , "produto_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }
}
