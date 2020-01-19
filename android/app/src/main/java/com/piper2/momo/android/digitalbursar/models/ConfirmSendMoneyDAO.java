package com.piper2.momo.android.digitalbursar.models;

public class ConfirmSendMoneyDAO {

    private String referenceId;
    private long to;

    public ConfirmSendMoneyDAO(String referenceId, long recievingStudentAccountNumber){
        this.referenceId = referenceId;
        this.to = recievingStudentAccountNumber;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public long getTo() {
        return to;
    }

    public void setTo(long to) {
        this.to = to;
    }
}
