package cohortHub.data.repositories;

import cohortHub.data.models.CohortHubMembership;

public interface CohortHubMemberShipRepository {

    CohortHubMembership save(CohortHubMembership cohortHubMembership);
    CohortHubMembership findById(String id);
    void delete(String id);
    CohortHubMembership update(CohortHubMembership cohortHubMembership);
    
}
