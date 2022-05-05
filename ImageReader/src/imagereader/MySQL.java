package imagereader;

import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;

//  Create Table dbo.pictures(
//	id    int IDENTITY(1,1) NOT NULL,
//	photo image NOT NULL,
//	name  nvarchar(50) NULL,
//  Constraint PK_pictures primary key clustered (id ASC) )

public class MySQL {
    static String url;
    
    static{
         url="jdbc:sqlserver://localhost\\SQLExpress;database=AdventureWorks";
         try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
         }catch(ClassNotFoundException ex) { 
             System.err.println(ex.getMessage()); }
    }
    public static String[] getPhotos() {
        ArrayList<String> list=null;
        
        try(Connection con=DriverManager.
                        getConnection(url,"sa","1234")){
    
            String sql="Select id,name from dbo.pictures";
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(sql);
            
            list=new ArrayList<>();
            while(rs.next())
               list.add(rs.getString(1)+" "+rs.getString(2));
        }
        catch(SQLException ex) { System.err.println(ex.getMessage()); }
        
        return list.toArray(new String[]{});
    }
    public static BufferedImage getPhoto(int id){
        BufferedImage buf=null;
        InputStream in=null;
        
        try(Connection con=DriverManager.
                        getConnection(url,"sa","1234")){
    
            String sql="Select photo from dbo.pictures where id=?";
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs=pst.executeQuery();
            rs.next();
            in=rs.getBinaryStream(1); 
            buf=ImageIO.read(in);
        }
        catch(Exception ex) { System.err.println(ex.getMessage()); } 
        
        return buf; 
    }
}
