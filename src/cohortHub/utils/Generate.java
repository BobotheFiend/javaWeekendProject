package cohortHub.utils;

import cohortHub.data.models.SemicolonStudent;

public class Generate {

    public static String generateNumber(SemicolonStudent student, int number) {
        StringBuilder result = new StringBuilder();

        String startingName = String.valueOf(student.getName().charAt(0));
        String cohortNumber = String.valueOf(student.getCohort().getValue());

        result.append(startingName);
        result.append("-");
        result.append(cohortNumber);
        result.append("/");
        result.append(String.format("%02d",number));

        return result.toString();
    }

}
