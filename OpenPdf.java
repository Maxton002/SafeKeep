import dao.InventoryUtils;
import java.io.File;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Cris
 */
public class OpenPdf {
    
    public static void OpenById(String id){
        try{
            if((new File(InventoryUtils.billPath+id+".pdf")).exists()){
                Process p =Runtime
                        .getRuntime()
                        .exec("rund1132 url.dll,FileProtocolHandler "+InventoryUtils.billPath+""+id+".pdf");
                        
            }else{
                JOptionPane.showMessageDialog(null, "File does not Exist");
            }
        }
        catch(Exception e){  
            JOptionPane.showMessageDialog(null, e);
        }  
    }
}
