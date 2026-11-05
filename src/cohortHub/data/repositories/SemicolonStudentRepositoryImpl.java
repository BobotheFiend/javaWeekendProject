package cohortHub.data.repositories;

import cohortHub.data.models.SemicolonStudent;
import cohortHub.utils.Generate.*;

import java.util.Map;
import java.util.TreeMap;

import static cohortHub.utils.Generate.generateNumber;

public class SemicolonStudentRepositoryImpl implements SemicolonStudentRepository {

    private final static Map<String, SemicolonStudent> semicolonStudentRepository = new TreeMap<String, SemicolonStudent>();
    private int count;

    @Override
    public SemicolonStudent save(SemicolonStudent semicolonStudent) {
        generateId(semicolonStudent);
        semicolonStudentRepository.put(semicolonStudent.getId(), semicolonStudent);
        return semicolonStudent;

    }

    @Override
    public SemicolonStudent findById(String id) {

        return semicolonStudentRepository.get(id);
    }

    @Override
    public void delete(String id) {
        semicolonStudentRepository.remove(id);
    }

    @Override
    public SemicolonStudent update(SemicolonStudent semicolonStudent) {
        return null;
    }

    @Override
    public int length() {
        return semicolonStudentRepository.size();
    }

    @Override
    public void clearAll() {
        semicolonStudentRepository.clear();
    }

    private void generateId(SemicolonStudent semicolonStudent) {
       count++;
       semicolonStudent.setId(generateNumber(semicolonStudent, count));
    }
}
