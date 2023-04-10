package com.michael.appbalum.mainModule.view;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import com.michael.appbalum.R;
import com.michael.appbalum.common.pojo.Vocabulary;
import com.michael.appbalum.databinding.ActivityMainBinding;
import com.michael.appbalum.mainModule.MainPresenter;
import com.michael.appbalum.mainModule.MainPresenterClass;
import com.michael.appbalum.mainModule.view.adapters.OnItemClickListener;
import com.michael.appbalum.mainModule.view.adapters.VocabularyAdapter;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements OnItemClickListener, MainView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.contentMain)
    ConstraintLayout contentMain;
    @BindView(R.id.fab)
    FloatingActionButton fab;



    private MainPresenter mPresenter;
    private VocabularyAdapter mAdapter;

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        configToolbar();
        configAdapter();
        configRecyclerView();

        mPresenter = new MainPresenterClass(this);
        mPresenter.onCreate();

        //NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        //appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

    }

    private void configToolbar() {
        //binding = ActivityMainBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());
        setSupportActionBar(toolbar);
    }


    private void configAdapter(){
        mAdapter = new VocabularyAdapter(new ArrayList<Vocabulary>(),this);
    }

    private void configRecyclerView(){
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(this,
                getResources().getInteger(R.integer.main_columns));
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //@Override
    //public boolean onSupportNavigateUp() {
    //NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
    //return NavigationUI.navigateUp(navController, appBarConfiguration)
    //        || super.onSupportNavigateUp();
    //}
    //FIN DEL BACKUP MAIN POR DEFAULT

    @OnClick(R.id.fab)
    public void onAddClicked(){

    }

    /*
     * MainView
     * */


    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void add(Vocabulary vocabulary) {
        mAdapter.add(vocabulary);
    }

    @Override
    public void update(Vocabulary vocabulary) {
        mAdapter.update(vocabulary);
    }

    @Override
    public void remove(Vocabulary vocabulary) {
        mAdapter.remove(vocabulary);
    }

    @Override
    public void removeFail() {
        Snackbar.make(contentMain, R.string.main_error_remove, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onShowError(int resMsg) {
        Snackbar.make(contentMain, resMsg, Snackbar.LENGTH_LONG).show();
    }

    /*
     * OnItemClickListener
     * */

    @Override
    public void onItemClick(Vocabulary vocabulary) {

    }

    @Override
    public void onLongItemClick(Vocabulary vocabulary) {

    }

}