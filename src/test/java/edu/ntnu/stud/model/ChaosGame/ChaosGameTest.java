//package edu.ntnu.stud.model;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import edu.ntnu.stud.model.chaosGame.ChaosGame;
//import edu.ntnu.stud.model.chaosGame.ChaosGameDescription;
//import edu.ntnu.stud.model.linalg.Matrix2x2;
//import edu.ntnu.stud.model.linalg.vector.Vector2D;
//import edu.ntnu.stud.model.fractals.AffineTransform2D;
//import edu.ntnu.stud.model.interfaces.Transform2D;
//import java.io.FileNotFoundException;
//import java.util.ArrayList;
//import java.util.List;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//
//class ChaosGameTest {
//
//  private ChaosGame game;
//  private ChaosGameDescription description;
//  private Vector2D minCoords;
//  private Vector2D maxCoords;
//  private List<Transform2D> transforms;
//  private TestObserver observer;
//  private TestObserver observer2;
//
//  @BeforeEach
//  void setUp() throws FileNotFoundException {
//    observer = new TestObserver();
//    observer2 = new TestObserver();
//    minCoords = new Vector2D(0, 0);
//    maxCoords = new Vector2D(1, 1);
//    transforms = new ArrayList<>();
//    description = new ChaosGameDescription(minCoords, maxCoords, transforms);
//    transforms.add(new AffineTransform2D(new Matrix2x2(1, 0, 0, 1), new Vector2D(0, 0)));
//    game = new ChaosGame(description, new Vector2D(0,0), 100, 100);
//    TestObserver observer = new TestObserver();
//    TestObserver observer2 = new TestObserver();
//  }
//
//
//  @Test
//  void testAddObserver() {
//    game.addObserver(observer);
//    assertEquals(1, game.getAllObservers().size());
//  }
//
//  @Test
//  void testRemoveObserver() {
//    game.addObserver(observer);
//    game.removeObserver(observer);
//  }
//
//  @Test
//  @Disabled
//  void testNotifyObservers() {
//    game.addObserver(observer);
//    game.addObserver(observer2);
//    //game.run(10);
//    assertEquals(10, observer.getUpdateCount());
//    assertEquals(10, observer2.getUpdateCount());
//  }
//}
//
