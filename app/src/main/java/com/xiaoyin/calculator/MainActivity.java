package com.xiaoyin.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button add, reduce, multiply, divide, left,rigth,back,
           zero, one, two, three, four, five, six, seven, eight, nine,point, equal, clear;
    private TextView show;
    private String temp = "", num1="", num2="", result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = findViewById(R.id.add);
        reduce = findViewById(R.id.reduce);
        multiply = findViewById(R.id.multiply);
        divide = findViewById(R.id.divide);
        left=findViewById(R.id.left);
        rigth=findViewById(R.id.right);

        zero = findViewById(R.id.zero);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        point = findViewById(R.id.point);

        back=findViewById(R.id.back);
        show = findViewById(R.id.show);
        equal = findViewById(R.id.equal);
        clear = findViewById(R.id.clear);

        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set("0");
            }
        });
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set("1");
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set("2");
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set("3");
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set("4");
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set("5");
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set("6");
            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set("7");
            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set("8");
            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set("9");
            }
        });
        point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set(".");
            }
        });

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp += "(";
                show.setText(temp);
            }
        });
        rigth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp += ")";
                show.setText(temp);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp += "+";
                show.setText(temp);
            }
        });
        reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp += "-";
                show.setText(temp);
            }
        });
        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp += "*";
                show.setText(temp);
            }
        });
        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp += "/";
                show.setText(temp);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String substring = temp.substring(0, temp.length() - 1);
                temp=substring;
                show.setText(temp);
            }
        });
        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int i = temp.indexOf("+");
//                int j=temp.indexOf("-");
//                int k=temp.indexOf("*");
//                int l=temp.indexOf("/");
//
//                if (i != -1) show.setText(String.valueOf(Operation.add(num1, num2)));
//                if (j != -1) show.setText(String.valueOf(Operation.sub(num1, num2)));
//                if (k != -1) show.setText(String.valueOf(Operation.mult(num1, num2)));
//                if (l != -1) show.setText(String.valueOf(Operation.divide(num1, num2)));
                String data = temp;
                String[] dataArray = Compute.getDataArray(data);
                String[] postfixArray =Compute.getPostFix(dataArray);
                String s = Compute.computeWithPostFix(postfixArray);
                if (s!=null&&s.equals("520")) s="hemin";
                show.setText(s);

            }
        });

        //清除按钮
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp = "";
                num1="";
                num2="";
                show.setText(temp);
            }
        });

    }
    void set(String str){
        if (this.num1.equals("")){
            this.num1=str;
        }else {
            this.num2 =str;
        }
        temp += str;
        show.setText(temp);
    }
}
