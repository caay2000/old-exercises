package com.jraska.rx.codelab;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import org.junit.Test;

public class Task1_Basics {
  @Test
  public void dummyObservable() {
    // TODO:  Create Observable with single String value, subscribe to it and print it to console (Observable.just)

    Observable<String> stringObservable = Observable.just("Hello World");

    stringObservable.subscribe(new Consumer<String>() {
      @Override
      public void accept(String s) throws Exception {
        System.out.println(s);
      }
    });

    stringObservable.subscribe(System.out::println);
  }

  @Test
  public void arrayObservable() {
    // TODO:  Create Observable with ints 1, 2, 3, 4, 5, subscribe to it and print each value to console (Observable.fromArray)

    Observable<Integer> integerObservable = Observable.fromArray(1, 2, 3, 4, 5);

    integerObservable.subscribe(System.out::println);
  }

  @Test
  public void helloOperator() {
    // TODO:  Create Observable with ints 1 .. 10 subscribe to it and print only odd values (Observable.range, observable.filter)

    Observable<Integer> integerObservable = Observable.range(1, 10);

    integerObservable
      .filter(i -> i % 2 == 1)
      .subscribe(System.out::println);
  }

  @Test
  public void receivingError() {
    // TODO:  Create Observable which emits an error and print the console (Observable.error), subscribe with onError handling

    Observable<Integer> integerObservable = Observable.error(new RuntimeException());

    integerObservable.subscribe(System.out::println, System.err::println);
  }
}
