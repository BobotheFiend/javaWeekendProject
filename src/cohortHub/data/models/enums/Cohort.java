package cohortHub.data.models.enums;

import lombok.Data;

public enum Cohort {
    TWENTY_NINE(29),
    THIRTY(30),
    THIRTY_ONE(31),
    THIRTY_TWO(32),
    THIRTY_THREE(33),
    THIRTY_FOUR(34);


    private int value;
    Cohort(int cohortNumber) {
        this.value = cohortNumber;
    }

    public int getValue() {
        return value;
    }
}
