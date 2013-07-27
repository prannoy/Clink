import java.io.File;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
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
	final static int n = 9;// enter number of merchants in the list
	final static int c = 7;// number of fields in the merchant json

	public static JSONObject getMerchantId(JSONObject json)
			throws JSONException {

		String urlString = "http://ratnakar.api.clinknow.com/m/merchant/add";
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
		URLConnection connection = null;
		OutputStream output = null;
		InputStream response = null;

		try {
			url = new URL(urlString);
			connection = url.openConnection();
			connection.setRequestProperty("Authorization", "Basic "
					+ authStringEnc);
			connection.setDoOutput(true);
			connection.setRequestProperty("Accept-Charset", charset);
			connection.setRequestProperty("Content-Type", contentType
					+ ";charset=" + charset);
			// connection.setRequestProperty("username", username);
			// connection.setRequestProperty("password", password);
			output = connection.getOutputStream();
			output.write(json.toString().getBytes());

			response = connection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(
					response));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			response.close();
			result = sb.toString();
			System.out.println("Result : \n" + result);
		} catch (MalformedURLException e) {
			System.out.println("Incorrect URL for Rest Services");
		} catch (UnsupportedEncodingException e) {
			System.out.println("Unable to connect to Rest Services");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Unable to connect to Rest Services");
		}

		return new JSONObject(result);
	}

	public static void main(String[] args) throws JSONException {
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
					"F:/merchants.xls"));

			// Obtain the reference to the first sheet in the workbook
			Sheet sheet1 = wrk1.getSheet(0);
			WritableWorkbook copy = Workbook.createWorkbook(new File(
					"F:/merchants with ids.xls"), wrk1);
			WritableSheet sheet2 = copy.getSheet(0);

			for (int k = 0; k < c; k++) {
				Cell colArow = sheet1.getCell(k, 0);
				String str_colArow = colArow.getContents();
				headings[k] = str_colArow;
			}
			Label label2 = new Label(c, 0, "merchant_id");
			sheet2.addCell(label2);
			for (int i = 1; i < n; i++) {
				for (int j = 0; j < c; j++)
				// Obtain reference to the Cell using getCell(int col, int row)
				// method of sheet
				{
					
					Cell colArow1 = sheet1.getCell(j, i);
					String str_colArow1 = colArow1.getContents();
					if (headings[j].equals("address"))
						json.put(headings[j], addressOrg);
					else
                        jsonObj.put(headings[j], str_colArow1);
					// System.out.println("Contents of cell Col" + jsonObj);
				}
				System.out.println(jsonObj);

				JSONObject orgid = jsoncon.getMerchantId(jsonObj);
				System.out.println(orgid);

				Object obj = parser.parse(orgid.toString());

				org.json.simple.JSONObject jsonp = (org.json.simple.JSONObject) obj;
				orgId = (String) jsonp.get("merchant_id");
				Label label1 = new Label(c, i, orgId);
				sheet2.addCell(label1);

			}
			// writing all orgId's into the excel sheet
			copy.write();
			copy.close();

		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}

	}

}
