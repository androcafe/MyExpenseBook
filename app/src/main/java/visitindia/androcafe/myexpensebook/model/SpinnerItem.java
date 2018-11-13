package visitindia.androcafe.myexpensebook.model;

public class SpinnerItem {
    String list_name;
    int list_image;

    public SpinnerItem(String list_name, int list_image) {
        this.list_name = list_name;
        this.list_image = list_image;
    }

    public String getList_name() {
        return list_name;
    }

    public void setList_name(String list_name) {
        this.list_name = list_name;
    }

    public int getList_image() {
        return list_image;
    }

    public void setList_image(int list_image) {
        this.list_image = list_image;
    }
}
