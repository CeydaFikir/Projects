package AddressPackage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import AddressPackage.Address;

public class AddressDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/intecon";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Sebahat1970!";

    private static final String INSERT_ADDRESS_SQL = "INSERT INTO ADDRESS_TABLE (ADDRCODE, ADDRTYPE, ADDRESS, DISTRICT, ZIPCODE, CITY) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String SELECT_ADDRESS_BY_ID = "SELECT * FROM ADDRESS_TABLE WHERE IDX = ?";
    private static final String SELECT_ALL_ADDRESSES = "SELECT * FROM ADDRESS_TABLE";
    private static final String UPDATE_ADDRESS_SQL = "UPDATE ADDRESS_TABLE SET ADDRCODE = ?, ADDRTYPE = ?, ADDRESS = ?, DISTRICT = ?, ZIPCODE = ?, CITY = ? WHERE IDX = ?;";
    private static final String DELETE_ADDRESS_SQL = "DELETE FROM ADDRESS_TABLE WHERE IDX = ?;";

    public AddressDAO() {}

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // Insert an address
    public void insertAddress(Address address) throws SQLException {
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ADDRESS_SQL)) {
            preparedStatement.setString(1, address.getAddressCode());
            preparedStatement.setString(2, address.getAddressType());
            preparedStatement.setString(3, address.getAddress());
            preparedStatement.setString(4, address.getDistrict());
            preparedStatement.setString(5, address.getZipcode());
            preparedStatement.setString(6, address.getCity());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    // Select an address by ID
    public Address selectAddress(int id) {
        Address address = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ADDRESS_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String addressCode = rs.getString("ADDRCODE");
                String addressType = rs.getString("ADDRTYPE");
                String addr = rs.getString("ADDRESS");
                String district = rs.getString("DISTRICT");
                String zipcode = rs.getString("ZIPCODE");
                String city = rs.getString("CITY");
                address = new Address(id, addressCode, addressType, addr, district, zipcode, city);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return address;
    }

    // Select all addresses
    public List<Address> selectAllAddresses() {
        List<Address> addresses = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ADDRESSES)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("IDX");
                String addressCode = rs.getString("ADDRCODE");
                String addressType = rs.getString("ADDRTYPE");
                String addr = rs.getString("ADDRESS");
                String district = rs.getString("DISTRICT");
                String zipcode = rs.getString("ZIPCODE");
                String city = rs.getString("CITY");
                addresses.add(new Address(id, addressCode, addressType, addr, district, zipcode, city));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return addresses;
    }

    // Update an address
    public boolean updateAddress(Address address) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ADDRESS_SQL)) {
            preparedStatement.setString(1, address.getAddressCode());
            preparedStatement.setString(2, address.getAddressType());
            preparedStatement.setString(3, address.getAddress());
            preparedStatement.setString(4, address.getDistrict());
            preparedStatement.setString(5, address.getZipcode());
            preparedStatement.setString(6, address.getCity());
            preparedStatement.setInt(7, address.getId());
            rowUpdated = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    // Delete an address
    public boolean deleteAddress(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ADDRESS_SQL)) {
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
