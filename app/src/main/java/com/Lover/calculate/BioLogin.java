package com.Lover.calculate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
//import androidx.core.content.ContextCompat;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.MessageFormat;
import java.util.concurrent.Executor;

public class BioLogin extends AppCompatActivity {

    private TextView _errorTxt;
    private Integer _bioResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bio_login);
        Button _loginB = findViewById(R.id.loginButton);
        _loginB.setOnClickListener(this.launchMainActivityListener);
        _errorTxt = findViewById(R.id.errorText);
        biometricInit();
    }

    void biometricInit() {
        BiometricManager bio = BiometricManager.from(this);
        _bioResult = bio.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_WEAK | BiometricManager.Authenticators.DEVICE_CREDENTIAL);
    }

    private void authenticateUsingBiometric() {
        //Executor exe2 = ContextCompat.getMainExecutor(this); //api below 28
        Executor exe = getMainExecutor();

        BiometricPrompt bioPrompt = new BiometricPrompt(this, exe, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                setErrorMsg(MessageFormat.format("ورود با خطا رو به رو شد. کد خطا:{0}\n{1}", errorCode, errString));
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                launchMainActivity();
                _errorTxt.setVisibility(View.GONE);
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                setErrorMsg("ورود ناموفق بود.");
            }
        });
        BiometricPrompt.PromptInfo biPromptInfo;
        try {
            biPromptInfo = new BiometricPrompt.PromptInfo.Builder()
                    .setTitle("احراز هویت جهت ورود").
                    setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_WEAK | BiometricManager.Authenticators.DEVICE_CREDENTIAL).build();

            bioPrompt.authenticate(biPromptInfo);
        } catch (Exception e) {
            setErrorMsg("خطای ایجاد سیستم احراز هویت");
        }

    }

    private final View.OnClickListener launchMainActivityListener = view -> {
        switch (_bioResult) {
            case BiometricManager.BIOMETRIC_SUCCESS:
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
            case BiometricManager.BIOMETRIC_ERROR_UNSUPPORTED: {
                authenticateUsingBiometric();
                break;
            }
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED: {
                setErrorMsg("سیستم شما دارای رمز یا اثر انگشت نمی باشد.");
                break;
            }
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE: {
                setErrorMsg("سخت افزار احراز هویت در دسترس نمیباشد.");
                authenticateUsingBiometric();
                break;
            }
            case BiometricManager.BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED: {
                setErrorMsg("سیستم شما نیازمند آپدیت امنیتی می باشد.");
                break;
            }

        }
    };

    private void setErrorMsg(String str, int color) {
        _errorTxt.setVisibility(View.VISIBLE);
        _errorTxt.setText(str);
        _errorTxt.setTextColor(color);
    }

    private void setErrorMsg(String str) {
        _errorTxt.setVisibility(View.VISIBLE);
        _errorTxt.setText(str);
    }

    void launchMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}