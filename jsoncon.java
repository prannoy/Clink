import java.io.File;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class jsoncon {

	/**
	 * @param args
	 */
	final static int n = 23;// enter number of merchants in the list
	final static int c = 9;// number of columns for each merchant

	public static void getMerchantId(JSONObject json,String orgId)
			throws JSONException {

		String urlString = "http://ratnakar.api.clinknow.com/m/campaign/update/"+orgId;
		String result = "";
		String contentType = "application/json";
		String charset = "UTF-8";
		String name = "ratnakar";
		String password = "banksecret";

		String authString = name + ":" + password;
		System.out.println("auth string: " + authString);
		byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
		String authStringEnc = new String(authEncBytes);
		System.out.println("Base64 encoded auth string: " + authStringEnc);

		URL url = null;
		OutputStream output = null;
		InputStream response = null;

		try {
			url = new URL(urlString);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("Authorization", "Basic "
					+ authStringEnc);
			connection.setDoOutput(true);
			connection.setRequestMethod("PUT");
			connection.setRequestProperty("Accept-Charset", charset);
			connection.setRequestProperty("Content-Type", contentType
					+ ";charset=" + charset);
			// connection.setRequestProperty("username", username);
			// connection.setRequestProperty("password", password);
			
			OutputStreamWriter out = new OutputStreamWriter(
					connection.getOutputStream());
			out.write(json.toString());
			out.close();
			connection.getInputStream();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws JSONException {
		// String[] details = new String[c];
		String[] headings = new String[c];
		String orgId = new String();
		JSONObject jsonObj = new JSONObject();
		JSONObject json = new JSONObject();
		JSONParser parser = new JSONParser();

		JSONObject addressOrg = new JSONObject();
		addressOrg.put("line_1", "Dummy_line1");
		addressOrg.put("line_2", "Dummy_line2");
		addressOrg.put("city", "Dummy");
		addressOrg.put("state", "Dummy State");
		addressOrg.put("country", "India");
		addressOrg.put("pincode", 500008);

		try {

			// Create a workbook object from the file at specified location.
			// Change the path of the file as per the location on your computer.
			Workbook wrk1 = Workbook.getWorkbook(new File(
					"F:/campaigns with ids.xls"));

			// Obtain the reference to the first sheet in the workbook
			Sheet sheet1 = wrk1.getSheet(0);
			

			for (int k = 0; k < c; k++) {
				Cell colArow = sheet1.getCell(k, 0);
				String str_colArow = colArow.getContents();
				headings[k] = str_colArow;
				System.out.println("Heading of  " + (k + 1) + " column \""
						+ str_colArow + "\"");
			}
			for (int i = 1; i < n; i++) {
				for (int j = 0; j < c; j++)
				// Obtain reference to the Cell using getCell(int col, int row)
				// method of sheet
				{
					JSONArray campaignInfo = new JSONArray();
					Cell colArow1 = sheet1.getCell(j, i);
					String str_colArow1 = colArow1.getContents();
					if (headings[j].equals("creative_id"))
						json.put(headings[j], str_colArow1);
					else {
						if (headings[j].equals("campaign_info")) {
							campaignInfo.put(json);// (headings[j],json);
							jsonObj.put(headings[j],campaignInfo);

						} else
							jsonObj.put(headings[j], str_colArow1);
					}
					// System.out.println("Contents of cell Col" + jsonObj);
					// details[j]=str_colArow1;
				}
				System.out.println(jsonObj);
				Cell colArow1 = sheet1.getCell(c, i);
				String str_colArow = colArow1.getContents();

				jsoncon.getMerchantId(jsonObj,str_colArow);


				// System.out.println("org_id= " +
				// jsonp.get("Organization_id"));
			}
			// writing all orgId's into the excel sheet

		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 

	}

}
