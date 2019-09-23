package app;

public class CalculationTypes{
    
    public double integrate(String func,int left_bound, int right_bound){

        double solution = 0;
        if(left_bound < right_bound){
            for(double i = left_bound; i<=right_bound; i+= .005){
                reader j = new reader(func);
                
               solution += j.function_Evalulate(i)*.005;
              
            }
        }
        if(right_bound < left_bound){
            for(double i = right_bound; i<=left_bound; i+= .001){
                reader j = new reader(func);
                
               solution -= j.function_Evalulate(i+.01)-j.function_Evalulate(i)*.001;
                
            }  
        }
        return solution;  
    }
    public double derive(String func, double xValue){
        reader i = new reader(func);
        reader j = new reader(func);
       
        return (i.function_Evalulate(xValue+.000001)- j.function_Evalulate(xValue))/.000001;
        
        
        
        
    }
    public double solve(String func, double xValue){
        reader j = new reader(func);
       
        return j.function_Evalulate(xValue);
    }

}