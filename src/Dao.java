import java.util.List;
import java.util.Optional;

public interface Dao <T>{
    Optional<T> get(int id);
    Optional<T> getStu(int id);


    //hata verirse import java.util.List kaynaklı olabilir kontrol et.
    List<T> getAll();
    String getAllSTR();

    void save(T t);

    void update(T t, String[] params);

    void delete(T t);
}
