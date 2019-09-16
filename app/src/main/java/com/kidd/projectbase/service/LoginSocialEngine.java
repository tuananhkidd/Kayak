package com.kidd.projectbase.service;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.Fragment;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.gson.Gson;
import com.kidd.projectbase.App;
import com.kidd.projectbase.custom.InstagramAuthenticationDialog;
import com.kidd.projectbase.entity.login_response.ZaloLoginResponse;
import com.kidd.projectbase.presenter.HomePresenter;
import com.kidd.projectbase.utils.ToastUtil;
import com.zing.zalo.zalosdk.oauth.LoginVia;
import com.zing.zalo.zalosdk.oauth.OAuthCompleteListener;
import com.zing.zalo.zalosdk.oauth.OauthResponse;
import com.zing.zalo.zalosdk.oauth.ZaloOpenAPICallback;
import com.zing.zalo.zalosdk.oauth.ZaloSDK;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;

public class LoginSocialEngine<T> extends OAuthCompleteListener implements ZaloOpenAPICallback {

    private T mPresenter;

    private static LoginSocialEngine instance = null;
    public static final int GOOGLE_REQUEST_CODE_LOGIN = 1996;
    private GoogleSignInClient googleSignInClient;
    private ZaloLoginListener zaloLoginListener;

    private LoginSocialEngine() {
    }

    public static LoginSocialEngine getInstance() {
        if (instance == null) {
            instance = new LoginSocialEngine();
        }
        return instance;
    }

    public LoginSocialEngine setmPresenter(T mPresenter) {
        this.mPresenter = mPresenter;
        return this;
    }

    public void loginInstagram(Context context) {
        InstagramAuthenticationDialog instagramAuthenticationDialog = new InstagramAuthenticationDialog(context, auth_token -> {
            if (mPresenter instanceof HomePresenter) {
                ((HomePresenter) mPresenter).loginInstagram(auth_token);
            }
        });
        instagramAuthenticationDialog.setCancelable(true);
        instagramAuthenticationDialog.show();
    }


    public void loginGoogle(Activity activity) {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(activity, gso);
        Intent signInIntent = googleSignInClient.getSignInIntent();
        activity.startActivityForResult(signInIntent, GOOGLE_REQUEST_CODE_LOGIN);
    }

    public void loginZalo(Activity activity, ZaloLoginListener zaloLoginListener) {
        ZaloSDK.Instance.authenticate(activity, LoginVia.APP_OR_WEB, this);
        this.zaloLoginListener = zaloLoginListener;
    }

    public void loginFacebook(CallbackManager callbackManager, Fragment fragment) {
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                if (loginResult != null && loginResult.getAccessToken() != null) {
                    Log.d("facebooklogin", "onSuccess: "+loginResult.getAccessToken());
                    getUserFacebookProfile(loginResult.getAccessToken());
                }
            }

            @Override
            public void onCancel() {
                Log.d("facebooklogin", "onCancel: ");

            }

            @Override
            public void onError(FacebookException error) {
                Log.d("facebooklogin", "onError: ");

            }
        });
        LoginManager.getInstance().logInWithReadPermissions(fragment, Collections.singletonList("public_profile"));

    }

    private void getUserFacebookProfile(AccessToken currentAccessToken) {
        GraphRequest request = GraphRequest.newMeRequest(
                currentAccessToken,
                (object, response) -> {
                    try {
                        Log.d("facebooklogin", "onCompleted: "+object.toString());
                        //You can fetch user info like this…
                        //object.getJSONObject(“picture”).
                        object.getString("name");
                        //object.getString(“email”));
                        //object.getString(“id”));
                    } catch (JSONException e) {
                        Log.d("facebooklogin", "onCompleted: "+e.getMessage());
                        e.printStackTrace();
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,picture");
        request.setParameters(parameters);
        request.executeAsync();
    }

    @Override
    public void onResult(JSONObject jSONObject) {
        Log.d("zalologin", "onResult: " + jSONObject.toString());
        ZaloLoginResponse zaloLoginResponse = new Gson().fromJson(jSONObject.toString(), ZaloLoginResponse.class);
        if (zaloLoginListener != null) {
            zaloLoginListener.onZaloLogin(zaloLoginResponse);
        }
    }

    @Override
    public void onGetOAuthComplete(OauthResponse response) {
        super.onGetOAuthComplete(response);
        String[] fields = new String[]{"id", "name", "picture"};
        ZaloSDK.Instance.getProfile(App.getContext(), this, fields);
    }

    @Override
    public void onAuthenError(int errorCode, String message) {
        super.onAuthenError(errorCode, message);
        ToastUtil.show(message);
    }

    public interface ZaloLoginListener {
        void onZaloLogin(ZaloLoginResponse zaloLoginResponse);
    }

    public void logout(Context context) {
        if (googleSignInClient != null) {
            googleSignInClient.signOut();
        }
        LoginManager.getInstance().logOut();
        ZaloSDK.Instance.unauthenticate();
    }
}
