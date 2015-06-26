
package examsoftgui;


/**
 *
 * @author zubaeyr
 */
import java.lang.Math.*;

import java.util.*;
import javax.swing.JTextField;
public class Calculator {

    /**
     * @param args the command line arguments
     */
    
 
    Calculator(Stack stk,JTextField jTextField1,int Degree){
        
        String[] expression=new String[stk.size()];
        for(int i=0;i<stk.size();i++){
         
            expression[i]=String.valueOf(stk.get(i));
            
         
       }

         String[] postfix=new String[1000];
        int endofposfix=-1;
        int endofinfix=expression.length;
        Stack st=new Stack();
        boolean bool=false;
        Double val=null;
        
        
        for(int i=0;i<endofinfix;i++){
            
            String element=new String(expression[i]);
            try{
                val=new Double(Double.parseDouble(element));
                bool=true;
            }catch(NumberFormatException e){
                bool=false;
            }
          
            if(bool){
                endofposfix=endofposfix+1;
                postfix[endofposfix]=new String(element);
            }
            else if(element.equals("e")){
                  postfix[++endofposfix]=new String(String.valueOf(Math.E)); 
            }
            else if(element.equals("pi")){
                  postfix[++endofposfix]=new String(String.valueOf(Math.PI));                    
            }
            else if(element.equals("(")||(element.equals("sqrt("))||element.equals("sin(")||element.equals("cos(")||element.equals("tan(")||element.equals("ln(")||element.equals("log(")||element.equals("e^(")||element.equals("atan(")||element.equals("acos(")||element.equals("asin(")||element.equals("!")||element.equals("sinh(")||element.equals("cosh(")||element.equals("tanh(")){
                st.push(element);
            }
            else if(element.equals("+")||element.equals("*")||element.equals("÷")||element.equals("-")||element.equals("^")||element.equals("%")){
                int u=precedence(element);
                int v=0;
                try{
                v=precedence((String)st.peek());
               
                while((v>u)&&(!(st.peek().equals("(")))&&(!st.peek().equals("sin("))&&(!st.peek().equals("cos("))&&(!st.peek().equals("tan("))&&(!st.peek().equals("asin("))&&(!st.peek().equals("acos("))&&(!st.peek().equals("atan("))&&(!st.peek().equals("sqrt("))&&(!st.peek().equals("!"))&&(!st.peek().equals("e^("))&&(!st.peek().equals("tanh("))&&(!st.peek().equals("sinh("))&&(!st.peek().equals("cosh("))){
                       postfix[++endofposfix]=new String((String)st.pop());
                       
                            v=precedence((String)st.peek());
                         
                }}catch(EmptyStackException e){}
                st.push(element);
            }
            else if(element.equals(")")){
                String poppedval=new String((String) st.pop());
                while((!poppedval.equals("(")) && (!poppedval.equals("sin(")) && (!poppedval.equals("cos(")) && (!poppedval.equals("tan(")) && (!poppedval.equals("ln("))&& (!poppedval.equals("log("))&& (!poppedval.equals("e^("))&&(!poppedval.equals("asin("))&&(!poppedval.equals("acos("))&&(!poppedval.equals("atan("))&&(!poppedval.equals("sqrt(")) && (!poppedval.equals("sinh(")) && (!poppedval.equals("tanh(")) && (!poppedval.equals("cosh("))){
                    ++endofposfix;
                    postfix[endofposfix]=new String(poppedval);
                   
                                         
                    if(st.isEmpty()){
                      jTextField1.setText("Expression Error");
                    }
                    poppedval=new String((String) st.pop()); 
                }
                
                if((poppedval.equals("sin(")) || (poppedval.equals("cos(")) || (poppedval.equals("tan(")) || (poppedval.equals("ln("))|| (poppedval.equals("log("))|| (poppedval.equals("e^("))||(poppedval.equals("asin("))||(poppedval.equals("acos("))||(poppedval.equals("atan("))||(poppedval.equals("sqrt("))|| (poppedval.equals("tanh("))|| (poppedval.equals("sinh("))|| (poppedval.equals("cosh("))){
                    postfix[++endofposfix]=new String((String) poppedval.subSequence(0, poppedval.lastIndexOf('(')));  
                }
                
            }
            else{
                
                 jTextField1.setText("Expression Error");
            }
            
        }
        while(!st.isEmpty()){
                postfix[++endofposfix]=new String((String) st.pop());
            }
        
       String[] postfixexp=new String[endofposfix+1];
       
      
       for(int i=0;i<=endofposfix;i++){
   
           
            postfixexp[i]=new String(postfix[i]);
       }
       try{
                  new Evaluator(postfixexp,stk,jTextField1,Degree);
       }catch(Exception e){
           jTextField1.setText("Expression Error");
       }

    }
  

    private int precedence(String operator) {
        int value;
        char[] operatorz=operator.toCharArray();
        if(operator.equals("%")){
            operatorz[0]='m';
        }else if(operator.equals("÷")){
            operatorz[0]='/';
        }
        switch(operatorz[0]){
		case '^':value=3;
			break;
		case '*':
                case 'm'://modulo
		case '/':value=2;
			break;
		case '+':
		case '-':value=1;
			break;	
		default :value=0;
			break;
	}
	return(value);

        //To change body of generated methods, choose Tools | Templates.
    }
    
}
