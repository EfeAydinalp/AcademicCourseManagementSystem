public class simpleEquation implements Equation {

    @Override
    public int cost(String Assesment){

        if(Assesment.equals("MultipleChoice")){
            return 15;
        }
        else if (Assesment.equals("Essaybased")){
            return 10;
        }

        return 0;
    }
}
