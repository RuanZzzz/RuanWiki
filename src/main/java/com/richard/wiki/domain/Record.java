package com.richard.wiki.domain;

public class Record {
    private Integer id;

    private Integer goodsId;

    private Integer goodsCount;

    private Float goodsPrice;

    private Float goodsTotalPrice;

    private String recordTime;

    private String createdAt;

    private String operaType;

    private String recordName;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }

    public Float getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Float goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Float getGoodsTotalPrice() {
        return goodsTotalPrice;
    }

    public void setGoodsTotalPrice(Float goodsTotalPrice) {
        this.goodsTotalPrice = goodsTotalPrice;
    }

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getOperaType() {
        return operaType;
    }

    public void setOperaType(String operaType) {
        this.operaType = operaType;
    }

    public String getRecordName() {
        return recordName;
    }

    public void setRecordName(String recordName) {
        this.recordName = recordName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", goodsCount=").append(goodsCount);
        sb.append(", goodsPrice=").append(goodsPrice);
        sb.append(", goodsTotalPrice=").append(goodsTotalPrice);
        sb.append(", recordTime=").append(recordTime);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", operaType=").append(operaType);
        sb.append(", recordName=").append(recordName);
        sb.append(", remark=").append(remark);
        sb.append("]");
        return sb.toString();
    }
}