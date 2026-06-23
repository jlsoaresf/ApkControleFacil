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

import java.math.BigDecimal;

public class AddProdutoActivity extends AppCompatActivity {

    EditText nomeProduto;
    EditText quantidade;
    EditText preco;
    Button salvar;
    Button cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_produto);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toolbar toolbar = findViewById(R.id.toolbaAddProduto);
        toolbar.setTitle("Novo Produto");

        nomeProduto = findViewById(R.id.editAddProduto);
        preco = findViewById(R.id.editAddPreco);
        quantidade = findViewById(R.id.editAddQuant);

        salvar = findViewById(R.id.btnSalvar);
        cancelar = findViewById(R.id.btnCancelar);

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
                salvarProduto();
            }
        });

    }

    public void salvarProduto() {
        String svNomeProduto = nomeProduto.getText().toString();
        String svPreco = preco.getText().toString();
        String svQuantidade = quantidade.getText().toString();

        Intent i = new Intent();
        i.putExtra("svNomeProduto", svNomeProduto);
        i.putExtra("svPreco", svPreco);
        i.putExtra("svQuantidade", svQuantidade);

        setResult(RESULT_OK, i);
        finish();
    }
}