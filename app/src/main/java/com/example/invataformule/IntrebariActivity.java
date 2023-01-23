package com.example.invataformule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class IntrebariActivity extends AppCompatActivity {

    ImageView imagine;
    TextView intrebare, scortxt;
    Button raspuns1, raspuns2, raspuns3, raspuns4, nextBtn;
    Button[] butoane = new Button[4];
    int[] selected = new int[4];
    HashMap<String, String> afisare = new HashMap<String, String>();
    HashMap<String, String> copie = new HashMap<String,String>();

    int scor, total, correctid;
    private int[] imagesArray = {R.drawable.learnin1, R.drawable.learning, R.drawable.math1, R.drawable.math2, R.drawable.math3, R.drawable.math4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;

        decorView.setSystemUiVisibility(uiOptions);
        setContentView(R.layout.activity_intrebari);

        intrebare = findViewById(R.id.intrebare);
        scortxt = findViewById(R.id.scortext);
        imagine = findViewById(R.id.imagineTitlu);

        raspuns1 = findViewById(R.id.raspuns1);
        raspuns1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Clicked(0, raspuns1);
            }
        });
        raspuns2 = findViewById(R.id.raspuns2);
        raspuns2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Clicked(1,raspuns2);
            }
        });
        raspuns3 = findViewById(R.id.raspuns3);
        raspuns3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Clicked(2,raspuns3);
            }
        });
        raspuns4 = findViewById(R.id.raspuns4);
        raspuns4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Clicked(3, raspuns4);
            }
        });
        nextBtn = findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NextQuestion();
            }
        });

        butoane[0] = raspuns1;
        butoane[1] = raspuns2;
        butoane[2] = raspuns3;
        butoane[3] = raspuns4;


        scor = 0;
        ClearAll();
        Intent intent = getIntent();
        String mode = intent.getStringExtra("set");
        if(mode.equals("integrale"))
            setIntegrals();
        else if(mode.equals("derivate"))
            setDerivate();
        else if(mode.equals("trigo"))
            setTrigo();

        total = afisare.size();
        copie.putAll(afisare);
        setQuestion();

    }
    //                                  "∫x²√ⁿ ⁿ⁺¹ eͯ aͯ eͣͯ"
    private void setIntegrals(){
        afisare.put("∫ x ⁿ dx", "x ⁿ⁺¹ / (n+1) + C");
        afisare.put("∫ 1/x dx", "ln |x| + C");
        afisare.put("∫ e ͯ dx", "e ͯ + C");
        afisare.put("∫ cosx dx", "sinx + C");
        afisare.put("∫ sinx dx", "-cosx + C");
        afisare.put("∫ 1 / (cos²x) dx", "tgx + C");
        afisare.put("∫ 1 / (sin²x) dx", "-ctgx + C");
        afisare.put("∫ 1 / (x² + a²) dx", "1/a arctg x/a + C");
        afisare.put("∫ 1 / (x² - a²) dx", "1/2a ln |(x-a)/(x+a)| + C");
        afisare.put("∫ 1 / √(x² - a²) dx", "ln |x + √(x² - a²)| + C");
        afisare.put("∫ 1 / √(x² + a²) dx", "ln |x + √(x² + a²)| + C");
        afisare.put("∫ 1 / √(a² - x²) dx", "arcsin(x/a) + C");
        afisare.put("∫ e ͣ ͯ dx", "e ͣ ͯ / a + C");
        afisare.put("∫ sinax dx", "- cosax / a + C");
        afisare.put("∫ cosax dx", "sinax / a + C");
        afisare.put("∫ a ͯ dx", "a ͯ / lna + C");
        afisare.put("∫ x / √(x²-a²) dx", "√(x²-a²) + C");
        afisare.put("∫ x / √(x²+a²) dx", "√(x²+a²) + C");
        afisare.put("∫ x / √(a²-x²) dx", "-√(a²-x²) + C");
        afisare.put("∫ tgx dx", "-ln |cosx| + C");
        afisare.put("∫ ctgx dx", "ln |sinx| + C");
    }

    private void setDerivate(){
        afisare.put("c'", "0");
        afisare.put("x'", "1");
        afisare.put("x ⁿ '", "n * x ⁿ̄¹");
        afisare.put("1/x'", "- 1 / x²");
        afisare.put("√x'", " 1 / 2√x");
        afisare.put("(e ͯ)'", "e ͯ");
        afisare.put("a ͯ'", "a ͯ lna");
        afisare.put("lnx'", "1/x");
        afisare.put("log ₐ x'", "1/xlna");
        afisare.put("sinx'", "cosx");
        afisare.put("cosx'", "-sinx");
        afisare.put("tgx'", "1 / cos²x");
        afisare.put("ctgx'", "-1 / sin²x");
        afisare.put("arcsinx'", "1 / √(1-x²)");
        afisare.put("arccosx'", "-1 / √(1-x²)");
        afisare.put("arctgx'", "1 / 1 + x²");
        afisare.put("arcctgx'", "-1 / 1 + x²");
    }

    private void setTrigo(){
        afisare.put("sin(a+b)", "sinacosb + sinbcosa");
        afisare.put("sin(a-b)", "sinacosb - sinbcosa");
        afisare.put("cos(a+b)", "cosacosb - sinasinb");
        afisare.put("cos(a-b)", "cosacosb + sinasinb");
        afisare.put("sin2x", "2sinxcosx");
        afisare.put("cos2x", "-");
        afisare.put("sin(x/2)", "± √ (1-cosx)/2 ");
        afisare.put("cos(x/2)", "± √ (1+cosx)/2 ");
        afisare.put("sin²x", "(1-cosx) / 2");
        afisare.put("cos²x", "(1+cosx) / 2");
    }

    private void ClearAll(){
        String text = scor + " / " + total;
        scortxt.setText(text);
        intrebare.setText("");
        raspuns1.setText("");
        raspuns2.setText("");
        raspuns3.setText("");
        raspuns4.setText("");

        Arrays.fill(selected, 0);

        for (Button button : butoane) button.setBackgroundColor(Color.parseColor("#d8d8d8"));
        nextBtn.setBackgroundColor(Color.parseColor("#aaaaaa"));
        nextBtn.setEnabled(false);
    }


    private void setQuestion(){
        String text = scor + " / " + total;
        scortxt.setText(text);
        correctid = (int) (Math.random() * 4);
        String key;
        if(afisare.size() > 0){
            key = afisare.keySet().toArray()[0].toString();
            intrebare.setText(key);
            imagine.setImageResource(imagesArray[(int)(Math.random() * imagesArray.length)]);
            if(!key.equals("cos2x")) {


                for (int i = 0; i < copie.size(); i++)
                    if (copie.keySet().toArray()[i].toString().equals(intrebare.getText().toString())) {
                        key = copie.values().toArray()[i].toString();
                        butoane[correctid].setText(key);
                    }
                for (int i = 0; i < butoane.length; i++) {

                    if (i != correctid) {
                        key = (String) copie.values().toArray()[(int) (Math.random() * copie.size())];
                        while (key.equals(raspuns1.getText().toString()) || key.equals(raspuns2.getText().toString()) || key.equals(raspuns3.getText().toString()) || key.equals(raspuns4.getText().toString()))
                            key = (String) copie.values().toArray()[(int) (Math.random() * afisare.size())];
                        butoane[i].setText(key);
                    }
                }
            }
            else{
                butoane[0].setText("2cos²x - 1");
                butoane[2].setText("cos²x - sin²x");
                butoane[3].setText("1 - 2sin²x");
                butoane[1].setText("1 + sin²x");
            }


        }
        else{

            int imagineToSet;
            if(scor < total/3) {
                imagineToSet = R.drawable.failure;
                text = "Mai ai de lucrat \n" + scortxt.getText().toString();
            }
            else if( scor < 2 * total / 3){
                imagineToSet = R.drawable.ballons;
                text = "Tine-o tot asa! \n" + scortxt.getText().toString();
            }

            else {
                text = "Felicitari! \n" + scortxt.getText().toString();
                imagineToSet = R.drawable.celebration;
            }
            intrebare.setText(text);
            imagine.setImageResource(imagineToSet);

            for(Button button : butoane)
                button.setVisibility(View.GONE);
            nextBtn.setEnabled(true);
            nextBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(IntrebariActivity.this, MainActivity.class));
                    finish();
                }
            });
        }
    }

    private void Clicked(int id, Button button){
        if(selected[id] == 0) {
            selected[id] = 1;
            button.setBackgroundColor(Color.parseColor("#bababa"));
        }
        else{
            selected[id] = 0;
            button.setBackgroundColor(Color.parseColor("#d8d8d8"));
        }
        int k = 0;
        for (int i = 0; i <selected.length; i++){
            System.out.println(i + " " + selected[i]);
            if(selected[i] == 1) k++;
        }

        nextBtn.setEnabled(k > 0);
    }

    private void CheckAnswer(){
        boolean correct = false;
        if(!intrebare.getText().toString().equals("cos2x")) {
            int k = 0;
            for(int j= 0; j<selected.length; j++) {
                if(selected[j] == 1) {
                    k++;
                    Button buttonClicked = butoane[j];
                    if (buttonClicked.getText().toString().equals(afisare.get(intrebare.getText().toString())))
                        correct = true;
                }
            }
            if(k>1) correct = false;
            for (int i = 0; i < butoane.length; i++)
                if (i == correctid)
                    butoane[i].setBackgroundColor(Color.parseColor("#adf7b6"));
                else
                    butoane[i].setBackgroundColor(Color.parseColor("#ff5e5b"));

        }
        else{

            if(selected[0] == 1 && selected[2] == 1 && selected[3] == 1 )
                correct = true;
            butoane[0].setBackgroundColor(Color.parseColor("#adf7b6"));
            butoane[1].setBackgroundColor(Color.parseColor("#ff5e5b"));
            butoane[2].setBackgroundColor(Color.parseColor("#adf7b6"));
            butoane[3].setBackgroundColor(Color.parseColor("#adf7b6"));

        }

        if (correct)
            scor++;
        String text = scor + " / " + total;
        scortxt.setText(text);

    }

    private void NextQuestion(){

        CheckAnswer();

        afisare.remove(intrebare.getText().toString());
        ClearAll();
        setQuestion();

    }

}