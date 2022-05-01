package helloFx.Beans;

public class SortsBean {
    private String book_id;
    private String mainSort;
    private String subSort;

    public SortsBean(String mainSort, String subSort) {
        this.mainSort = mainSort;
        this.subSort = subSort;
    }

    public SortsBean(String book_id, String mainSort, String subSort) {
        this.book_id = book_id;
        this.mainSort = mainSort;
        this.subSort = subSort;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getMainSort() {
        return mainSort;
    }

    public void setMainSort(String mainSort) {
        this.mainSort = mainSort;
    }

    public String getSubSort() {
        return subSort;
    }

    public void setSubSort(String subSort) {
        this.subSort = subSort;
    }

}
