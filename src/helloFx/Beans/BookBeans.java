package helloFx.Beans;

public class BookBeans {
    private String book_Id;
    private String book_Name;
    private String book_Author;
    private int book_Num;
    private String book_Mainsort;
    private String book_Subsort;

    public BookBeans(String book_Id, String book_Name, String book_Author, int book_Num) {
        this.book_Id = book_Id;
        this.book_Name = book_Name;
        this.book_Author = book_Author;
        this.book_Num = book_Num;
    }

    public BookBeans(String book_Id, String book_Name, String book_Author, int book_Num, String book_Mainsort,
            String book_Subsort) {
        this.book_Id = book_Id;
        this.book_Name = book_Name;
        this.book_Author = book_Author;
        this.book_Num = book_Num;
        this.book_Mainsort = book_Mainsort;
        this.book_Subsort = book_Subsort;
    }

    public BookBeans(String book_Name, String book_Subsort) {
        this.book_Name = book_Name;
        this.book_Subsort = book_Subsort;
    }

    public String getBook_Id() {
        return book_Id;
    }

    public void setBook_Id(String book_Id) {
        this.book_Id = book_Id;
    }

    public String getBook_Name() {
        return book_Name;
    }

    public void setBook_Name(String book_Name) {
        this.book_Name = book_Name;
    }

    public String getBook_Author() {
        return book_Author;
    }

    public void setBook_Author(String book_Author) {
        this.book_Author = book_Author;
    }

    public int getBook_Num() {
        return book_Num;
    }

    public void setBook_Num(int book_Num) {
        this.book_Num = book_Num;
    }

    public String getBook_Mainsort() {
        return book_Mainsort;
    }

    public void setBook_Mainsort(String book_Mainsort) {
        this.book_Mainsort = book_Mainsort;
    }

    public String getBook_Subsort() {
        return book_Subsort;
    }

    public void setBook_Subsort(String book_Subsort) {
        this.book_Subsort = book_Subsort;
    }

}
