package com.example.freshnexdelivery;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class OrderData {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("oid")
    @Expose
    private String oid;
    @SerializedName("uid")
    @Expose
    private String uid;
    @SerializedName("pname")
    @Expose
    private String pname;
    @SerializedName("pid")
    @Expose
    private String pid;
    @SerializedName("ptype")
    @Expose
    private String ptype;
    @SerializedName("pprice")
    @Expose
    private String pprice;
    @SerializedName("ddate")
    @Expose
    private String ddate;
    @SerializedName("timesloat")
    @Expose
    private String timesloat;
    @SerializedName("order_date")
    @Expose
    private String orderDate;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("qty")
    @Expose
    private String qty;
    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("rate")
    @Expose
    private String rate;
    @SerializedName("p_method")
    @Expose
    private String pMethod;
    @SerializedName("delivery_agent_id")
    @Expose
    private String deliveryAgentId;
    @SerializedName("canceled_by_agent")
    @Expose
    private String canceledByAgent;
    @SerializedName("user")
    @Expose
    private ArrayList<User> user;

    public OrderData(String id, String oid, String uid, String pname, String pid, String ptype, String pprice, String ddate, String timesloat, String orderDate, String status, String qty, String total, String rate, String pMethod, String deliveryAgentId, String canceledByAgent, ArrayList<User> user) {
        this.id = id;
        this.oid = oid;
        this.uid = uid;
        this.pname = pname;
        this.pid = pid;
        this.ptype = ptype;
        this.pprice = pprice;
        this.ddate = ddate;
        this.timesloat = timesloat;
        this.orderDate = orderDate;
        this.status = status;
        this.qty = qty;
        this.total = total;
        this.rate = rate;
        this.pMethod = pMethod;
        this.deliveryAgentId = deliveryAgentId;
        this.canceledByAgent = canceledByAgent;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPtype() {
        return ptype;
    }

    public void setPtype(String ptype) {
        this.ptype = ptype;
    }

    public String getPprice() {
        return pprice;
    }

    public void setPprice(String pprice) {
        this.pprice = pprice;
    }

    public String getDdate() {
        return ddate;
    }

    public void setDdate(String ddate) {
        this.ddate = ddate;
    }

    public String getTimesloat() {
        return timesloat;
    }

    public void setTimesloat(String timesloat) {
        this.timesloat = timesloat;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getpMethod() {
        return pMethod;
    }

    public void setpMethod(String pMethod) {
        this.pMethod = pMethod;
    }

    public String getDeliveryAgentId() {
        return deliveryAgentId;
    }

    public void setDeliveryAgentId(String deliveryAgentId) {
        this.deliveryAgentId = deliveryAgentId;
    }

    public String getCanceledByAgent() {
        return canceledByAgent;
    }

    public void setCanceledByAgent(String canceledByAgent) {
        this.canceledByAgent = canceledByAgent;
    }

    public ArrayList<User> getUser() {
        return user;
    }

    public void setUser(ArrayList<User> user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "OrderData{" +
                "id='" + id + '\'' +
                ", oid='" + oid + '\'' +
                ", uid='" + uid + '\'' +
                ", pname='" + pname + '\'' +
                ", pid='" + pid + '\'' +
                ", ptype='" + ptype + '\'' +
                ", pprice='" + pprice + '\'' +
                ", ddate='" + ddate + '\'' +
                ", timesloat='" + timesloat + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", status='" + status + '\'' +
                ", qty='" + qty + '\'' +
                ", total='" + total + '\'' +
                ", rate='" + rate + '\'' +
                ", pMethod='" + pMethod + '\'' +
                ", deliveryAgentId='" + deliveryAgentId + '\'' +
                ", canceledByAgent='" + canceledByAgent + '\'' +
                ", user=" + user +
                '}';
    }
}
