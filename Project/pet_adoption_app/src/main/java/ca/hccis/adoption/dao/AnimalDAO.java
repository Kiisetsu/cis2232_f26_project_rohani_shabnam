package ca.hccis.adoption.dao;

import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AnimalDAO {

    public record AnimalRow(int id, String name, String sex, String species,
                            String colour, String coat, double age) {}

    private final DataSource dataSource;

    public AnimalDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<AnimalRow> findBySpeciesAndAge(String species, double minAge, double maxAge)
            throws SQLException {

        String sql = "SELECT id, name, sex, species, colour, coat, age "
                + "FROM Animal WHERE species = ? AND age BETWEEN ? AND ? "
                + "ORDER BY age, name";

        List<AnimalRow> rows = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, species);
            ps.setDouble(2, minAge);
            ps.setDouble(3, maxAge);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    rows.add(new AnimalRow(rs.getInt("id"), rs.getString("name"),
                            rs.getString("sex"), rs.getString("species"),
                            rs.getString("colour"), rs.getString("coat"),
                            rs.getDouble("age")));
                }
            }
        }
        return rows;
    }
}
