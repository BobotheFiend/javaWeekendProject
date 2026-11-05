package cohortHub.data.repositories;

import cohortHub.data.models.SemicolonStudent;

public interface SemicolonStudentRepository {

    SemicolonStudent save(SemicolonStudent semicolonStudent);
    SemicolonStudent findById(String id);
    void delete(String id);
    SemicolonStudent update(SemicolonStudent semicolonStudent);
    int length();
    void clearAll();
}
