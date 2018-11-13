package visitindia.androcafe.myexpensebook.model;

public class StatusDataModel {
    int id;
    String date;
    String home;
    String friends;
    String ele;
    String groc;
    String med;
    String hot;
    String edu;
    String fit;
    String tra;
    String shop;
    String ins;
    String rec;
    String ent;
    String loan;
    String tax;
    String othe;

    public StatusDataModel(int id, String date, String home, String friends, String ele, String groc, String med, String hot, String edu, String fit, String tra, String shop, String ins, String rec, String ent, String loan, String tax, String othe) {
        this.id = id;
        this.date = date;
        this.home=home;
        this.friends=friends;
        this.ele=ele;
        this.groc=groc;
        this.med=med;
        this.hot=hot;
        this.edu=edu;
        this.fit=fit;
        this.tra=tra;
        this.shop=shop;
        this.ins=ins;
        this.rec=rec;
        this.ent=ent;
        this.loan=loan;
        this.tax=tax;
        this.othe=othe;
    }

    public StatusDataModel(int id, String date) {
        this.id = id;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getFriends() {
        return friends;
    }

    public void setFriends(String friends) {
        this.friends = friends;
    }

    public String getEle() {
        return ele;
    }

    public void setEle(String ele) {
        this.ele = ele;
    }

    public String getGroc() {
        return groc;
    }

    public void setGroc(String groc) {
        this.groc = groc;
    }

    public String getMed() {
        return med;
    }

    public void setMed(String med) {
        this.med = med;
    }

    public String getHot() {
        return hot;
    }

    public void setHot(String hot) {
        this.hot = hot;
    }

    public String getEdu() {
        return edu;
    }

    public void setEdu(String edu) {
        this.edu = edu;
    }

    public String getFit() {
        return fit;
    }

    public void setFit(String fit) {
        this.fit = fit;
    }

    public String getTra() {
        return tra;
    }

    public void setTra(String tra) {
        this.tra = tra;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getIns() {
        return ins;
    }

    public void setIns(String ins) {
        this.ins = ins;
    }

    public String getRec() {
        return rec;
    }

    public void setRec(String rec) {
        this.rec = rec;
    }

    public String getEnt() {
        return ent;
    }

    public void setEnt(String ent) {
        this.ent = ent;
    }

    public String getLoan() {
        return loan;
    }

    public void setLoan(String loan) {
        this.loan = loan;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getOthe() {
        return othe;
    }

    public void setOthe(String othe) {
        this.othe = othe;
    }
}
