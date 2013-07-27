import java.io.File;


import java.io.IOException;



import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


import org.apache.commons.codec.binary.Base64;

import org.json.JSONException;
import org.json.JSONObject;


public class jsoncon {

	/**
	 * @param args
	 */
	final static int n = 28;// enter number of creatives in the list
	final static int c = 6;// number of fields in the creative json

	public static void getMerchantId(JSONObject json,String s) throws JSONException {

		String urlString = "http://ratnakar.api.clinknow.com/m/creative/update/"+s;
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
		// URLConnection connection = null;


		try {
			url = new URL(urlString);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			// connection = url.openConnection();
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

		} catch (MalformedURLException e) {
			System.out.println("Incorrect URL for Rest Services");
		} catch (UnsupportedEncodingException e) {
			System.out.println("Unable to connect to Rest Services");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Unable to connect to Rest Services");
		}

	}

	public static void main(String[] args) throws JSONException {
		String[] headings = new String[c];
		
		String id = new String();
		JSONObject jsonObj = new JSONObject();

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
			Workbook wrk1 = Workbook.getWorkbook(new File("F:/creatives with ids.xls"));

			// Obtain the reference to the first sheet in the workbook
			Sheet sheet1 = wrk1.getSheet(0);

			for (int k = 0; k < c; k++) {
				Cell colArow = sheet1.getCell(k, 0);
				String str_colArow = colArow.getContents();
				headings[k] = str_colArow;
			}
			for (int i = 27; i < n; i++) {
				for (int j = 0; j < c; j++)
				// Obtain reference to the Cell using getCell(int col, int row)
				// method of sheet
				{

					Cell colArow1 = sheet1.getCell(j, i);
					String str_colArow1 = colArow1.getContents();
					if (headings[j].equals("creative_id"))
						  id=str_colArow1;
					else
						jsonObj.put(headings[j], str_colArow1);
					// System.out.println("Contents of cell Col" + jsonObj);
				}
				System.out.println(jsonObj);

				jsoncon.getMerchantId(jsonObj,id);

			}
			// writing all orgId's into the excel sheet

		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
