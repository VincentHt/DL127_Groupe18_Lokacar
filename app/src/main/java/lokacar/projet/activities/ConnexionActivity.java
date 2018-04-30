package lokacar.projet.activities;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.Toast;
        import android.widget.Toolbar;

        import lokacar.projet.ActionsChoiceActivity;
        import lokacar.projet.R;
        import lokacar.projet.dal.vehicules.VehiculeDAO;

public class ConnexionActivity extends AppCompatActivity {

    private final String login = "Tonton";
    private final String password = "fuck";
    private EditText loginField;
    private EditText passwordField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VehiculeDAO dao = new VehiculeDAO(ConnexionActivity.this);

    }

    public void onClickConnexion(View view) {

        loginField = findViewById(R.id.login);
        passwordField = findViewById(R.id.motDePasse);

        String loginSaisi = loginField.getText().toString();
        String passwordSaisi = passwordField.getText().toString();

        if(login.equals(loginSaisi) && password.equals(passwordSaisi)){
            Intent intent = new Intent(ConnexionActivity.this, ActionsChoiceActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Login ou Mot de passe éronnés", Toast.LENGTH_SHORT).show();
        }

    }
}
