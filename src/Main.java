import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static Dao<Student> STUDao;
    private static Dao<Enrollment> ENRDao;

    public static void main(String[] args) throws IOException {

        STUDao = new StudentDao();

        ReadFromFile rf = new ReadFromFile();
        String[][] studentTxt = rf.readFile("student.txt","\t");

        for(int i = 0 ; i < studentTxt.length;i++){



            STUDao.save(new Student(Integer.parseInt(studentTxt[i][0]),studentTxt[i][1].split("\\s+")[0]
                    ,studentTxt[i][1].split("\\s+")[1],studentTxt[i][2],studentTxt[i][3]));

        }
        //userDao.getAll();

        ENRDao = new EnrollmentDao();

        ReadFromFile rf2 = new ReadFromFile();
        String[][] enrollmentTxt = rf2.readFile("courseEnrollment.txt","\\s+");



        int ENR_ID_SAVER=0;

        for(int i = 0 ; i < enrollmentTxt.length;i++){


            if((enrollmentTxt[i][0]).equals("MultipleChoice")){

                ENRDao.get(ENR_ID_SAVER).get().addAssessmentType("MultipleChoice");

                List<String> taskList = new ArrayList<>();

                for(int j = 1; j < enrollmentTxt[i].length;j++){
                    if(enrollmentTxt[i][j] != null) {
                        taskList.add(enrollmentTxt[i][j]);
                    }
                    else{
                        break;
                    }
                }

                ENRDao.get(ENR_ID_SAVER).get().addTasksForAssessmentType(taskList);


            }
            else if((enrollmentTxt[i][0]).equals("Essaybased")){

                ENRDao.get(ENR_ID_SAVER).get().addAssessmentType("Essaybased");

                List<String> taskList = new ArrayList<>();

                for(int j = 1; j < enrollmentTxt[i].length-1;j++){

                    if(enrollmentTxt[i][j] != null) {
                        taskList.add(enrollmentTxt[i][j]);
                    }
                    else{
                        break;
                    }
                }

                ENRDao.get(ENR_ID_SAVER).get().addTasksForAssessmentType(taskList);

            }
            else{

                ENRDao.save(new Enrollment(Integer.parseInt(enrollmentTxt[i][0]),Integer.parseInt(enrollmentTxt[i][1])));
                ENR_ID_SAVER = Integer.parseInt(enrollmentTxt[i][0]);

            }


        }

        //Equation equation = new LiteratureReview(new simpleEquation());
        //Equation equation1 = new QuestionSet(new LiteratureReview(new simpleEquation()));
        //System.out.println(equation.cost("Essaybased"));



        ReadFromFile rf3 = new ReadFromFile();
        String[][] inputTxt = rf3.readFile(args[0],"\\s+");




        String filePath = "Output.txt";

        File output = new File(filePath);
        FileWriter writter = new FileWriter(output,false);
        BufferedWriter Yaz = new BufferedWriter(writter);

        //System.out.println(STUDao.get(5).get().getStudentName());

        for(int i = 0; i< inputTxt.length;i++){

            if(inputTxt[i][0].equals("AddStudent")){

                String address = "Address: " ;
                for (int j = 5; j<inputTxt[i].length;j++){
                    if(inputTxt[i][j] != null) {
                        address += inputTxt[i][j] + " ";
                    }
                }

                Yaz.write("Student " + inputTxt[i][1] + " " + inputTxt[i][2] + " added.\n");

                STUDao.save(new Student(Integer.parseInt(inputTxt[i][1]),inputTxt[i][2]
                        ,inputTxt[i][3],inputTxt[i][4],address));

            }
            else if(inputTxt[i][0].equals("RemoveStudent")){

                Yaz.write("Student " + inputTxt[i][1] + " "
                        + STUDao.get(Integer.parseInt(inputTxt[i][1])).get().getStudentName() + " removed.\n");

                STUDao.delete(STUDao.get(Integer.parseInt(inputTxt[i][1])).get());



                ENRDao.delete(ENRDao.getStu(Integer.parseInt(inputTxt[i][1])).get());


            }
            else if(inputTxt[i][0].equals("CreateEnrollment")){

                Yaz.write("CourseEnrollment " + inputTxt[i][1] + " created.\n");

                ENRDao.save(new Enrollment(Integer.parseInt(inputTxt[i][1]),Integer.parseInt((inputTxt[i][2]))));


            }
            else if(inputTxt[i][0].equals("AddAssessment")){

                Yaz.write(inputTxt[i][2] + " assessment added to enrollment " + inputTxt[i][1] + "\n");

                ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().addAssessmentType(inputTxt[i][2]);

                List<String> taskList = new ArrayList<>();

                for(int j = 3; j < inputTxt[i].length;j++) {

                    if (inputTxt[i][j] != null) {

                        taskList.add(inputTxt[i][j]);
                    }
                }

                ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().addTasksForAssessmentType(taskList);


            }
            else if(inputTxt[i][0].equals("TotalFee")){
                Yaz.write("TotalFee for enrollment "+ inputTxt[i][1] + "\n");

                int cost = 0;


                for(int j = 0; j < (ENRDao.get(Integer.parseInt(inputTxt[i][1]))).get().getAssessmentTypes().size();j++){

                    String task_str = "";

                    for(int t = 0;t<ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getTasks().get(j).size();t++){

                        task_str += ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getTasks().get(j).get(t) + " ";

                    }

                    Yaz.write("\t"+ ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getAssessmentTypes().get(j) + " "
                            + task_str + " ");

                        if(ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getTasks().get(j).contains("Analysis")
                        && ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getTasks().get(j).contains("QuestionSet")
                        &&ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getTasks().get(j).contains("LiteratureReview")) {
                            Equation equation = new Analysis(new QuestionSet(new LiteratureReview(new simpleEquation())));
                            Yaz.write(""+equation.cost(ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getAssessmentTypes().get(j)));

                            cost += equation.cost(ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getAssessmentTypes().get(j));

                        }

                    else if(ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getTasks().get(j).contains("Analysis")
                            && ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getTasks().get(j).contains("QuestionSet")
                            &&ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getTasks().get(j).contains("AdditionalTasks")) {
                        Equation equation = new Analysis(new QuestionSet(new AdditionalTasks(new simpleEquation())));
                        Yaz.write(""+equation.cost(ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getAssessmentTypes().get(j)));

                        cost += equation.cost(ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getAssessmentTypes().get(j));

                    }
                        else if(ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getTasks().get(j).contains("QuestionSet")
                                && ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getTasks().get(j).contains("LiteratureReview")
                                &&ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getTasks().get(j).contains("AdditionalTasks")) {
                            Equation equation = new QuestionSet(new LiteratureReview(new AdditionalTasks(new simpleEquation())));
                            Yaz.write(""+equation.cost(ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getAssessmentTypes().get(j)));

                            cost += equation.cost(ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getAssessmentTypes().get(j));

                        }
                        else if(ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getTasks().get(j).contains("Analysis")
                                && ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getTasks().get(j).contains("LiteratureReview")
                                &&ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getTasks().get(j).contains("AdditionalTasks")) {
                            Equation equation = new Analysis(new LiteratureReview(new AdditionalTasks(new simpleEquation())));
                            Yaz.write(""+equation.cost(ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getAssessmentTypes().get(j)));

                            cost += equation.cost(ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getAssessmentTypes().get(j));

                        }

                        else if(ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getTasks().get(j).contains("Analysis")
                                && ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getTasks().get(j).contains("QuestionSet")) {
                            Equation equation = new Analysis(new QuestionSet(new simpleEquation()));
                            Yaz.write(""+equation.cost(ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getAssessmentTypes().get(j)));

                            cost += equation.cost(ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getAssessmentTypes().get(j));

                        }
                        else if(ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getTasks().get(j).contains("Analysis")
                                && ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getTasks().get(j).contains("LiteratureReview")) {
                            Equation equation = new Analysis(new LiteratureReview(new simpleEquation()));
                            Yaz.write(""+equation.cost(ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getAssessmentTypes().get(j)));

                            cost += equation.cost(ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getAssessmentTypes().get(j));

                        }
                        else if(ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getTasks().get(j).contains("Analysis")
                                && ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getTasks().get(j).contains("AdditionalTasks")) {
                            Equation equation = new Analysis(new AdditionalTasks(new simpleEquation()));
                            Yaz.write(""+equation.cost(ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getAssessmentTypes().get(j)));

                            cost += equation.cost(ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getAssessmentTypes().get(j));

                        }
                        else if(ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getTasks().get(j).contains("QuestionSet")
                                && ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getTasks().get(j).contains("LiteratureReview")) {
                            Equation equation = new QuestionSet(new LiteratureReview(new simpleEquation()));
                            Yaz.write(""+equation.cost(ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getAssessmentTypes().get(j)));

                            cost += equation.cost(ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getAssessmentTypes().get(j));


                        }
                        else if(ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getTasks().get(j).contains("QuestionSet")
                                && ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getTasks().get(j).contains("AdditionalTasks")) {
                            Equation equation = new QuestionSet(new AdditionalTasks(new simpleEquation()));
                            Yaz.write(""+equation.cost(ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getAssessmentTypes().get(j)));

                            cost += equation.cost(ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getAssessmentTypes().get(j));

                        }
                        else if(ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getTasks().get(j).contains("QuestionSet")
                                && ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getTasks().get(j).contains("AdditionalTasks")) {
                            Equation equation = new QuestionSet(new AdditionalTasks(new simpleEquation()));
                            Yaz.write(""+equation.cost(ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getAssessmentTypes().get(j)));

                            cost += equation.cost(ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getAssessmentTypes().get(j));

                        }
                        else if(ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getTasks().get(j).contains("QuestionSet")) {
                            Equation equation = new QuestionSet(new simpleEquation());
                            Yaz.write(""+equation.cost(ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getAssessmentTypes().get(j)));

                            cost += equation.cost(ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getAssessmentTypes().get(j));

                        }
                        else if(ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getTasks().get(j).contains("AdditionalTasks")) {
                            Equation equation = new AdditionalTasks(new simpleEquation());
                            Yaz.write(""+equation.cost(ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getAssessmentTypes().get(j)));

                            cost += equation.cost(ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getAssessmentTypes().get(j));

                        }
                        else if(ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getTasks().get(j).contains("LiteratureReview")) {
                            Equation equation = new LiteratureReview(new simpleEquation());
                            Yaz.write(""+equation.cost(ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getAssessmentTypes().get(j)));

                            cost += equation.cost(ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getAssessmentTypes().get(j));

                        }
                        else if(ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getTasks().get(j).contains("Analysis")) {
                            Equation equation = new Analysis(new simpleEquation());
                            Yaz.write(""+equation.cost(ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getAssessmentTypes().get(j)));

                            cost += equation.cost(ENRDao.get(Integer.parseInt(inputTxt[i][1])).get().getAssessmentTypes().get(j));

                        }




                    Yaz.write("$\n");

                }

                Yaz.write("\tTotal : " + cost+"$\n");

                //Equation equation = new LiteratureReview(new simpleEquation());
                //Equation equation1 = new QuestionSet(new LiteratureReview(new simpleEquation()));
                //System.out.println(equation.cost("Essaybased"));

            }
            else if(inputTxt[i][0].equals("ListStudents")) {
                Yaz.write("Student List: \n");
                Yaz.write(STUDao.getAllSTR());







            }


        } // input for
        Yaz.close();
    }
}