package hari.database;

import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //views
    private RecyclerView users_list_rl;
    private EditText name_et, mobile_et, email_et;
    private Button save_btn;
    //views related
    private UsersAdapter usersAdapter;
    //general
    private List<UserModel> uses;
    //db related
    private SQLiteDatabase sqLiteDatabase;
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //to pull views
        pullViews();
        //init listeners
        initListeners();


        //initializations
        //create out db helper object
        dbHelper = new DbHelper(this);
        //get database object to perform db operations
        sqLiteDatabase = dbHelper.getWritableDatabase();

        uses = new ArrayList<>();

        //to build list
        buildUsersList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        new DatabaseTask().execute();
    }

    @Override
    public void onClick(View v) {
        //validate input data
        validateInputData();
        //insert into table if valid data
        dbHelper.addUser(sqLiteDatabase, name_et.getText().toString(), email_et.getText().toString(), mobile_et.getText().toString());
        new DatabaseTask().execute();
    }


    /**
     * it pulls all views
     */
    private void pullViews() {
        users_list_rl = (RecyclerView) findViewById(R.id.users_list_rl);
        name_et = (EditText) findViewById(R.id.name_et);
        mobile_et = (EditText) findViewById(R.id.mobile_et);
        email_et = (EditText) findViewById(R.id.email_et);
        save_btn = (Button) findViewById(R.id.save_btn);

    }

    private void initListeners() {
        save_btn.setOnClickListener(this);
    }

    /**
     * it builds recycler view
     */
    private void buildUsersList() {
        //set layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //set orientation to recycler view
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //set layout manager ro recycler view
        users_list_rl.setLayoutManager(linearLayoutManager);
        //create adapter
        usersAdapter = new UsersAdapter(this, uses);
        //set adapter to recycler view
        users_list_rl.setAdapter(usersAdapter);
    }

    /**
     * it validates input data
     */
    private void validateInputData() {
        if (mobile_et.getText().toString().length() == 0) {
            name_et.setError("Should not be empty.");
            return;
        }
        if (mobile_et.getText().toString().length() == 0) {
            mobile_et.setError("Should not be empty.");
            return;
        }
        if (mobile_et.getText().toString().length() == 0) {
            mobile_et.setError("Should not be empty.");
            return;
        }

    }

    class DatabaseTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            uses = dbHelper.getAllUsers(sqLiteDatabase);
            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            usersAdapter.notifyAdapter(uses);
        }
    }


}
