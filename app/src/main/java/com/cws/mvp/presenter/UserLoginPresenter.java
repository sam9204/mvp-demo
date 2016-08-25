package com.cws.mvp.presenter;

import android.text.TextUtils;

import com.cws.mvp.bean.User;
import com.cws.mvp.net.UserLoginNet;

/**
 * 业务相关
 * Created by cws on 2016/8/25.
 */
public class UserLoginPresenter {

    // Activity  和 Fragment  通用性不好
    // 提高通用性： 放置参数为通用(抽象类或接口，实际开发者接口更通用）
//    private MainActivity mainActivity;
    private IUserLoginView view;

    public UserLoginPresenter(IUserLoginView view) {
        this.view = view;
    }

    /**
     * 判断用户输入
     * @return
     * @param user
     */
    public boolean checkUserInfo(User user) {
        if (TextUtils.isEmpty(user.username) || TextUtils.isEmpty(user.password)) {
            return false;
        }
        return true;
    }

    /**
     * 登陆
     * @param user
     */
    public void login(final User user) {
        new Thread() {
            @Override
            public void run() {
                UserLoginNet net = new UserLoginNet();
                if (net.sendUserInfo(user)) {
                    // 登陆成功
                    view.success();
                } else {
                    // 登陆失败
                    view.failed();
                }
            }
        }.start();
    }

}
