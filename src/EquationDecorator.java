abstract class EquationDecorator implements Equation {

    protected Equation decoratedEquaiton;

    public EquationDecorator(Equation decoratedEquaiton){
        this.decoratedEquaiton=decoratedEquaiton;
    }

    public int cost(String Assesment){
        return decoratedEquaiton.cost(Assesment);
    }

}
