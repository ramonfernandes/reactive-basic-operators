import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import org.junit.Assert;
import org.junit.Test;
import rx.Observable;

public class ReactiveOperatorsTest {

  @Test
  public void map() {
    List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6);
    List<Integer> result = new ArrayList<Integer>();

    Observable.from(integers)
        .map(this::multiplyByTen)
        .subscribe(result::add);

    Assert.assertEquals(Arrays.asList(10, 20, 30, 40, 50, 60), result);
  }

  @Test
  public void filter() {
    List<Integer> integers = Arrays.asList(10, 2, 3, 35, 50, 6);
    List<Integer> result = new ArrayList<Integer>();

    Observable.from(integers)
        .filter(this::biggerByTen)
        .subscribe(result::add);

    Assert.assertEquals(Arrays.asList(35, 50), result);
  }

  @Test
  public void toSortedList() {
    AtomicReference<List<Integer>> integers = new AtomicReference<>(
        Arrays.asList(10, 2, 3, 35, 50, 6));
    List<Integer> result = Arrays.asList(2, 3, 6, 10, 35, 50);

    Observable.from(integers.get())
        .toSortedList()
        .subscribe(integers::set);

    Assert.assertEquals(result, integers.get());

//   ⢀⣠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⣠⣤⣶⣶
//   ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⢰⣿⣿⣿⣿
//   ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⣀⣀⣾⣿⣿⣿⣿
//   ⣿⣿⣿⣿⣿⡏⠉⠛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⣿
//   ⣿⣿⣿⣿⣿⣿⠀⠀⠀⠈⠛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠛⠉⠁⠀⣿
//   ⣿⣿⣿⣿⣿⣿⣧⡀⠀⠀⠀⠀⠙⠿⠿⠿⠻⠿⠿⠟⠿⠛⠉⠀⠀⠀⠀⠀⣸⣿
//   ⣿⣿⣿⣿⣿⣿⣿⣷⣄⠀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣿⣿
//   ⣿⣿⣿⣿⣿⣿⣿⣿⣿⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⣴⣿⣿⣿⣿
//   ⣿⣿⣿⣿⣿⣿⣿⣿⡟⠀⠀⢰⣹⡆⠀⠀⠀⠀⠀⠀⣭⣷⠀⠀⠀⠸⣿⣿⣿⣿
//   ⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀⠀⠈⠉⠀⠀⠤⠄⠀⠀⠀⠉⠁⠀⠀⠀⠀⢿⣿⣿⣿
//   ⣿⣿⣿⣿⣿⣿⣿⣿⢾⣿⣷⠀⠀⠀⠀⡠⠤⢄⠀⠀⠀⠠⣿⣿⣷⠀⢸⣿⣿⣿
//   ⣿⣿⣿⣿⣿⣿⣿⣿⡀⠉⠀⠀⠀⠀⠀⢄⠀⢀⠀⠀⠀⠀⠉⠉⠁⠀⠀⣿⣿⣿
//   ⣿⣿⣿⣿⣿⣿⣿⣿⣧⠀⠀⠀⠀⠀⠀⠀⠈⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⣿⣿
//   ⣿⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿
  }

  @Test
  public void reduce() {
    List<String> integers = Arrays.asList("10", "2", "3", "35", "50", "6");
    AtomicReference<String> result = new AtomicReference<String>();

    Observable.from(integers)
        .reduce((a, b) -> a + ":reduziu:" + b)
        .subscribe(result::set);

    Assert.assertEquals("10:reduziu:2:reduziu:3:reduziu:35:reduziu:50:reduziu:6", result.get());
  }

  @Test
  public void buffer() {
    List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6);
    ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();

    Observable.from(integers)
        .buffer(3)
        .subscribe(result::add);

    Assert.assertEquals(Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6)), result);
  }

  @Test
  public void concat() {
    List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6);
    ArrayList<Integer> result = new ArrayList<>();

    Observable.from(integers)
        .repeat(3)
        .subscribe(result::add);

    Assert.assertEquals(Arrays
        .asList(1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6), result);
  }

  @Test
  public void pluck() {
    // aparentemente não tem pluck no RxJava dai eu fiz na mão
    List<String> stringers = Arrays
        .asList("1ramon", "2ramon", "3ramon", "4ramon", "5ramon", "6ramon");
    ArrayList<Integer> result = new ArrayList<>();

    Observable.from(stringers)
        .map(s -> s.replace("ramon", ""))
        .map(Integer::valueOf)
        .subscribe(result::add);

    Assert.assertEquals(Arrays
        .asList(1, 2, 3, 4, 5, 6), result);
  }

  int multiplyByTen(int x) {
    return x * 10;
  }

  boolean biggerByTen(int x) {
    return x > 10;
  }

}
