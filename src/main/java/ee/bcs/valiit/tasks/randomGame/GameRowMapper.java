package ee.bcs.valiit.tasks.randomGame;

import org.springframework.jdbc.core.RowMapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameRowMapper implements RowMapper<Game> {

    @Override
    public Game mapRow(ResultSet resultSet, int i) throws SQLException {
        Game game = new Game();
        game.setId(resultSet.getInt("id"));
        game.setRandomNumber(resultSet.getInt("random_number"));
        game.setCount(resultSet.getInt("count"));
        return game;
    }
}
