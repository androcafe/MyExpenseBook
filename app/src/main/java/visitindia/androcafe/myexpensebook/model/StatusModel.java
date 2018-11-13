package visitindia.androcafe.myexpensebook.model;

public class StatusModel {
    String Category;
    long amount;

    public StatusModel(String category, long amount) {
        Category = category;
        this.amount = amount;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
