package j.l.soares.f.controlefacil;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.math.BigDecimal;
import java.util.List;

public class ProdutoActivity extends AppCompatActivity {

    ProdutoViewModel produtoViewModel;

    ActivityResultLauncher<Intent> activityResultLauncherForAddProduto;
    ActivityResultLauncher<Intent> activityResultLauncherForUpdateProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_produto);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        registerActivityForAddProduto();
        registerActivityForUpdateProduto();

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Produto");
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ProdutoAdapter adapter = new ProdutoAdapter();
        recyclerView.setAdapter(adapter);

        produtoViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication())
                .create(ProdutoViewModel.class);
        produtoViewModel.getAllProdutos().observe(this, new Observer<List<Produto>>() {
            @Override
            public void onChanged(List<Produto> produtos) {

                adapter.setProdutos(produtos);

            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                produtoViewModel.delete(adapter.getProdutos(viewHolder.getAdapterPosition()));
                Toast.makeText(getApplicationContext(), "Produto apagado", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new ProdutoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Produto produto) {

                Intent intent = new Intent(ProdutoActivity.this, UpdateProdutoActivity.class);

                intent.putExtra("id", produto.getId());
                intent.putExtra("nomeProduto", produto.getProduto());
                intent.putExtra("preco", produto.getPreco().toString());
                intent.putExtra("quantidade", String.valueOf(produto.getQtdEstoque()));

                activityResultLauncherForUpdateProduto.launch(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_produto, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.top_menu) {
            Intent intent = new Intent(ProdutoActivity.this, AddProdutoActivity.class);
            activityResultLauncherForAddProduto.launch(intent);
            return true;
        } else {
                return super.onOptionsItemSelected(item);
        }
    }

    public void registerActivityForUpdateProduto() {

        activityResultLauncherForUpdateProduto = registerForActivityResult(new ActivityResultContracts.StartActivityForResult()
                , new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {

                        int resultCode = result.getResultCode();
                        Intent data = result.getData();

                        if(resultCode == RESULT_OK && data != null) {

                            String nomeProduto = data.getStringExtra("upNomeProduto");
                            BigDecimal preco = NumericConverter.toBigDecimal(data.getStringExtra("upPreco"));
                            Integer quantidade = Integer.parseInt(data.getStringExtra("upQuantidade"));
                            int id = data.getIntExtra("pId", -1);
                            Produto produto = new Produto(nomeProduto, preco, quantidade);
                            produto.setId(id);
                            produtoViewModel.update(produto);

                        }

                    }
                });

    }
    public void registerActivityForAddProduto() {
        activityResultLauncherForAddProduto = registerForActivityResult(new ActivityResultContracts.StartActivityForResult()
                , new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        int resultCode = result.getResultCode();
                        Intent data = result.getData();

                        if(resultCode == RESULT_OK && data != null) {
                            String nomeProduto = data.getStringExtra("svNomeProduto");
                            BigDecimal preco = NumericConverter.toBigDecimal(data.getStringExtra("svPreco"));
                            Integer quantidade = Integer.parseInt(data.getStringExtra("svQuantidade"));

                            Produto produto = new Produto(nomeProduto, preco, quantidade);
                            produtoViewModel.insert(produto);
                        }
                    }
                });
    }
}