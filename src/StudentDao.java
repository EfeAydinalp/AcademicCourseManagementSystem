import java.util.*;

public class StudentDao implements Dao<Student> {

    private List<Student> studentList = new ArrayList<>();

    public StudentDao() {
        /*
        studentList.add(new Student(2210356058, "Efe","AYDINALP",
                "530-0000000,","Çankaya"));
        */
    }
    public Optional<Student> getStu(int id) {

        return null;
    }

    @Override
    public Optional<Student> get(int id) {
        for (Student student: studentList){

            if(id == student.getStudentID()){

                return Optional.of(student);
            }

        }
        return null;
    }

    @Override
    public List<Student> getAll() {



        return studentList;
    }
    @Override
    public String getAllSTR() {



        String liste = "";

        for(int i = 0 ; i < studentList.size();i++){

            liste += studentList.get(i).getStudentID() + " " + studentList.get(i).getStudentName() + " " + studentList.get(i).getStudentSurname() + " "
                    +studentList.get(i).getStudentPhoneNumber() + " " + studentList.get(i).getStudentAddress() + "\n";
/*
            System.out.println(studentList.get(i).getStudentID() + " " + studentList.get(i).getStudentName() + " " + studentList.get(i).getStudentSurname() + " "
                    +studentList.get(i).getStudentPhoneNumber() + " " + studentList.get(i).getStudentAddress());

 */
        }

        return liste;
    }

    @Override
    public void save(Student user) {
        studentList.add(user);


    }




    @Override
    public void update(Student user, String[] params) {
        user.setStudentID(Objects.requireNonNull(
                Integer.parseInt(params[0]), "ID cannot be null"));
        user.setStudentName(Objects.requireNonNull(
                params[1], "Name cannot be null"));
        user.setStudentSurname(Objects.requireNonNull(
                params[2], "Surname cannot be null"));
        user.setStudentPhoneNumber(Objects.requireNonNull(
                params[3], "Phone Number cannot be null"));
        user.setStudentAddress(Objects.requireNonNull(
                params[4], "Address cannot be null"));

        studentList.add(user);
    }




    @Override
    public void delete(Student student) {

        studentList.remove(student);
    }
}