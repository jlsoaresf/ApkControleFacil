package j.l.soares.f.controlefacil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UpdateProdutoActivity extends AppCompatActivity {

    EditText nomeProduto;
    EditText quantidade;
    EditText preco;
    Button salvar;
    Button cancelar;
    int pId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_produto);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toolbar toolbar = findViewById(R.id.toolbaUpdateProduto);
        toolbar.setTitle("Atualizar Produto");

        nomeProduto = findViewById(R.id.editUpdateProduto);
        preco = findViewById(R.id.editUpdatePreco);
        quantidade = findViewById(R.id.editUpdateQuant);

        salvar = findViewById(R.id.btnSalvarUpdate);
        cancelar = findViewById(R.id.btnCancelarUpdate);

        getData();

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Cancelado", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProduto();
            }
        });
    }

    private void updateProduto() {

        String upNomeProduto = nomeProduto.getText().toString();
        String upPreco = preco.getText().toString();
        String upQuantidade = quantidade.getText().toString();

        Intent intent = new Intent();
        intent.putExtra("upNomeProduto", upNomeProduto);
        intent.putExtra("upPreco", upPreco);
        intent.putExtra("upQuantidade", upQuantidade);

        if(pId != -1) {
            intent.putExtra("pId", pId);

            setResult(RESULT_OK, intent);
            Toast.makeText(getApplicationContext(), "Produto Atualizado", Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    public void getData() {

        Intent intent = getIntent();
        pId = intent.getIntExtra("id", -1);
        String pNomeProduto = intent.getStringExtra("nomeProduto");
        String pPreco = intent.getStringExtra("preco");
        String pQuantidade = intent.getStringExtra("quantidade");

        nomeProduto.setText(pNomeProduto);
        preco.setText(pPreco);
        quantidade.setText(pQuantidade);

    }

}