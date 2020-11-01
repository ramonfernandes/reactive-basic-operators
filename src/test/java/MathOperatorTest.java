import java.util.Arrays;
import org.junit.Test;
import rx.Observable;
import rx.observables.MathObservable;
import rx.observers.TestSubscriber;

public class MathOperatorTest {

  @Test
  public void max() {
    Observable<Integer> observable = Observable.from(Arrays.asList(1, 2, 52, 25, 13, 62, 55));
    TestSubscriber<Integer> subscriber = TestSubscriber.create();

    MathObservable.max(observable).subscribe(subscriber);

    subscriber.assertValue(62);
  }

  @Test
  public void min() {
    Observable<Integer> observable = Observable.from(Arrays.asList(52, 25, 13, 62, 55));
    TestSubscriber<Integer> subscriber = TestSubscriber.create();

    MathObservable.min(observable).subscribe(subscriber);

    subscriber.assertValue(13);
  }

  @Test
  public void average() {
    Observable<Integer> observable = Observable.from(Arrays.asList(20, 20, 10, 10));
    TestSubscriber<Integer> subscriber = TestSubscriber.create();

    MathObservable.averageInteger(observable).subscribe(subscriber);

    subscriber.assertValue(15);
  }

  @Test
  public void sum() {
    Observable<Integer> observable = Observable.from(Arrays.asList(20, 20, 10, 10));
    TestSubscriber<Integer> subscriber = TestSubscriber.create();

    MathObservable.sumInteger(observable).subscribe(subscriber);

    subscriber.assertValue(60);
  }


}
