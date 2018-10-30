package net.quber.whatisrxjava;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import io.reactivex.Observable;

public class MultipleTableActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_table);

        EditText editText = findViewById(R.id.gugudan_activity_edit);
        TextView textView = findViewById(R.id.gugudan_activity_result_text);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Observable
                        .defer(() ->
                        {
                            textView.setText("");
                            return Observable.range(1, 9);
                        })
                        .map(row -> {
                            int dan = Integer.parseInt(editText.getText().toString());
                            return (dan + "*" + row + "=" + (dan * row));
                        }).map(row -> row + '\n')
                        .subscribe(textView::append, throwable -> textView.setText(throwable.getMessage()));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
