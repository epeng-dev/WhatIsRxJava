package net.quber.whatisrxjava;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import io.reactivex.Observable;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.main_activity_text);
        Button button = findViewById(R.id.main_activity_button);
        Observable.just(textView.getText().toString())
                .map(s -> s + " RX!")
                .subscribe(text -> textView.setText(text));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MultipleTableActivity.class);
                startActivity(intent);
            }
        });
    }
}
