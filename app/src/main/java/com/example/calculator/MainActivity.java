package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import com.example.calculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private boolean isFirstInput = true;
    private ActivityMainBinding binding;
    private Logic.Operations operation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btn0.setOnClickListener(this);
        binding.btn1.setOnClickListener(this);
        binding.btn2.setOnClickListener(this);
        binding.btn3.setOnClickListener(this);
        binding.btn4.setOnClickListener(this);
        binding.btn5.setOnClickListener(this);
        binding.btn6.setOnClickListener(this);
        binding.btn7.setOnClickListener(this);
        binding.btn8.setOnClickListener(this);
        binding.btn9.setOnClickListener(this);
        binding.btnDot.setOnClickListener(this);

        binding.btnPlus.setOnClickListener(this);
        binding.btnMinus.setOnClickListener(this);
        binding.btnMult.setOnClickListener(this);
        binding.btnDiv.setOnClickListener(this);
        binding.btnPercent.setOnClickListener(this);

        binding.btnBack.setText("\u140A");
        binding.btnBack.setOnClickListener(this);
        binding.btnClear.setOnClickListener(this);

        binding.btnEq.setOnClickListener(this);
        binding.btnChange.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_0:
            { input(0);}
            break;
            case R.id.btn_1:
            {input(1);}
            break;
            case R.id.btn_2:
            {input(2);}
            break;
            case R.id.btn_3:
            {input(3);}
            break;
            case R.id.btn_4:
            {input(4);}
            break;
            case R.id.btn_5:
            {input(5);}
            break;
            case R.id.btn_6:
            {input(6);}
            break;
            case R.id.btn_7:
            {input(7);}
            break;
            case R.id.btn_8:
            {input(8);}
            break;
            case R.id.btn_9:
            {input(9);}
            break;
            case R.id.btn_dot:
            {inputDot();}
            break;
            //==============================
            case R.id.btn_plus:
            {setOperation(Logic.Operations.PLUS);}
            break;
            case R.id.btn_minus:
            {setOperation(Logic.Operations.MINUS);}
            break;
            case R.id.btn_mult:
            {setOperation(Logic.Operations.MULT);}
            break;
            case R.id.btn_div:
            {setOperation(Logic.Operations.DIV);}
            break;
            case R.id.btn_percent:
            {setOperation(Logic.Operations.PERSENT);}
            break;
            //==============================
            case R.id.btn_back:
            {
                if(!isFirstInput){
                    if(!binding.input2.getText().equals("")) {
                        String str = binding.input2.getText().toString();
                        binding.input2.setText(str.substring(0,str.length()-1));
                        binding.resultPreview.setText(String.format("= %s", Logic.calculate(
                                binding.input1.getText().toString(),
                                binding.input2.getText().toString(),
                                operation
                        )));
                    }
                    else {
                        isFirstInput=true;
                        binding.operation.setText("");
                    }

                }
                else{
                    String str = binding.input1.getText().toString();
                    if(str.length()>1)
                    binding.input1.setText(str.substring(0,str.length()-1));
                    else binding.input1.setText("0");
                }
            }
            break;
            case R.id.btn_clear:
            {
                binding.input1.setText("0");
                binding.input2.setText("");
                binding.operation.setText("");
                binding.resultPreview.setText("");
                isFirstInput=true;
            }
            break;
            case R.id.btn_eq:
            {
                binding.input1.setText(binding.resultPreview.getText().toString().split(" ")[1]);
                binding.resultPreview.setText("");
                binding.input2.setText("");
                binding.operation.setText("");
                isFirstInput=true;
            }
            break;
            case R.id.btn_change:
            {
                if(isFirstInput){
                    String str = binding.input1.getText().toString();
                    if(!str.equals("0")){
                        if(!str.contains("-"))binding.input1.setText(String.format("-%s", str));
                        else binding.input1.setText(str.substring(1));
                    }
                }
                else {
                    String str = binding.input2.getText().toString();
                    if(str.length()!=0){
                        if(!str.contains("-"))binding.input2.setText(String.format("-%s", str));
                        else binding.input2.setText(str.substring(1));
                        binding.resultPreview.setText(String.format("= %s", Logic.calculate(
                                binding.input1.getText().toString(),
                                binding.input2.getText().toString(),
                                operation
                        )));
                    }
                }

            }
                break;
            //==============================
            default:
                break;
        }
    }

    private void input(int i) {
        if(isFirstInput)
        {
            if(!binding.input1.getText().equals("0"))
                binding.input1.setText(binding.input1.getText().toString()+i);
            else binding.input1.setText(String.valueOf(i));
        }
        else {
            binding.input2.setText(binding.input2.getText().toString()+i);
            binding.resultPreview.setText(String.format("= %s", Logic.calculate(
                    binding.input1.getText().toString(),
                    binding.input2.getText().toString(),
                    operation
            )));
        }
    }
    private void inputDot() {
        if(isFirstInput) {
            if(!binding.input1.getText().toString().contains("."))
                binding.input1.setText(binding.input1.getText().toString()+".");
        }
        else{
            if(!binding.input2.getText().toString().contains(".") && !binding.input2.getText().equals(""))
                binding.input2.setText(binding.input2.getText().toString()+".");
        }
    }
    private void setOperation(Logic.Operations op){
        if(isFirstInput)
        {
            operation=op;
            isFirstInput=false;
            switch (operation){
                case PLUS:
                    binding.operation.setText("+");
                    break;
                case MINUS:
                    binding.operation.setText("-");
                    break;
                case MULT:
                    binding.operation.setText("*");
                    break;
                case DIV:
                    binding.operation.setText("/");
                    break;
                case PERSENT:
                    binding.operation.setText("%");
                    break;
            }
        }
    }
}