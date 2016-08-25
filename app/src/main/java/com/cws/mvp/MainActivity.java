package com.cws.mvp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.cws.mvp.bean.User;
import com.cws.mvp.presenter.IUserLoginView;
import com.cws.mvp.presenter.UserLoginPresenter;

/**
 * MVP
 * Created by cws on 2016/8/25.
 */
/*


存在问题：


解决方案：

 */
public class MainActivity extends AppCompatActivity implements IUserLoginView{

    EditText et_username;
    EditText et_password;
    ProgressDialog pd;
    UserLoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        pd = new ProgressDialog(this);
        presenter = new UserLoginPresenter(this);
    }

    /**
     * 按钮点击
     * @param v
     */
    public void login(View v){
        final String username = et_username.getText().toString();
        final String password = et_password.getText().toString();

        final User user = new User();
        user.username = username;
        user.password = password;

        if (presenter.checkUserInfo(user)) {
            // 登陆
            pd.show();
            presenter.login(user);
        } else {
            showToast("请输入完整信息");
        }

    }

    public void failed() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // 登陆失败
                pd.dismiss();
                showToast("用户名或密码不正确");
            }
        });
    }

    public void success() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // 登陆成功
                pd.dismiss();
                showToast("登陆成功");
            }
        });
    }

    /**
     * 判断用户输入
     * @return
     * @param user
     */
    private boolean checkUserInfo(User user) {
        if (TextUtils.isEmpty(user.username) || TextUtils.isEmpty(user.password)) {
            return false;
        }
        return true;
    }

    /**
     * 显示toast
     * @param msg
     */
    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
