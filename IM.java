package sor;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
 
public class IM {
    
	public static void main(String[] args) {
	    Connection conn = null;

        try{
            // 1. 드라이버 로딩
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	 // 2. 연결하기
        	String url = "jdbc:mysql://localhost/mungtwo";
        	conn = DriverManager.getConnection(url, "root", "1234");
            System.out.println("연결 성공");
        }
        catch(ClassNotFoundException e){
            System.out.println("드라이버 로딩 실패");
        }
        catch(SQLException e){
            System.out.println("에러: " + e);
        }
        finally{
            try{
                if( conn != null && !conn.isClosed()){
                    conn.close();
                }
            }
            catch( SQLException e){
                e.printStackTrace();
            }
        }
    }
	
    /* 1 상품등록 */
    public int insert (String imP, String imPInfo) throws Exception{        
        Connection con = makeConnection();
        String sql = "insert into InventoryM values(InventoryM_seq.nextval,?,?,sysdate)";
        PreparedStatement pstat = con.prepareStatement(sql);
        pstat.setString(1, imP);
        pstat.setString(2, imPInfo);
        int result = pstat.executeUpdate();
        con.commit();
        pstat.close();
        con.close();
        return result;        
    }
    
    /* 2 상품조회*/
    public List <HashMap<String, Object>> inquiry() throws Exception{
        String sql = "select * from InventoryM";
        try(    Connection con = makeConnection();        
                PreparedStatement pstat = con.prepareStatement(sql);
            ){
            ResultSet rs = pstat.executeQuery();
            List <HashMap<String, Object>> hasmapList = new ArrayList<HashMap<String, Object>> ();            
            while(rs.next()) {
                HashMap<String, Object> hashput = new HashMap<String, Object>();
                hashput.put("imNo",rs.getInt(1));
                hashput.put("imP",rs.getString(2));
                hashput.put("imPInfo",rs.getString(3));
                hashput.put("imDate",rs.getDate(4));
                hasmapList.add(hashput);
            }
            return hasmapList;
        }
        
    }
    
    /* 3 상품검색 - 이름으로 검색*/
    public List <Product> isIdExist(String Idck) throws Exception{
        String sql = "Select * from InventoryM where imp = ?";
        try(    Connection con = makeConnection();
                PreparedStatement pstat = con.prepareStatement(sql);
                ){
            pstat.setString(1, Idck);
            ResultSet rs = pstat.executeQuery();
            List <Product> isIdckList = new ArrayList <Product> ();
            while (rs.next()) {
                int no = rs.getInt(1);
                String p =rs.getString(2);
                String info = rs.getString(3);
                Date date = rs.getDate(4);
                isIdckList.add(new Product(no,p,info,date));
            }
            return isIdckList;
        }    
    }
    
    /* 4 상품삭제 */
    public int delete(int imNo) throws Exception{
        String sql = "delete from InventoryM where imNo=?";        
        try(    Connection con = makeConnection();
                PreparedStatement pstat = con.prepareStatement(sql);
            ){
            pstat.setInt(1, imNo);
            int result = pstat.executeUpdate();
            con.commit();
            return result;            
        }
    }   
}
