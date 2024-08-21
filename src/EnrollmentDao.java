import java.util.*;

public class EnrollmentDao implements Dao<Enrollment>{
    private List<Enrollment> enrollmentList = new ArrayList<>();



    public EnrollmentDao() {

    }

    public Optional<Enrollment> getStu(int id) {

        for (Enrollment enroll: enrollmentList){

            if(id == enroll.getEnrollStudentID()){

                return Optional.of(enroll);
            }

        }

        return null;
    }

    @Override
    public Optional<Enrollment> get(int id) {

        for (Enrollment enroll: enrollmentList){

            if(id == enroll.getEnrollmentID()){

                return Optional.of(enroll);
            }

        }

        return null;
    }

    @Override
    public List<Enrollment> getAll() {



        return enrollmentList;
    }

    @Override
    public String getAllSTR() {

        return null;
    }

    @Override
    public void save(Enrollment enroll) {
        enrollmentList.add(enroll);


    }

    @Override
    public void update(Enrollment enroll, String[] params) {
        enroll.setEnrollmentID(Objects.requireNonNull(
                Integer.parseInt(params[0]), "Enrollment ID cannot be null"));
        enroll.setEnrollStudentID(Objects.requireNonNull(
                Integer.parseInt(params[1]), "Student ID cannot be null"));


        enrollmentList.add(enroll);



    }




    @Override
    public void delete(Enrollment enroll) {
        enrollmentList.remove(enroll);
    }
}











