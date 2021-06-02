package servlet;

import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;


@WebServlet("/ImobiliariaServlet")
public class ImobiliariaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		byte[] b = IOUtils.toByteArray(request.getInputStream());

		JSONObject obj = new JSONObject(new String(b));
		String img = obj.getString("imagem");
		String prop = obj.getString("proprietario");
		
		byte[] imgByte = Base64.decodeBase64(img);
		
		FileOutputStream fos = 
				new FileOutputStream("C:\\temp\\" + prop + "_imgImovel.jpg");
		fos.write(imgByte);
		fos.close();
		
		response.getWriter().print("{\"status\":\"OK\"}");
		
	}

}
