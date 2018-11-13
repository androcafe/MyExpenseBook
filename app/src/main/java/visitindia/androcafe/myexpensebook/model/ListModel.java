package visitindia.androcafe.myexpensebook.model;

public class ListModel {
    int id;
    String date;
    String expendOn;
    String expendMoney;

    public ListModel(int id, String date, String expendOn, String expendMoney) {
        this.id=id;
        this.date=date;
        this.expendMoney=expendMoney;
        this.expendOn=expendOn;
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

    public String getExpendOn() {
        return expendOn;
    }

    public void setExpendOn(String expendOn) {
        this.expendOn = expendOn;
    }

    public String getExpendMoney() {
        return expendMoney;
    }

    public void setExpendMoney(String expendMoney) {
        this.expendMoney = expendMoney;
    }
}
