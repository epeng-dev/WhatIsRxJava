package net.quber.whatisrxjava;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MultipleTableActivity extends Activity {
    private static final String TAG = "MultipleTableActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_table);

        EditText editText = findViewById(R.id.multiple_table_activity_edit);
        TextView textView = findViewById(R.id.multiple_table_activity_result_text);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Observable
                        .defer(() ->
                        {
                            Log.d(TAG, Thread.currentThread().getName() + " defer");
                            textView.setText("");
                            return Observable.range(1, 9);
                        })
                        .map(row -> {
                            Log.d(TAG, Thread.currentThread().getName() + " map1");
                            int dan = Integer.parseInt(editText.getText().toString());
                            return (dan + "*" + row + "=" + (dan * row));
                        })
                        .map(row -> {
                            Log.d(TAG, Thread.currentThread().getName() + " map2");
                            return row + '\n';
                        })
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnError(throwable -> {
                            Log.d(TAG, Thread.currentThread().getName() + " doOnError");
                            textView.setText("");
                        })
                        .subscribe(rowString -> {
                                    Log.d(TAG, Thread.currentThread().getName() + " onNext");
                                    textView.append(rowString);
                                }
                        );
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
