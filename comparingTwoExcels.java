import java.io.File;




import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.*;
import jxl.write.Boolean;
import jxl.write.Number;
import jxl.write.biff.RowsExceededException;


public class categorycode {
	final static int n=46783;//enter number of merchants in the list
	 final static int c=3;//number of columns for each merchant
	public static void main(String[] args) {
		
		 try {
            
	            //Create a workbook object from the file at specified location.
	            //Change the path of the file as per the location on your computer.
	            Workbook wrk1 =  Workbook.getWorkbook(new File("F:/Transaction Data Dump combined dumpt.xls"));
	            WritableWorkbook copy = Workbook.createWorkbook(new File("F:/Transaction Data Dump combined dumpt copy.xls"), wrk1);
	            WritableSheet sheet2 = copy.getSheet(1);
	            Workbook wrk2 =  Workbook.getWorkbook(new File("F:/Complete mcc codes.xls"));
	            
	            
	             
	            //Obtain the reference to the first sheet in the workbook
	            Sheet sheet1 = wrk1.getSheet(1);
	            Sheet sheet3 = wrk2.getSheet(0);
	            
	            
	            //Iterate over row 0 and get the column names
	        
	            for(int i=1;i<=n;i++)
	            {
	            //Obtain reference to the Cell using getCell(int col, int row) method of sheet
	            	Cell colArow1 = sheet1.getCell(9,i);
	                String str_colArow1 = colArow1.getContents();
	                for(int j=1;j<739;j++)
	                {
	                	Cell colArow2 = sheet3.getCell(0,j);
	                	String str_colArow2 = colArow2.getContents();
	                	if(str_colArow2.equals(str_colArow1))
	                	{
	                		Cell colArow3 = sheet3.getCell(1,j);
		                	String str_colArow3 = colArow3.getContents();
		                	Cell colArow4 = sheet3.getCell(2,j);
		                	String str_colArow4 = colArow4.getContents();
	    					
		                	Label label2 =new Label(15,i,str_colArow3);
	    					sheet2.addCell(label2);
	    					Label label3 =new Label(16,i,str_colArow4);
	    					sheet2.addCell(label3);
	                	}
	                	
	                }
                     System.out.println("Contents of cell Row "+i+": \""+str_colArow1 + "\"");
                     //WritableCell cell = sheet2.getWritableCell(c+1,i);
                     
              
					
					
					
	            } 
	                copy.write();
					copy.close();
				
	           
	 
	        } catch (BiffException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }catch (WriteException e) {
				e.printStackTrace();
			}
		
	}
}

