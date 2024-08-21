class AdditionalTasks extends EquationDecorator {
    public AdditionalTasks(Equation decoratedEquation){
        super(decoratedEquation);
    }
    @Override
    public  int cost(String Assesment){
        return super.cost(Assesment) + 5;
    }

}