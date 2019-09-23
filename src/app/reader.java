package app;

import java.util.ArrayList;
public class reader {
    private static String function;
    private ArrayList<String> function_copy = new ArrayList<>();
    public ArrayList<String> single_Operation = new ArrayList<>();
    public ArrayList<Integer> numbers = new ArrayList<>();
    public ArrayList<Integer> x = new ArrayList<>();
    public ArrayList<Integer> operation = new ArrayList<>();
    public ArrayList<Integer> exponent = new ArrayList<>();
    public ArrayList<Integer> open_parentheses = new ArrayList<>();
    public ArrayList<Integer> closed_parentheses = new ArrayList<>();

    public reader() {
        System.out.println("enter your function");
      
        function = "10x^2+10x+5";
        for(int i =0; i<function.length();i++){
            function_copy.add(function.substring(i, i+1));
        }
        for (int j= 0; j < function_copy.size(); j++) {
            boolean test = false;
            String temp ="";
            String todoub = "";
            boolean i = false;
            while(!i){
                try{
                    Integer.parseInt(function_copy.get(j));
                    todoub += function_copy.get(j);
                    j+= 1;
                    test = true;
                }catch(Exception e){
                    if (test) {
                        if(j<function_copy.size()){
                            if(function_copy.get(j).equals(".")){
                                todoub += function_copy.get(j);
                                j++;
                                i=false;

                            }else{
                                i = true;
                            }
                        }
                    }
                    i =true;
                }
            }
            if(j< function_copy.size() && function_copy.get(j).equals("^")){
                temp+= function_copy.get(j);
                j+=1;
                i = false;
                while(!i){
                    try{
                        Integer.parseInt(function_copy.get(j));
                        temp += function_copy.get(j);
                        j+=1;
                    }catch(Exception e){
                        i = true;
                    }
                }
            }
            if(todoub.equals("")){
            }else{
                single_Operation.add(todoub);
            }
            if(temp.equals("")){
            }else{
                single_Operation.add(temp);
            }
            if(j<function_copy.size()){
                if(function_copy.get(j).equals("x") || function_copy.get(j).equals("(") ||function_copy.get(j).equals(")")||function_copy.get(j).equals("+")||function_copy.get(j).equals("-")||function_copy.get(j).equals("*")||function_copy.get(j).equals("/")){
                    single_Operation.add(function_copy.get(j));
                }
            }
        }

    }
    public reader(String func) {
        
        function = func;
        for(int i =0; i<function.length();i++){
            function_copy.add(function.substring(i, i+1));
        }
        for (int j= 0; j < function_copy.size(); j++) {
            boolean test = false;
            String temp ="";
            String todoub = "";
            boolean i = false;
            while(!i){
                try{
                    Integer.parseInt(function_copy.get(j));
                    todoub += function_copy.get(j);
                    j+= 1;
                    test = true;
                }catch(Exception e){
                    if (test) {
                        if(j<function_copy.size()){
                            if(function_copy.get(j).equals(".")){
                                todoub += function_copy.get(j);
                                j++;
                                i=false;

                            }else{
                                i = true;
                            }
                        }
                    }
                    i =true;
                }
            }
            if(j< function_copy.size() && function_copy.get(j).equals("^")){
                temp+= function_copy.get(j);
                j+=1;
                i = false;
                while(!i){
                    try{
                        Integer.parseInt(function_copy.get(j));
                        temp += function_copy.get(j);
                        j+=1;
                    }catch(Exception e){
                        i = true;
                    }
                }
            }
            if(todoub.equals("")){
            }else{
                single_Operation.add(todoub);
            }
            if(temp.equals("")){
            }else{
                single_Operation.add(temp);
            }
            if(j<function_copy.size()){
                if(function_copy.get(j).equals("x") || function_copy.get(j).equals("(") ||function_copy.get(j).equals(")")||function_copy.get(j).equals("+")||function_copy.get(j).equals("-")||function_copy.get(j).equals("*")||function_copy.get(j).equals("/")){
                    single_Operation.add(function_copy.get(j));
                }
            }
        }
        categorizer();
    }
    
    
    public static String return_Function() {
        return function;
    }
    private void categorizer (){
        for (int i =0; i < single_Operation.size(); i++){
            int j = 0;
            try{
                Double.parseDouble(single_Operation.get(i));
                j=1;
                numbers.add(i);

            }catch(Exception e){

            }
            if(single_Operation.get(i).contains("^")){
                exponent.add(i);
            }else if(single_Operation.get(i).equals("x")){
                x.add(i);

            }else if(single_Operation.get(i).equals("(")){
                open_parentheses.add(i);

            }else if(single_Operation.get(i).equals(")")){
                closed_parentheses.add(i);
            }else if (j==0) {
                operation.add(i);
            }
        }
    }
    public double function_Evalulate(double input){
        
        ArrayList<Integer> md = new ArrayList<>();
        ArrayList<Integer> open_Parentheses_Copy = open_parentheses;
        ArrayList<Integer> closed_Parentheses_Copy = closed_parentheses;
        ArrayList<String> single_Operation_Copy = single_Operation;
        ArrayList<Integer> x_Copy = x;
        boolean x_left = true;
        
        while(x_left){
            int i = 0;
            while (open_Parentheses_Copy.size()>0){
                int o_p_c = 0;
                int c_p_c = 0;
                for(int j = 0; j<closed_Parentheses_Copy.size(); j++){
                    for(int k =0; k<closed_Parentheses_Copy.size();k++){
                        if(open_Parentheses_Copy.get(k) < closed_Parentheses_Copy.get(j)){
                            o_p_c = open_Parentheses_Copy.get(k);
                            c_p_c = closed_Parentheses_Copy.get(i);
                        }

                    }
                    
                    
                    String tester= "";
                    for(int k = o_p_c+1; k<c_p_c;k++){
                        tester += single_Operation_Copy.get(k);
                    }
                    
                    reader inside_Parentheses = new reader(tester);
                    inside_Parentheses.categorizer();
                    double paratheses_value = inside_Parentheses.function_Evalulate(input);
                    
                    x_Copy.clear();
                    open_Parentheses_Copy.clear();
                    closed_Parentheses_Copy.clear();
                    for(int k =o_p_c; k<=c_p_c;k++){
                        single_Operation_Copy.remove(o_p_c);
                    }
                    single_Operation_Copy.add(o_p_c, Double.toString(paratheses_value));

                    
                    for(int k = 0; k<single_Operation_Copy.size();k++){
                        if(single_Operation_Copy.get(k).contains("x")){
                            x_Copy.add(k);
                        }
                        if(single_Operation_Copy.get(k).contains("(")){
                            open_Parentheses_Copy.add(k);
                            
                        }
                        if(single_Operation_Copy.get(k).contains(")")){
                            closed_Parentheses_Copy.add(k);
                        }
                    }


                        
                }
                
                
            }
            if(x_Copy.size()==1){
                x_left = false;
            }
            if(x_Copy.size()<1){
                break;
            }
            boolean exponentiated = false;
            boolean mutiplied = false;
            

            double temporary_x = input;
            if(x_Copy.get(i)+1<single_Operation_Copy.size()){
                if(single_Operation_Copy.get(x_Copy.get(i)+1).contains("^")){
                    temporary_x= 1;
                    String value = single_Operation_Copy.get(x_Copy.get(i)+1);
                    String exponent_value = value.substring(1);
                    exponentiated = true;
                   

                    for(int j = 0; j<Double.parseDouble(exponent_value); j++){
                        temporary_x *= input;
                    }
                }
            }   
            try{
                double x_multiplier = Double.parseDouble(single_Operation_Copy.get(x_Copy.get(i)-1));
                temporary_x *= x_multiplier;
                mutiplied = true;

            }catch(Exception e){

            }
            
            

            if(exponentiated){
                single_Operation_Copy.remove(x.get(i)+1);
            }
            if(mutiplied){
                int k = x_Copy.get(i);
                single_Operation_Copy.remove(k);
                single_Operation_Copy.remove(k-1);
                single_Operation_Copy.add(k-1,Double.toString(temporary_x));
            }else{
                int k = x_Copy.get(i);
                single_Operation_Copy.remove(k);
                single_Operation_Copy.add(k, Double.toString(temporary_x));
            }
            x_Copy.clear();
            for (int j =0; j < single_Operation_Copy.size(); j++){

                if(single_Operation_Copy.get(j).contains("x")){

                    x_Copy.add(j);

                }
            }







        }
        
        double final_value = 0;
        final_value += Double.parseDouble(single_Operation_Copy.get(0));
        ArrayList<Double> mdvalue = new ArrayList<>();
        for(int i=0;i<single_Operation_Copy.size();i++){
            
            if(single_Operation_Copy.get(i).equals("*")){
                md.add(i);
                mdvalue.add(Double.parseDouble(single_Operation_Copy.get(i+1))*Double.parseDouble(single_Operation_Copy.get(i-1)));
            }else if(single_Operation_Copy.get(i).equals("/")){
                md.add(i);
                mdvalue.add(Double.parseDouble(single_Operation_Copy.get(i+1))/Double.parseDouble(single_Operation_Copy.get(i-1)));
            }
        }
        for(int i=0;i<md.size();i++){
            single_Operation_Copy.remove(md.get(i));
            single_Operation_Copy.remove(md.get(i));
            single_Operation_Copy.remove(md.get(i)-1);
            single_Operation_Copy.add(md.get(i)-1, Double.toString(mdvalue.get(i)));
        }
        
        for(int i=0;i<single_Operation_Copy.size();i++){
            
            if(single_Operation_Copy.get(i).equals("+")){
                final_value += Double.parseDouble(single_Operation_Copy.get(i+1));
            }else if(single_Operation_Copy.get(i).equals("-")){
                final_value -= Double.parseDouble(single_Operation_Copy.get(i+1));
            }
            
        }



        return final_value;
    }
}


