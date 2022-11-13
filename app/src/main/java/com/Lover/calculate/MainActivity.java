package com.Lover.calculate;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.content.ClipData;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button _calculateButton;
    View.OnClickListener _calculateButtonOnclickListener = view -> {
        MainActivity.this._serialTextNumber = MainActivity.this._serialNumberEditText.getText().toString();
        if (MainActivity.this._serialTextNumber.length() != 8) {
            MainActivity.this._serialNumberEditText.setError("هشت رقم لطفا");
        } else {
            MainActivity.this.CalculateCodes();
        }
    };

    TextView _cancelRegister;
    ClipboardManager _clipboardManager;
    private int _codeOne;
    View.OnClickListener _copyToClipboard = view -> {
        if (MainActivity.this._isItCopyTime) {
            MainActivity.this._clipboardManager.setPrimaryClip(ClipData.newPlainText("Generated Code", ((TextView) view).getText()));
            Toast.makeText(MainActivity.this, "کد کپی شد!", Toast.LENGTH_SHORT).show();

        }
    };
    TextView _eightRegister;
    TextView _fifthRegister;
    TextView _firstRegister;
    TextView _fourthRegister;
    Boolean _isItCopyTime = false;
    TextView _ninthRegister;
    TextView _oneYearRegister;
    TextView _secondRegister;
    EditText _serialNumberEditText;
    String _serialTextNumber;
    TextView _sevenRegister;
    TextView _sixRegister;
    View.OnFocusChangeListener _snEditTextOnFocusChangeListener = (view, z) -> {
        if (MainActivity.this._serialNumberEditText.getText().toString().length() < 8) {
            MainActivity.this._serialNumberEditText.setError("هشت رقم وارد کنید");
        } else {
            MainActivity.this._serialNumberEditText.setError(null);
        }
    };
    TextView _tenRegister;
    TextView _thirdRegister;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);


        this._clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

        this._calculateButton = findViewById(R.id.CalculateButton);
        this._serialNumberEditText = findViewById(R.id.SnTxtInput);
        this._firstRegister = findViewById(R.id.firstRegister);
        this._secondRegister = findViewById(R.id.scndRegister);
        this._thirdRegister = findViewById(R.id.thrdRegister);
        this._fourthRegister = findViewById(R.id.fourthRegister);
        this._fifthRegister = findViewById(R.id.fifthRegister);
        this._sixRegister = findViewById(R.id.sixthRegister);
        this._sevenRegister = findViewById(R.id.seventhRegister);
        this._eightRegister = findViewById(R.id.eightRegister);
        this._ninthRegister = findViewById(R.id.ninthRegister);
        this._tenRegister = findViewById(R.id.TenthRegister);
        this._oneYearRegister = findViewById(R.id.OneYearCode);
        this._cancelRegister = findViewById(R.id.CancelCode);
        this._calculateButton.setOnClickListener(this._calculateButtonOnclickListener);
        this._serialNumberEditText.setOnFocusChangeListener(this._snEditTextOnFocusChangeListener);

        this._firstRegister.setOnClickListener(this._copyToClipboard);
        this._firstRegister.setOnClickListener(this._copyToClipboard);
        this._secondRegister.setOnClickListener(this._copyToClipboard);
        this._thirdRegister.setOnClickListener(this._copyToClipboard);
        this._fourthRegister.setOnClickListener(this._copyToClipboard);
        this._fifthRegister.setOnClickListener(this._copyToClipboard);
        this._sixRegister.setOnClickListener(this._copyToClipboard);
        this._sevenRegister.setOnClickListener(this._copyToClipboard);
        this._eightRegister.setOnClickListener(this._copyToClipboard);
        this._ninthRegister.setOnClickListener(this._copyToClipboard);
        this._tenRegister.setOnClickListener(this._copyToClipboard);
        this._oneYearRegister.setOnClickListener(this._copyToClipboard);
        this._cancelRegister.setOnClickListener(this._copyToClipboard);
    }

    //ساخت منو
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    // آیتم های منو
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getTitle() == getResources().getString(R.string.AboutUS)) {
            Intent settingsActivityIntent = new Intent(this, AboutUsActivity.class);// using explicitly intent to open new activity
            this.startActivity(settingsActivityIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /* access modifiers changed from: private */
    @SuppressLint("SetTextI18n")
    public void CalculateCodes() {
        this._firstRegister.setText(Integer.toString(CalculateCodeOne()));
        this._secondRegister.setText(Integer.toString(CalculateCodeTwo()));
        this._thirdRegister.setText(Integer.toString(CalculateCodeThree()));
        this._fourthRegister.setText(Integer.toString(CalculateCodeFour()));
        this._fifthRegister.setText(Integer.toString(CalculateCodeFive()));
        this._sixRegister.setText(Integer.toString(CalculateCodeSix()));
        this._sevenRegister.setText(Integer.toString(CalculateCodeSeven()));
        this._eightRegister.setText(Integer.toString(CalculateCodeEight()));
        this._ninthRegister.setText(Integer.toString(CalculateCodeNine()));
        this._tenRegister.setText(Integer.toString(CalculateCodeTen()));
        this._cancelRegister.setText(Integer.toString(CalculateCodeCancel()));
        this._oneYearRegister.setText(Integer.toString(CalculateOneYearCode()));
        this._isItCopyTime = true;
    }

    private int CalculateOneYearCode() {
        int parseInt = Integer.parseInt(this._serialTextNumber.substring(0, 2)) * Integer.parseInt(this._serialTextNumber.substring(2, 4));
        return (parseInt * parseInt) + 4027 + 1364 + Integer.parseInt(this._serialTextNumber.substring(4, 6));
    }

    private int CalculateCodeCancel() {
        return CalculateCodeSix() + 1363;
    }

    private int CalculateCodeTen() {
        return 4084027 + Integer.parseInt(this._serialTextNumber.substring(6, 8)) + Integer.parseInt(this._serialTextNumber.substring(4, 6)) + Integer.parseInt(this._serialTextNumber.substring(2, 4)) + Integer.parseInt(this._serialTextNumber.substring(0, 2));
    }

    private int CalculateCodeNine() {
        return this._codeOne + CalculateCodeThree() + 54321;
    }

    private int CalculateCodeEight() {
        int parseInt = Integer.parseInt(this._serialTextNumber.substring(6, 8)) * Integer.parseInt(this._serialTextNumber.substring(4, 6));
        return (parseInt * parseInt) + 6336 + CalculateCodeSeven();
    }

    private int CalculateCodeSeven() {
        return ((Integer.parseInt(this._serialTextNumber.substring(0, 2)) +
                Integer.parseInt(this._serialTextNumber.substring(2, 4))) * 55 * Integer.parseInt(this._serialTextNumber.substring(6, 8)) *
                Integer.parseInt(this._serialTextNumber.substring(4, 6))) + CalculateCodeFour();
    }

    private int CalculateCodeSix() {
        return this._codeOne + Integer.parseInt(this._serialTextNumber.substring(0, 4));
    }

    private int CalculateCodeFive() {
        return this._codeOne - (Integer.parseInt(this._serialTextNumber.substring(0, 2)) + Integer.parseInt(this._serialTextNumber.substring(2, 4)));
    }

    private int CalculateCodeFour() {
        return this._codeOne + Integer.parseInt(this._serialTextNumber.substring(0, 2)) + Integer.parseInt(this._serialTextNumber.substring(2, 4));
    }

    private int CalculateCodeThree() {
        return this._codeOne - Integer.parseInt(this._serialTextNumber.substring(2, 4));
    }

    private int CalculateCodeTwo() {
        return this._codeOne + Integer.parseInt(this._serialTextNumber.substring(0, 2));
    }

    private int CalculateCodeOne() {
        this._codeOne = Integer.parseInt(this._serialTextNumber.substring(0, 2)) * Integer.parseInt(this._serialTextNumber.substring(2, 4));
        this._codeOne *= this._codeOne;
        this._codeOne = this._codeOne + Integer.parseInt(this._serialTextNumber.substring(4, 6)) + Integer.parseInt(this._serialTextNumber.substring(6, 8));
        return this._codeOne;
    }
}