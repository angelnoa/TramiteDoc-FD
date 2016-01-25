package tramitedoc.concytec.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tramitedoc.concytec.bean.BParametro;
import tramitedoc.concytec.dao.sql.SqlParametroDAO;

/**
 * Servlet implementation class AActionParametro
 */
public class AActionParametro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AActionParametro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SqlParametroDAO daoSqlParametro = new SqlParametroDAO();
        BParametro parametro = new BParametro();
        Integer codigo_parametro = Integer.parseInt(request.getParameter("hdfIdParametro"));
        
       SqlParametroDAO sqlParametroDAO = new SqlParametroDAO();	
    	List<BParametro> parametros = sqlParametroDAO.listar();
    	
        for(BParametro p : parametros) {
       	 System.out.println("NO adentro" + p.getValor());
    		if(p.getCodigo_parametro() == codigo_parametro) {
    			parametro = p;
   			System.out.println(p.getValor());
    			break;
    		}
    	}
        
        if(parametro.getValor().equalsIgnoreCase("1")){        	 
       	 parametro.setValor("0");
        }else{
       	 parametro.setValor("1");
        }
        
        daoSqlParametro.modificar(parametro);
        response.sendRedirect("switchLogin.jsp");

	}

}
