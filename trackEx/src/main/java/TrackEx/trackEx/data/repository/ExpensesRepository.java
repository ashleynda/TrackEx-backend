package TrackEx.trackEx.data.repository;

import TrackEx.trackEx.data.model.Expenses;
import TrackEx.trackEx.data.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ExpensesRepository extends MongoRepository<Expenses, String> {


    List<Expenses> findAllExpensesByAccountOwner(User user);
}
