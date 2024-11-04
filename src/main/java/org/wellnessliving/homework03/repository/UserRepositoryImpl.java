package org.wellnessliving.homework03.repository;

import org.wellnessliving.homework03.entity.User;
import org.wellnessliving.homework03.exception.DataException;
import org.wellnessliving.homework03.util.DataSource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    private static final int MIN_AFFECTED_ROWS_COUNT = 1;
    private static final String ID = "id";
    private static final String EMAIL = "email";
    private static final String PHONE = "phone";

    @Override
    public Optional<User> findById(Long id) throws DataException {
        String sqlRequest = "SELECT * FROM users WHERE id = ?";

        try (PreparedStatement preparedStatement = DataSource.getConnection().prepareStatement(sqlRequest)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(resultToUser(resultSet));
            }
        } catch (SQLException e) {
            throw new DataException("Can't find car by id. ID=" + id, e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> save(User user) throws DataException {
        String sqlRequest = "INSERT INTO users (email, pwd, phone) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = DataSource.getConnection().prepareStatement(
                sqlRequest,
                Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getPhoneNumber());

            checkAffectedRowsNumber(preparedStatement.executeUpdate());
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                Long id = generatedKeys.getObject(1, Long.class);
                user.setId(id);
                user.setPassword("");
                return Optional.of(user);
            }
        } catch (SQLException e) {
            throw new DataException("Can't create instance of user:"
                    + user.toString()
                    + " and add it to DB", e);
        }
        return Optional.empty();
    }

    private void checkAffectedRowsNumber(int number) throws DataException {
        if (number < MIN_AFFECTED_ROWS_COUNT) {
            throw new DataException("Excepted to change at least 1 row. "
                    + "But changed 0 rows.");
        }
    }

    private User resultToUser(ResultSet requestResult) throws DataException {
        try {
            User user = new User();
            user.setId(requestResult.getObject(ID, Long.class));
            user.setEmail(requestResult.getString(EMAIL));
            user.setPhoneNumber(requestResult.getString(PHONE));
            return user;
        } catch (SQLException e) {
            throw new DataException("Cannot turn resultSet data into correct User data", e);
        }
    }
}
