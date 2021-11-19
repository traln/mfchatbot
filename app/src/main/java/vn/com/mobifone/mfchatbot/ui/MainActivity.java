package vn.com.mobifone.mfchatbot.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import vn.com.mobifone.mfchatbot.R;
import vn.com.mobifone.mfchatbot.model.ChatBotModel;
import vn.com.mobifone.mfchatbot.model.MarsPhotos;
import vn.com.mobifone.mfchatbot.param.ChatBotParam;

public class MainActivity extends AppCompatActivity implements MainContract.MainView{

    private ProgressBar progressBar;
    private AppCompatEditText edt_username;
    private AppCompatEditText edt_content;
    private AppCompatButton btn_Add;
    private TextView tv_ID;
    private TextView tv_Site;

    MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
//        progressBarInit();

        presenter = new MainPresenterImpl(this, new IntractorImpl());
    }
    private void initView(){
        edt_username = this.findViewById(R.id.edt_username);
        edt_content = this.findViewById(R.id.edt_content);
        btn_Add = this.findViewById(R.id.btn_Add);
        tv_ID = this.findViewById(R.id.tv_ID);
        tv_Site = this.findViewById(R.id.tv_Site);
        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatBotParam param=new ChatBotParam(edt_username.getText().toString(),edt_content.getText().toString());

                presenter.addChatBot(param);
                progressBarInit();
            }
        });
    }



    private void progressBarInit(){
        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleLarge);
        progressBar.setIndeterminate(true);

        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setGravity(Gravity.CENTER);
        relativeLayout.addView(progressBar);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
        );

        progressBar.setVisibility(View.VISIBLE);

        this.addContentView(relativeLayout, params);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setLoadingProgressBar(String message, boolean active) {

    }

    @Override
    public void setDataToRecyclerView(ArrayList<MarsPhotos> noticeArrayList) {

    }

    @Override
    public void setDataChatBot(ChatBotModel rs) {
        tv_ID.setText("ID ChatBot: "+rs.getId().toString());
        tv_Site.setText("Site: "+rs.getContents().toString());
    }


    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(MainActivity.this,
                "Connection Error" + throwable.getMessage(),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
