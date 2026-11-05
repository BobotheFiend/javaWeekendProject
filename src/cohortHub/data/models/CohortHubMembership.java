package cohortHub.data.models;


import cohortHub.data.models.enums.Membership;
import lombok.Data;

@Data
public class CohortHubMembership  implements Comparable<SemicolonStudent>{
    private SemicolonStudent semicolonStudent;
    private Membership membership;


    @Override
    public int compareTo(SemicolonStudent o) {
        return this.semicolonStudent.getName().compareTo(o.getName());
    }
}
