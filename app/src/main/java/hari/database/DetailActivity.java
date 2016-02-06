package hari.database;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText name, email, mobile;
    private Button edit_user_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        pullViews();
        initListeners();
        populateData();
    }

    @Override
    public void onClick(View v) {

    }

    private void pullViews() {
        name = (EditText) findViewById(R.id.detail_name_et);
        email = (EditText) findViewById(R.id.detail_email_et);
        mobile = (EditText) findViewById(R.id.detail_mobile_et);
        edit_user_btn = (Button) findViewById(R.id.edit_user_btn);
    }

    private void initListeners() {
        edit_user_btn.setOnClickListener(this);
    }

    void populateData() {
        name.setText(getIntent().getStringExtra(Constants.NAME));
        mobile.setText(getIntent().getStringExtra(Constants.MOBILE));
        email.setText(getIntent().getStringExtra(Constants.EMAIL));
    }


    /**
     * it validates input data
     */
    private void validateInputData() {

        if (name.getText().toString().length() == 0) {
            name.setError("Should not be empty.");
            return;
        }
        if (mobile.getText().toString().length() == 0) {
            mobile.setError("Should not be empty.");
            return;
        }
        if (email.getText().toString().length() == 0) {
            email.setError("Should not be empty.");
            return;
        }


    }


}
