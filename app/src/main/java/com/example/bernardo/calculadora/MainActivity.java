package com.example.bernardo.calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity {
    private TextView display;
    private Button button0, button1,button2,button3,button4,button5,button6,button7,button8,button9,buttonAC,buttonPercent,buttonDivide,
            buttonX,buttonPlus,buttonCollon,buttonEquals,buttonMinus,buttonPlusMinus;
    private boolean lastDigit,lastOperand,isDecimal,isPercent,lastLessOperation;
    private char aux;
    private String expression = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        this.display = findViewById(R.id.display);
        this.button0 = findViewById(R.id.button0);
        button0.setOnClickListener(listener);
        this.button1 = findViewById(R.id.button1);
        button1.setOnClickListener(listener);
        this.button2 = findViewById(R.id.button2);
        button2.setOnClickListener(listener);
        this.button3 = findViewById(R.id.button3);
        button3.setOnClickListener(listener);
        this.button4 = findViewById(R.id.button4);
        button4.setOnClickListener(listener);
        this.button5 = findViewById(R.id.button5);
        button5.setOnClickListener(listener);
        this.button6 = findViewById(R.id.button6);
        button6.setOnClickListener(listener);
        this.button7 = findViewById(R.id.button7);
        button7.setOnClickListener(listener);
        this.button8 = findViewById(R.id.button8);
        button8.setOnClickListener(listener);
        this.button9 = findViewById(R.id.button9);
        button9.setOnClickListener(listener);
        this.buttonAC = findViewById(R.id.AC);
        buttonAC.setOnClickListener(listener);
        this.buttonPercent = findViewById(R.id.percent);
        buttonPercent.setOnClickListener(listener);
        this.buttonDivide = findViewById(R.id.divide);
        buttonDivide.setOnClickListener(listener);
        this.buttonX = findViewById(R.id.X);
        buttonX.setOnClickListener(listener);
        this.buttonPlus = findViewById(R.id.buttonPlus);
        buttonPlus.setOnClickListener(listener);
        this.buttonCollon = findViewById(R.id.collon);
        buttonCollon.setOnClickListener(listener);
        this.buttonEquals = findViewById(R.id.equals);
        buttonEquals.setOnClickListener(listener);
        this.buttonMinus = findViewById(R.id.minus);
        buttonMinus.setOnClickListener(listener);
        this.buttonPlusMinus = findViewById(R.id.plusMius);
        buttonPlusMinus.setOnClickListener(listener);
        this.lastDigit = false;
        this.lastOperand = false;
        this.isDecimal = false;
        this.isPercent = false;
        this.lastLessOperation = false;
    }

    View.OnClickListener listener  = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.button0:
                    display.append("0");
                    expression+="0";
                    lastDigit = true;
                    lastOperand = false;
                    break;
                case R.id.button1:
                    display.append("1");
                    expression+="1";
                    lastDigit = true;
                    lastOperand = false;
                    break;
                case R.id.button2:
                    display.append("2");
                    expression+="2";
                    lastDigit = true;
                    lastOperand = false;
                    break;
                case R.id.button3:
                    display.append("3");
                    expression+="3";
                    lastDigit = true;
                    lastOperand = false;
                    break;
                case R.id.button4:
                    display.append("4");
                    expression+="4";
                    lastDigit = true;
                    lastOperand = false;
                    break;
                case R.id.button5:
                    display.append("5");
                    expression+="5";
                    lastDigit = true;
                    lastOperand = false;
                    break;
                case R.id.button6:
                    display.append("6");
                    expression+="6";
                    lastDigit = true;
                    lastOperand = false;
                    break;
                case R.id.button7:
                    display.append("7");
                    expression+="7";
                    lastDigit = true;
                    lastOperand = false;
                    break;
                case R.id.button8:
                    display.append("8");
                    expression+="8";
                    lastDigit = true;
                    lastOperand = false;
                    break;
                case R.id.button9:
                    display.append("9");
                    expression+="9";
                    lastDigit = true;
                    lastOperand = false;
                    break;
                case R.id.collon:
                    display.append(".");
                    expression+=".";
                    isDecimal = true;
                    lastDigit = false;
                    lastOperand = false;
                    break;
                case R.id.buttonPlus:
                    if(lastLessOperation && lastDigit) {
                        Expression ex = new ExpressionBuilder(expression).build();
                        double result = ex.evaluate();
                        display.setText(Double.toString(result));
                        expression = Double.toString(result);
                        lastLessOperation = false;
                    }
                    if(lastDigit) {
                        display.append("+");
                        expression+="+";
                        lastDigit = false;
                        lastOperand = true;
                        lastLessOperation = true;
                        aux = '+';
                    }
                    break;
                case R.id.X:
                    if(lastDigit) {
                        display.append("*");
                        expression+="*";
                        lastDigit = false;
                        lastOperand = true;
                        aux = '*';
                    }
                    break;
                case R.id.divide:
                    if(lastDigit) {
                        display.append("/");
                        expression+="/";
                        lastDigit = false;
                        lastOperand = true;
                        aux = '/';
                    }
                    break;
                case R.id.percent:
                    if(lastDigit) {
                        lastDigit = false;
                        isPercent = true;
                        display.append("%");
                        //Pega o indice da ultima opetação(+,-,* ou /)
                        int opIndex = expression.lastIndexOf(aux);
                        double number;
                        //Caso encontre, ou seja o numero não foi o primeiro a ser digitado.
                        if(opIndex != -1)
                        {
                            if(aux == '-')
                            {
                                //Pega o valor apartir da opeação e divide por 10 e soma com o valor para calcular a porcentagem.
                                number = Double.parseDouble(expression.substring(opIndex + 1));
                                double change = number / 100;
                                expression = display.getText().toString().substring(0, opIndex);
                                expression += "-" + change + "*" + expression.substring(0, opIndex);
                            }
                            if(aux == '+'){
                                //Pega o valor apartir da opeação e divide por 10 e soma com o valor para calcular a porcentagem.
                                number = Double.parseDouble(expression.substring(opIndex + 1));
                                double change = number / 100;
                                expression = display.getText().toString().substring(0, opIndex);
                                expression += "*" + change + aux + expression.substring(0, opIndex);
                            }
                            if(aux == '*'){
                                number = Double.parseDouble(expression.substring(opIndex + 1));
                                double change = number / 100;
                                //display.setText(display.getText().toString().substring(0, opIndex));
                                expression = change + "*" + expression.substring(0, opIndex);
                            }
                        }
                        else
                        {
                            number = Double.parseDouble(expression);
                            double change = number/100;
                            display.setText("*" + Double.toString(change));
                            expression = "*" + Double.toString(change);
                            //display.append("(" + Double.toString(change) + ")");
                        }
                    }
                    break;
                case R.id.AC:
                    display.setText("");
                    expression = "";
                    lastDigit = false;
                    lastOperand = false;
                    isDecimal = false;
                    lastLessOperation = false;
                    break;
                case R.id.minus:
                    if(lastLessOperation && lastDigit) {
                        Expression ex = new ExpressionBuilder(expression).build();
                        double result = ex.evaluate();
                        display.setText(Double.toString(result));
                        expression = Double.toString(result);
                        lastLessOperation = false;
                    }
                    if(lastDigit) {
                        display.append("-");
                        expression += "-";
                        lastDigit = false;
                        lastOperand = true;
                        lastLessOperation = true;
                        aux = '-';
                    }
                    break;
                case R.id.equals:
                    //Só calcular o resultado caso o ultimo valor seja um digito!
                    if(lastDigit || isPercent) {
                        Expression ex = new ExpressionBuilder(expression).build();
                        double result = ex.evaluate();
                        display.setText(Double.toString(result));
                        expression = Double.toString(result);
                        lastDigit = true;
                        lastOperand = false;
                        isDecimal = false;
                        isPercent = false;
                        lastLessOperation = false;
                    }
                    break;
                case R.id.plusMius:
                    if(lastDigit)
                    {
                        //Pega o indice da ultima opetação(+,-,* ou /)
                        int opIndex = expression.lastIndexOf(aux);
                        double number;
                        //Caso encontre, ou seja o numero não foi o primeiro a ser digitado.
                        if(opIndex != -1)
                        {
                            //Pega o valor apartir da opeação e transforma para negativo.
                            number = Double.parseDouble(String.valueOf(display.getText().toString().substring(opIndex+1)));
                            double change = number * - 1;
                            display.setText(display.getText().toString().substring(0, opIndex+1));
                            expression = display.getText().toString();
                            display.append("(" + Double.toString(change) + ")");
                            expression +=  Double.toString(change) ;
                        }
                        else
                        {
                            number = Double.parseDouble(String.valueOf(display.getText().toString()));
                            double change = number * - 1;
                            display.setText("(" + Double.toString(change) + ")");
                            expression =  Double.toString(change);
                        }
                    }
                    break;
            }
        }
    };
}
