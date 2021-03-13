package censusanalyser;

import com.opencsv.bean.*;

public class StateCode {

    public String getSrNo() {
        return SrNo;
    }

    public String getStateName() {
        return StateName;
    }

    public int getTIN() {
        return TIN;
    }

    public String getStateCode() {
        return StateCode;
    }

    @CsvBindByName(column = "SrNo", required = true)
    public String SrNo;

    @CsvBindByName(column = "StateName", required = true)
    public String StateName;

    @CsvBindByName(column = "TIN", required = true)
    public int TIN;

    @CsvBindByName(column = "StateCode", required = true)
    public String StateCode;

    @Override
    public String toString() {
        return "StateCode{" +
                "SrNo='" + SrNo + '\'' +
                ", StateName='" + StateName + '\'' +
                ", TIN=" + TIN +
                ", StateCode='" + StateCode + '\'' +
                '}';
    }
}
