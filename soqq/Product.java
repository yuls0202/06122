package soqq;

public class Product {
    private int no;
    private String imP;
    private String imPInfo;
    private Object date;
    
    public Product(int no, String imP, String imPInfo) {
        super();
        this.no = no;
        this.imP = imP;
        this.imPInfo = imPInfo;
    }
    
    public Product(int no, String imP, String imPInfo, Object date) {
        super();
        this.no = no;
        this.imP = imP;
        this.imPInfo = imPInfo;
        this.date = date;
    }
 
    public int getNo() {
        return no;
    }
 
    public void setNo(int no) {
        this.no = no;
    }
 
    public Object getDate() {
        return date;
    }
 
    public void setDate(Object date) {
        this.date = date;
    }
    
    public String getImP() {
        return imP;
    }
    public void setImP(String imP) {
        this.imP = imP;
    }
    public String getImPInfo() {
        return imPInfo;
    }
    public void setImPInfo(String imPInfo) {
        this.imPInfo = imPInfo;
    }
}
