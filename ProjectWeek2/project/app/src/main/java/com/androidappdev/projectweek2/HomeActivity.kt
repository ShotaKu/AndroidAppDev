package com.androidappdev.projectweek2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        input1_et.setText("")
        input2_et.setText("")
        //calculateAction(R.layout.activity_home);
    }
    fun calculateAction(v: View){
        var a = 0;
        var b = 0;
/*        when(v){
            add ->{
                //You can also write like this.
            }
        }*/
        try{
            a = input1_et.text.toString().toInt()
            b = input2_et.text.toString().toInt()
            var btn = (v as Button)
            if(btn != null){
                var operator = btn.text.toString()
                var result = calculate(a,b,operator)
                message.text = "$result"   //special operation to change value to String.
            }
        }catch (t: Throwable){

        }
    }



    fun calculate(a: Int, b: Int, op:String):String{
        var result = 0;
        if(op == "+"){
            result = add(a,b)
        }
        else if(op == "-"){
            result = sub(a,b)
        }
        else if(op == "X"){
            result = multi(a,b)
        }
        else if(op == "AC"){
            clear();
            return "";
        }
        else{
            if(b == 0){
                return "E"
            }
            result = div(a,b)
        }

        return  result.toString();
    }

    fun sub(subtractor: Int, subtrahend: Int): Int{
        return subtractor - subtrahend
    }

    fun add(adder: Int, addend: Int): Int{
        return adder + addend
    }

    fun multi( multiplicand: Int, multiplier: Int): Int{
        return  multiplicand * multiplier
    }

    fun div(dividend: Int, devisor: Int): Int{
        return dividend / devisor;
    }

    fun clear(){
        input1_et.setText("")
        input2_et.setText("")
        message.text = ""
    }
}
