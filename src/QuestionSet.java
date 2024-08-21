class QuestionSet extends EquationDecorator {
    public QuestionSet(Equation decoratedEquation){
        super(decoratedEquation);
    }
    @Override
    public  int cost(String Assesment){
        return super.cost(Assesment) + 7;
    }

}