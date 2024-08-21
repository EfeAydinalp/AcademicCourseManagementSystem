class LiteratureReview extends EquationDecorator {
    public LiteratureReview(Equation decoratedEquation){
        super(decoratedEquation);
    }
    @Override
    public  int cost(String Assesment){
        return super.cost(Assesment) + 15;
    }

}
