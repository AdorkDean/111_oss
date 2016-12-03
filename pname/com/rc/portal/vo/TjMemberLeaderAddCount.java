package com.rc.portal.vo;

import java.util.Date;

public class TjMemberLeaderAddCount {
    private Long id;

    private String dateStr;

    private Integer leaderCount;

    private Integer memberCount;

    private Integer powderCount;

    private Integer leaderSum;

    private Integer memberSum;

    private Integer powderSum;

    private Date createDt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public Integer getLeaderCount() {
        return leaderCount;
    }

    public void setLeaderCount(Integer leaderCount) {
        this.leaderCount = leaderCount;
    }

    public Integer getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(Integer memberCount) {
        this.memberCount = memberCount;
    }

    public Integer getPowderCount() {
        return powderCount;
    }

    public void setPowderCount(Integer powderCount) {
        this.powderCount = powderCount;
    }

    public Integer getLeaderSum() {
        return leaderSum;
    }

    public void setLeaderSum(Integer leaderSum) {
        this.leaderSum = leaderSum;
    }

    public Integer getMemberSum() {
        return memberSum;
    }

    public void setMemberSum(Integer memberSum) {
        this.memberSum = memberSum;
    }

    public Integer getPowderSum() {
        return powderSum;
    }

    public void setPowderSum(Integer powderSum) {
        this.powderSum = powderSum;
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }
}