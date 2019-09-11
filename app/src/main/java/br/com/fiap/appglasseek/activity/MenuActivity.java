package br.com.fiap.appglasseek.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import br.com.fiap.appglasseek.R;
import br.com.fiap.appglasseek.fragment.AjudaFragment;
import br.com.fiap.appglasseek.fragment.CarrinhoFragment;
import br.com.fiap.appglasseek.fragment.ComprasFragment;
import br.com.fiap.appglasseek.fragment.FavoritosFragment;
import br.com.fiap.appglasseek.fragment.InicioFragment;
import br.com.fiap.appglasseek.fragment.PerfilFragment;
import br.com.fiap.appglasseek.service.LoginService;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new InicioFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_inicio);
        }

        navigationView.setCheckedItem(R.id.nav_inicio);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        new AlertDialog.Builder(this)
                .setMessage("Deseja realmente sair da aplicação?")
                .setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MenuActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_inicio) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new InicioFragment()).commit();

        } else if (id == R.id.nav_perfil) {
            if (LoginService.isLogged(getApplicationContext())) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PerfilFragment()).commit();
            } else {
                Intent intent = new Intent(MenuActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        } else if (id == R.id.nav_favoritos) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FavoritosFragment()).commit();

        } else if (id == R.id.nav_compras) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ComprasFragment()).commit();

        } else if (id == R.id.nav_carrinho) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CarrinhoFragment()).commit();

        } else if (id == R.id.nav_ajuda) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AjudaFragment()).commit();

        } else if (id == R.id.nav_sair) {
            Intent intent = new Intent(MenuActivity.this, LoginActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}
