
import java.io.BufferedWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


import org.json.JSONException;
import org.json.JSONObject;

public class dummymerchant {

	final static int n=738;//enter number of merchants in the list
	final static int c=11;//number of columns for each merchant
	public static void main(String[] args) {
		
		JSONObject jsonObj = new JSONObject();
		JSONObject addressOrg = new JSONObject();
		String[] headings=new String[c];
		BufferedWriter writer = null;
		

		 try {
	            
	            //Create a workbook object from the file at specified location.
	            //Change the path of the file as per the location on your computer.
	            Workbook wrk1 =  Workbook.getWorkbook(new File("F:/Book1.xls"));
	            writer = new BufferedWriter(new FileWriter("F:/output.txt"));
	            
	            
	             
	            //Obtain the reference to the first sheet in the workbook
	            Sheet sheet1 = wrk1.getSheet(0);
	            
	            
	            for(int k=0;k<c;k++)
		          {
		        	  Cell colArow = sheet1.getCell(k,0);
		               String str_colArow = colArow.getContents();
		               headings[k]=str_colArow;
		               //System.out.println("Heading of  "+(k+1)+" column \""+str_colArow + "\"");
		          }
	            
	            
	            //Iterate over row 0 and get the column names
	        
	            for(int i=1;i<=n;i++)
	            {
	            for(int j=0;j<c;j++)
	            //Obtain reference to the Cell using getCell(int col, int row) method of sheet
	               {
	            	Cell colArow1 = sheet1.getCell(j,i);
	                String str_colArow1 = colArow1.getContents();
	                if(j>6)
	                  addressOrg.put(headings[j],str_colArow1);
		            else
		               jsonObj.put(headings[j], str_colArow1);
	                if(j==c-1)
	    		    jsonObj.put("address", addressOrg); 
                  
                    }
	            //System.out.println("JSON Obj" + jsonObj);
	            writer.write(jsonObj+",\n");
           
				
	            } 
	            
	            if (writer != null) 
                writer.close();
                 
	                				
	           
	 
	        } catch (BiffException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (JSONException e) {
				e.printStackTrace();
			}
		
	}

}
