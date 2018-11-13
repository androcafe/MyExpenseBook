package visitindia.androcafe.myexpensebook;

public interface SpinnerInterface {
    String expenseCat[]={"Family","Friends","Electricity","Grocery","Medical","Hotel","Education","Fitness","Transport","Shopping","Insurance","Recharge","Entertainment","Loan","Tax","Other"};
    int expenseImg[]={R.raw.family,R.raw.friendship,R.raw.idea,R.raw.food,R.raw.hospital,R.raw.hotel,R.raw.books,R.raw.fitness,R.raw.car,R.raw.gift,R.raw.house,R.raw.wifi,R.raw.entertainment,R.raw.loan,R.raw.tax,R.raw.other};
    String month[]={"jan","feb","mar","apr","may","jun","jul","aug","sep","oct","nov","dec"};
    String status[]={"getHome()","getFriends()","getEle()","getGroc()","getMed()" ,"getHot()"
            ,"getEdu()",
            "+getFit()",
            "+getTra()",
            "+getShop()",
            "+getIns()",
            "getRec()",
            "getEnt()",
            "getLoan()",
            "getTax()",
            "getOthe()"};
}
