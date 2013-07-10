import java.io.File;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
<<<<<<< HEAD
import java.io.UnsupportedEncodingException;
=======
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
>>>>>>> 8956a8e909923766b4a1ca96780018f6e5327484
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
<<<<<<< HEAD
	final static int n = 9;// enter number of merchants in the list
	final static int c = 3;// number of fields in the organisations json

	public static JSONObject getMerchantId(JSONObject json)
			throws JSONException {

		String urlString = "http://ratnakar.api.clinknow.com/m/organization/add";
=======
	final static int n = 23;// enter number of merchants in the list
	final static int c = 9;// number of columns for each merchant

	public static void getMerchantId(JSONObject json,String orgId)
			throws JSONException {

		String urlString = "http://ratnakar.api.clinknow.com/m/campaign/update/"+orgId;
>>>>>>> 8956a8e909923766b4a1ca96780018f6e5327484
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
<<<<<<< HEAD
		URLConnection connection = null;
=======
>>>>>>> 8956a8e909923766b4a1ca96780018f6e5327484
		OutputStream output = null;
		InputStream response = null;

		try {
			url = new URL(urlString);
<<<<<<< HEAD
			connection = url.openConnection();
			connection.setRequestProperty("Authorization", "Basic "
					+ authStringEnc);
			connection.setDoOutput(true);
=======
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("Authorization", "Basic "
					+ authStringEnc);
			connection.setDoOutput(true);
			connection.setRequestMethod("PUT");
>>>>>>> 8956a8e909923766b4a1ca96780018f6e5327484
			connection.setRequestProperty("Accept-Charset", charset);
			connection.setRequestProperty("Content-Type", contentType
					+ ";charset=" + charset);
			// connection.setRequestProperty("username", username);
			// connection.setRequestProperty("password", password);
<<<<<<< HEAD
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
=======
			
			OutputStreamWriter out = new OutputStreamWriter(
					connection.getOutputStream());
			out.write(json.toString());
			out.close();
			connection.getInputStream();
		}catch(Exception e){
			e.printStackTrace();
		}
>>>>>>> 8956a8e909923766b4a1ca96780018f6e5327484
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
<<<<<<< HEAD
					"F:/Organizations.xls"));

			// Obtain the reference to the first sheet in the workbook
			Sheet sheet1 = wrk1.getSheet(0);
			WritableWorkbook copy = Workbook.createWorkbook(new File(
					"F:/Organizations with ids.xls"), wrk1);
			WritableSheet sheet2 = copy.getSheet(0);
=======
					"F:/campaigns with ids.xls"));

			// Obtain the reference to the first sheet in the workbook
			Sheet sheet1 = wrk1.getSheet(0);
			
>>>>>>> 8956a8e909923766b4a1ca96780018f6e5327484

			for (int k = 0; k < c; k++) {
				Cell colArow = sheet1.getCell(k, 0);
				String str_colArow = colArow.getContents();
				headings[k] = str_colArow;
<<<<<<< HEAD
			}
			Label label2 = new Label(c, 0, "organization_id");
			sheet2.addCell(label2);
=======
				System.out.println("Heading of  " + (k + 1) + " column \""
						+ str_colArow + "\"");
			}
>>>>>>> 8956a8e909923766b4a1ca96780018f6e5327484
			for (int i = 1; i < n; i++) {
				for (int j = 0; j < c; j++)
				// Obtain reference to the Cell using getCell(int col, int row)
				// method of sheet
				{
					JSONArray campaignInfo = new JSONArray();
					Cell colArow1 = sheet1.getCell(j, i);
					String str_colArow1 = colArow1.getContents();
<<<<<<< HEAD
					if (headings[j].equals("address"))
						json.put(headings[j], addressOrg);
					else
                        jsonObj.put(headings[j], str_colArow1);
=======
					if (headings[j].equals("creative_id"))
						json.put(headings[j], str_colArow1);
					else {
						if (headings[j].equals("campaign_info")) {
							campaignInfo.put(json);// (headings[j],json);
							jsonObj.put(headings[j],campaignInfo);

						} else
							jsonObj.put(headings[j], str_colArow1);
					}
>>>>>>> 8956a8e909923766b4a1ca96780018f6e5327484
					// System.out.println("Contents of cell Col" + jsonObj);
					// details[j]=str_colArow1;
				}
				System.out.println(jsonObj);
<<<<<<< HEAD

				JSONObject orgid = jsoncon.getMerchantId(jsonObj);
				System.out.println(orgid);

				Object obj = parser.parse(orgid.toString());

				org.json.simple.JSONObject jsonp = (org.json.simple.JSONObject) obj;
				orgId = (String) jsonp.get("organization_id");
				Label label1 = new Label(c, i, orgId);
				sheet2.addCell(label1);
=======
				Cell colArow1 = sheet1.getCell(c, i);
				String str_colArow = colArow1.getContents();

				jsoncon.getMerchantId(jsonObj,str_colArow);

>>>>>>> 8956a8e909923766b4a1ca96780018f6e5327484

				// System.out.println("org_id= " +
				// jsonp.get("Organization_id"));
			}
			// writing all orgId's into the excel sheet
<<<<<<< HEAD
			copy.write();
			copy.close();
=======
>>>>>>> 8956a8e909923766b4a1ca96780018f6e5327484

		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
<<<<<<< HEAD
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
=======
		} 
>>>>>>> 8956a8e909923766b4a1ca96780018f6e5327484

	}

}
