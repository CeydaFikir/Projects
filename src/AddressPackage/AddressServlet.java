package AddressPackage;


import AddressPackage.AddressDAO;
import AddressPackage.Address;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/")
public class AddressServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AddressDAO addressDAO;

    public void init() {
        addressDAO = new AddressDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertAddress(request, response);
                    break;
                case "/delete":
                    deleteAddress(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateAddress(request, response);
                    break;
                default:
                    listAddress(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listAddress(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Address> listAddress = addressDAO.selectAllAddresses();
        request.setAttribute("listAddress", listAddress);
        RequestDispatcher dispatcher = request.getRequestDispatcher("address-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("address-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Address existingAddress = addressDAO.selectAddress(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("address-form.jsp");
        request.setAttribute("address", existingAddress);
        dispatcher.forward(request, response);
    }

    private void insertAddress(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String addressCode = request.getParameter("addressCode");
        String addressType = request.getParameter("addressType");
        String address = request.getParameter("address");
        String district = request.getParameter("district");
        String zipcode = request.getParameter("zipcode");
        String city = request.getParameter("city");

        Address newAddress = new Address(addressCode, addressType, address, district, zipcode, city);
        addressDAO.insertAddress(newAddress);
        response.sendRedirect("list");
    }

    private void updateAddress(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String addressCode = request.getParameter("addressCode");
        String addressType = request.getParameter("addressType");
        String address = request.getParameter("address");
        String district = request.getParameter("district");
        String zipcode = request.getParameter("zipcode");
        String city = request.getParameter("city");

        Address updatedAddress = new Address(id, addressCode, addressType, address, district, zipcode, city);
        addressDAO.updateAddress(updatedAddress);
        response.sendRedirect("list");
    }

    private void deleteAddress(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        addressDAO.deleteAddress(id);
        response.sendRedirect("list");
    }
}
