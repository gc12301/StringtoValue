package app;



public class App {
    public static void main(String[] args) throws Exception {
        String i = "x^2";
        CalculationTypes j = new CalculationTypes();
        double u = j.derive(i,1);
        double a = j.integrate(i, 0, 100);
        double z = j.solve(i, 5);
        System.out.println(u);
        System.out.println(a);
        System.out.print(z);        
        
    }
}