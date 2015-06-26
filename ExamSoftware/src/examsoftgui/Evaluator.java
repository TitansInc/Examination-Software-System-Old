package examsoftgui;


import java.util.*;
import javax.swing.*;
/**
 *
 * @author zubaeyr
 */
public class Evaluator {

    Evaluator(String postfix[],Stack stk,JTextField jTextField1,int Degree) throws Exception{
       
        Stack st=new Stack();
        Double a,b;
        Double result = null;
        for(int i=0;i<postfix.length;i++){
            String element=new String(postfix[i]);
       
            if(element.equals("+")){
               b=(Double) st.pop();
               a=(Double) st.pop();
               String prevop="+";
               try{
                prevop=new String(postfix[i+1]);
               }catch(Exception e){}
               if(prevop.equals("-")){
                        result=-a+b;
                        result=result*-1;
                        st.push(result);
               }else{
                   result=a+b;
                   st.push(result);
               }
            }
            else if(element.equals("-")){
               b=(Double) st.pop();
               a=(Double) st.pop();
               String prevop="+";
               try{
                prevop=new String(postfix[i+1]);
               }catch(Exception e){}
               if(prevop.equals("-")){
                        result=-a-b;
                        result=result*-1;
                        st.push(result);
               }else{
                   result=a-b;
                   st.push(result);
               }
            }
            else if(element.equals("*")){
               b=(Double) st.pop();
               a=(Double) st.pop();
               result=a*b;
               st.push(result);
            }
            else if(element.equals("÷")){
               b=(Double) st.pop();
               a=(Double) st.pop();
               result=a/b;
               st.push(result);
            }
            else if(element.equals("^")){
               b=(Double) st.pop();
               a=(Double) st.pop();
               result=Math.pow(a, b);
               st.push(result);
            }
            else if(element.equals("%")){
               b=(Double) st.pop();
               a=(Double) st.pop();
               String prevop="+";
               try{
                prevop=new String(postfix[i+1]);
               }catch(Exception e){}
               if(prevop.equals("-")){
                        result=(-a)%b;
                        result=result*-1;
                        st.push(result);
               }else{
                   result=a%b;
                   st.push(result);
               }
            }
            else if(element.equals("!")){
                
                a=(Double)st.pop(); 
                Long result1=new Long(a.longValue());
                result1=factorial(result1); 
                st.push(new Double(Double.parseDouble(String.valueOf(result1))));
            }
            else if(element.equals("sin")){
                a=(Double) st.pop();
                if(Degree==1){
                    result=Math.sin(a*Math.PI/180);
                }
                else{
                    result=Math.sin(a);
                }
               st.push(result);
            }
            else if(element.equals("cos")){
                a=(Double) st.pop();
                
                if(Degree==1){
                    result=Math.cos(a*Math.PI/180);
                    
                }
                else{
                    result=Math.cos(a);
                }
                st.push(result);
            }
            else if(element.equals("tan")){
                a=(Double) st.pop();
                if(Degree==1){
                    result=Math.tan(a*Math.PI/180);
                }
                else{
                    result=Math.tan(a);
                }
                st.push(result);
            }
            else if(element.equals("sinh")){
                a=(Double) st.pop();
                if(Degree==1){
                    result=Math.sinh(a*Math.PI/180);
                }
                else{
                    result=Math.sinh(a);
                }
               st.push(result);
            }
            else if(element.equals("cosh")){
                a=(Double) st.pop();
                if(Degree==1){
                    result=Math.cosh(a*Math.PI/180);
                }
                else{
                    result=Math.cosh(a);
                }
                st.push(result);
            }
            else if(element.equals("tanh")){
                a=(Double) st.pop();
               if(Degree==1){
                    result=Math.tanh(a*Math.PI/180);
                }
                else{
                    result=Math.tanh(a);
                }
                st.push(result);
            }
            else if(element.equals("asin")){
                a=(Double) st.pop();
                result=Math.asin(a);
                if(Degree==1){
                      result=result*180/Math.PI;
                }
                st.push(result);
            }
            else if(element.equals("acos")){
                a=(Double) st.pop();
                result=Math.acos(a);
                if(Degree==1){
                    result=result*180/Math.PI;
                }
                st.push(result);
            }
            else if(element.equals("atan")){
                a=(Double) st.pop();
                result=Math.atan(a);
                if(Degree==1){
                     result=result*180/Math.PI;
                }
                st.push(result);
            }
            else if(element.equals("log")){
                a=(Double) st.pop();
                result=Math.log(a);
                st.push(result);
            }
            else if(element.equals("e^")){
                a=(Double) st.pop();
                result=Math.pow(Math.E,a);
                st.push(result);
            }
            else if(element.equals("ln")){
                a=(Double) st.pop();
                result=Math.log(a);
                st.push(result);
            }
            else if(element.equals("sqrt")){
                a=(Double) st.pop();
                result=Math.sqrt(a);
                st.push(result);
                
            }
            else{
                st.push(new Double(Double.parseDouble(element)));
            }
            
        }
        stk.clear();
        jTextField1.setText(st.peek().toString());
       
    }
    long factorial(Long factr){
        if(factr==0){
            return 1;
        }
        else{
            return factr*factorial(factr-1);
        }
        
    }
}
