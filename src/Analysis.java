class Analysis extends EquationDecorator {
    public Analysis(Equation decoratedEquation){
        super(decoratedEquation);
    }
    @Override
    public  int cost(String Assesment){
        return super.cost(Assesment) + 10;
    }

}