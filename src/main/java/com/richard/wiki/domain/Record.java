package com.richard.wiki.domain;

public class Record {
    private String recordId;

    private Integer type;

    private String desc;

    private String recordTime;

    private String operaUrl;

    private String methodName;

    private String serviceName;

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    public String getOperaUrl() {
        return operaUrl;
    }

    public void setOperaUrl(String operaUrl) {
        this.operaUrl = operaUrl;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", recordId=").append(recordId);
        sb.append(", type=").append(type);
        sb.append(", desc=").append(desc);
        sb.append(", recordTime=").append(recordTime);
        sb.append(", operaUrl=").append(operaUrl);
        sb.append(", methodName=").append(methodName);
        sb.append(", serviceName=").append(serviceName);
        sb.append("]");
        return sb.toString();
    }
}