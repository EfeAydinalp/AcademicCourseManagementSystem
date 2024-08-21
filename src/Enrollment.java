import java.util.ArrayList;
import java.util.List;

public class Enrollment {

        private int enrollmentID;
        private int enrollStudentID;
        private List<String> assessmentTypes;
        private List<List<String>> tasks;

        public Enrollment(int enrollmentID, int enrollStudentID) {
            this.enrollmentID = enrollmentID;
            this.enrollStudentID = enrollStudentID;
            this.assessmentTypes = new ArrayList<>();
            this.tasks = new ArrayList<List<String>>();
        }

        public int getEnrollmentID() {
            return enrollmentID;
        }

        public void setEnrollmentID(int enrollmentID) {
            this.enrollmentID = enrollmentID;
        }

        public int getEnrollStudentID() {
            return enrollStudentID;
        }

        public void setEnrollStudentID(int enrollStudentID) {
            this.enrollStudentID = enrollStudentID;
        }

        public List<String> getAssessmentTypes() {
            return assessmentTypes;
        }

        public void setAssessmentTypes(List<String> assessmentTypes) {
            this.assessmentTypes = assessmentTypes;
        }

        public List<List<String>> getTasks() {
            return tasks;
        }

        public void setTasks(List<List<String>> tasks) {
            this.tasks = tasks;
        }

        // Helper method to add assessment types
        public void addAssessmentType(String type) {
            assessmentTypes.add(type);
        }

        // Helper method to add tasks for an assessment type
        public void addTasksForAssessmentType(List<String> tasksForType) {

            tasks.add(tasksForType);
        }
    }
