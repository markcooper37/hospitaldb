package com.backend.hospitaldb.ward;

public class Ward {

    private int wardId;
    private String wardName;
    private Long hospital;

    public Ward() {

    }

    public Ward(int wardId, String wardName, Long hospital) {
        this.wardId = wardId;
        this.wardName = wardName;
        this.hospital = hospital;
    }

    public int getWardId() {
        return wardId;
    }

    public void setWardId(int wardId) {
        this.wardId = wardId;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public Long getHospital() {
        return hospital;
    }

    public void setHospital(Long hospital) {
        this.hospital = hospital;
    }

    @Override
    public String toString() {
        return "Ward{" +
                "wardId=" + wardId +
                ", wardName='" + wardName + '\'' +
                ", hospital=" + hospital +
                '}';
    }
}
