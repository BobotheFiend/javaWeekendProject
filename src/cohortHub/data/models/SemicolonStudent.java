package cohortHub.data.models;

import lombok.Data;
import cohortHub.data.models.enums.Cohort;
import cohortHub.data.models.enums.MemberTag;


@Data
public class SemicolonStudent  {
    private String id;
    private String name;
    private String email;
    private Cohort cohort;
    private MemberTag memberTag;

}
