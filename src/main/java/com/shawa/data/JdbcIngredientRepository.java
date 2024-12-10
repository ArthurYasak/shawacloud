//package sia.tacos.data;
//
//import lombok.extern.java.Log;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//import sia.tacos.Ingredient;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//@Log
//public class JdbcIngredientRepository implements IngredientRepository {
//
//    private JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public JdbcIngredientRepository(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    @Override
//    public Iterable<Ingredient> findAll() {
//        return jdbcTemplate.query(
//                "select id, name, type from Ingredient",
//                this::mapRowToIngredient);
//    }
//    @Override
//    public Optional<Ingredient> findById(String id) {
//        List<Ingredient> results = jdbcTemplate.query(
//                "select id, name, type from Ingredient where id=?",
//                this::mapRowToIngredient /*(res*//*, num*//*) -> mapRowToIngredient(res*//*, num*//*)*/,
//                id);
//        return results.size() == 0 ?
//                Optional.empty() :
//                Optional.of(results.get(0));
//    }
//
//    @Override
//    public Ingredient save(Ingredient ingredient) {
//        jdbcTemplate.update(
//                "insert into Ingredient (id, name, type) values (?, ?, ?)",
//                ingredient.getId(),
//                ingredient.getName(),
//                ingredient.getType().toString());
//        return ingredient;
//    }
//
//    // преобразует ResultSet получаемый при селекте в Ingredient
//    private Ingredient mapRowToIngredient(ResultSet row, int rowNum)
//            throws SQLException {
//        log.info(String.valueOf(rowNum)); // TODO
//        return new Ingredient(
//                row.getString("id"),
//                row.getString("name"),
//                Ingredient.Type.valueOf(row.getString("type")));
//    }
//
//}